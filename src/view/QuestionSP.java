package view;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Question;

public class QuestionSP extends StackPane {
	private Question question;
	private StrategyAnswer strategyAnswer;
	
	private ImageView imgCard;
	private VBox vbQuestion;
	
	private Text txtQuestion;
	private TextField txtAnswer;
	private Button btnSubmit;
	
	private Label lblTimeLeft;
	private AnimationTimer timer;
	private Integer timeLeft;
	private long elapsedTime;
	
	public QuestionSP(Question question) {
		this.question = question;
		//Determining the size of the StackPane based on the size of the image
		this.setPrefHeight(getImgCard().getFitHeight());
		this.setPrefWidth(getImgCard().getFitWidth());
		//Adding the elements to the StackPane
		this.getChildren().addAll(getImgCard(), getVbQuestion());
		//Initialize the timer
		timeLeft = 30;
		elapsedTime = 0;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
		//Change the question to the current question
		getTxtQuestion().setText(question.getChallenge());
		//Set the image of the theme of the question
		getImgCard().setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("imgs/" + question.getTheme().toString() + ".png")));
		getLblTimeLeft().setTextFill(Color.BLACK);
		//Reset the timer
		timeLeft = 30;
		elapsedTime = 0;
		//Restart the timer
		getTimer().start();
	}
	
	public void setStrategyAnswer(StrategyAnswer strategy) {
		this.strategyAnswer = strategy;
	}

	public ImageView getImgCard() {
		if(imgCard == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream("imgs/" + question.getTheme().toString() + ".png"));
			imgCard = new ImageView(img);
			//Determining the size of the image
			imgCard.setFitHeight(GraphicConstants.CARD_HEIGHT);
			imgCard.setPreserveRatio(true);
		}
		return imgCard;
	}
	
	public VBox getVbQuestion() {
		if(vbQuestion == null) {
			//Adding the elements to the VBox
			vbQuestion = new VBox(getTxtQuestion(), getTxtAnswer(), getBtnSubmit(), getLblTimeLeft());
			//Positioning the VBox and determining the spacing
			vbQuestion.setAlignment(Pos.CENTER);
			vbQuestion.setSpacing(50.);
		}
		return vbQuestion;
	}

	public Text getTxtQuestion() {
		if(txtQuestion == null) {
			txtQuestion = new Text(question.getChallenge());
			txtQuestion.setFont(GraphicConstants.QUESTION_FONT);
			//Setting a width for the text and putting it in justify
			txtQuestion.setWrappingWidth(350);
			txtQuestion.setTextAlignment(TextAlignment.JUSTIFY);
		}
		return txtQuestion;
	}

	public TextField getTxtAnswer() {
		if(txtAnswer == null) {
			txtAnswer = new TextField();
			txtAnswer.setOnKeyReleased((event)->{
				if(event.getCode() == KeyCode.ENTER) {
					submit();
				}
			});
			txtAnswer.setMaxWidth(250.);
		}
		return txtAnswer;
	}
	
	public Button getBtnSubmit() {
		if(btnSubmit == null) {
			btnSubmit = new Button("Submit");
			//Determining the size of the button
			btnSubmit.setPrefWidth(100.);
			//Determining the behavior on the click
			btnSubmit.setOnAction((event)->submit());
		}
		return btnSubmit;
	}
	
	public Label getLblTimeLeft() {
		if(lblTimeLeft == null) {
			lblTimeLeft = new Label("30");
			lblTimeLeft.setFont(GraphicConstants.SMALL_FONT);
		}
		return lblTimeLeft;
	}
	
	public AnimationTimer getTimer() {
		if(timer == null) {
			timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					//If one second has passed
					if(now - elapsedTime >= 1000000000) {
						//Update the time display on the card
						getLblTimeLeft().setText(timeLeft.toString());
						//Decrease the time left and change the value of elapsedTime to now for the test the next second
						timeLeft--;
						elapsedTime = now;
						//If the time has ran out we check the answer
						if(timeLeft == -1) {
							if(question.checkAnswer(getTxtAnswer().getText())) {
								setStrategyAnswer(new AnswerCorrect());
								strategyAnswer.displayEffect(QuestionSP.this);
							} else {
								setStrategyAnswer(new AnswerWrong());
								strategyAnswer.displayEffect(QuestionSP.this);
							}
						//When there's 10 seconds left we put the text in red
						} else if(timeLeft == 9) {
							getLblTimeLeft().setTextFill(Color.RED);
						}
					}
				}
			};
		}
		return timer;
	}
	
	public void submit() {
		if(question.checkAnswer(getTxtAnswer().getText())) {
			setStrategyAnswer(new AnswerCorrect());
			strategyAnswer.displayEffect(QuestionSP.this);
		} else {
			setStrategyAnswer(new AnswerWrong());
			strategyAnswer.displayEffect(QuestionSP.this);
		}
	}
}
