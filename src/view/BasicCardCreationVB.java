package view;

import java.util.ArrayList;
import java.util.List;

import exceptions.DoubleException;
import exceptions.NotCompatibleException;
import exceptions.NotEnoughQuestionsException;
import exceptions.NullException;
import exceptions.TooManyQuestionsException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.BasicCard;
import model.Deck;
import model.Question;
import model.Theme;
import util.ReadWriteFile;

public class BasicCardCreationVB extends VBox{
	private Deck deck;
	
	private HBox hbTitle;
	private HBox hbThemeAuthor;
	private HBox hbSubject;
	private GridPane gpQuestions;
	private HBox hbError;
	private HBox hbButton;
	
	private Text txtTitle;
	
	private Label lblTheme;
	private ComboBox<String> cbTheme;
	private Label lblAuthor;
	private TextField txtAuthor;
	
	private Label lblSubject;
	private TextField txtSubject;
	
	private Label lblQuestion;
	private Label lblAnswer;
	private List<TextField> txtQuestions;
	private List<TextField> txtAnswers;
	private Text txtError;
	
	private ImageView imgAdd;
	private ImageView imgCancel;
	private ImageView imgBack;
	
	public BasicCardCreationVB(Deck deck) {
		this.deck = deck;
		//Positioning the VBox and determining the spacing
		this.setPadding(new Insets(GraphicConstants.BIG_PADDING));
		this.setSpacing(GraphicConstants.NORMAL_SPACING);
		this.setAlignment(Pos.CENTER_RIGHT);
		//Adding all the elements to the VBox
		this.getChildren().addAll(getHbTitle(), getHbThemeAuthor(), getHbSubject(), getGpQuestions(), getHbError(), getHbButton());
	}
	
	public HBox getHbTitle() {
		if(hbTitle == null) {
			//Adding the element to the HBox
			hbTitle = new HBox(getTxtTitle());
			//Positioning the HBox
			hbTitle.setAlignment(Pos.CENTER);
		}
		return hbTitle;
	}

	public HBox getHbThemeAuthor() {
		if(hbThemeAuthor == null) {
			//Adding the elements to the HBox
			hbThemeAuthor = new HBox(getLblTheme(), getCbTheme(), getLblAuthor(), getTxtAuthor());
			//Positioning the HBox and determining the spacing
			hbThemeAuthor.setSpacing(GraphicConstants.NORMAL_SPACING);
			hbThemeAuthor.setAlignment(Pos.CENTER_LEFT);
			HBox.setHgrow(getTxtAuthor(), Priority.ALWAYS);
		}
		return hbThemeAuthor;
	}

	public HBox getHbSubject() {
		if(hbSubject == null) {
			//Adding the elements to the HBox
			hbSubject = new HBox(getLblSubject(), getTxtSubject());
			//Positioning the HBox and determining the spacing
			hbSubject.setSpacing(GraphicConstants.NORMAL_SPACING);
			hbSubject.setAlignment(Pos.CENTER_LEFT);
			HBox.setHgrow(getTxtSubject(), Priority.ALWAYS);
		}
		return hbSubject;
	}

	public GridPane getGpQuestions() {
		if(gpQuestions == null) {
			gpQuestions = new GridPane();
			//Determining the spacing in the gridpane
			gpQuestions.setVgap(GraphicConstants.NORMAL_SPACING);
			gpQuestions.setHgap(GraphicConstants.NORMAL_SPACING);
			//Adding the labels to the gridpane
			gpQuestions.add(getLblQuestion(), 0, 0);
			gpQuestions.add(getLblAnswer(), 2, 0);
			//Insertion of the textfields for the questions and the answers in the gridpane
			for(int i = 0; i < getTxtQuestions().size(); i++) {
				gpQuestions.add(getTxtQuestions().get(i), 0, i + 1, 2, 1);
				gpQuestions.add(getTxtAnswers().get(i), 2, i + 1);
			}
			//Definition of the width of the cells in the gridpane
			int nbCols = 3;
			for(int i = 0; i < nbCols; i++) {
				ColumnConstraints constraint = new ColumnConstraints();
				constraint.setPercentWidth(100./nbCols);
				gpQuestions.getColumnConstraints().add(constraint);
			}
		}
		return gpQuestions;
	}
	
	public HBox getHbError() {
		if(hbError == null) {
			//Adding the element to the HBox
			hbError = new HBox(getTxtError());
			//Positioning the HBox
			hbError.setAlignment(Pos.BASELINE_RIGHT);
		}
		return hbError;
	}

	public HBox getHbButton() {
		if(hbButton == null) {
			//Adding the elements in the HBox
			hbButton = new HBox(getImgBack(), getImgCancel(), getImgAdd());
			//Positioning the HBox and determining the spacing
			hbButton.setAlignment(Pos.BOTTOM_RIGHT);
			hbButton.setSpacing(GraphicConstants.NORMAL_SPACING);
		}
		return hbButton;
	}
	
	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("Create a new card");
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtTitle.setEffect(ds);
		}
		return txtTitle;
	}

	public Label getLblTheme() {
		if(lblTheme == null) {
			lblTheme = new Label("Theme : ");
			lblTheme.setFont(GraphicConstants.SMALL_FONT);
			lblTheme.setTextFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblTheme.setEffect(ds);
		}
		return lblTheme;
	}

	public ComboBox<String> getCbTheme() {
		if(cbTheme == null) {
			cbTheme = new ComboBox<>();
			//Adding the Theme in the combo box
			cbTheme.getItems().addAll(Theme.IMPROBABLE.toString(), Theme.INFORMATICS.toString(), Theme.PLEASURE.toString(), Theme.SCHOOL.toString());
			//Selecting the first one by default
			cbTheme.getSelectionModel().selectFirst();
		}
		return cbTheme;
	}

	public Label getLblAuthor() {
		if(lblAuthor == null) {
			lblAuthor = new Label("Author : ");
			lblAuthor.setFont(GraphicConstants.SMALL_FONT);
			lblAuthor.setTextFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblAuthor.setEffect(ds);
		}
		return lblAuthor;
	}

	public TextField getTxtAuthor() {
		if(txtAuthor == null) {
			txtAuthor = new TextField();
		}
		return txtAuthor;
	}

	public Label getLblSubject() {
		if(lblSubject == null) {
			lblSubject = new Label("Subject : ");
			lblSubject.setFont(GraphicConstants.SMALL_FONT);
			lblSubject.setTextFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblSubject.setEffect(ds);
		}
		return lblSubject;
	}

	public TextField getTxtSubject() {
		if(txtSubject == null) {
			txtSubject = new TextField();
		}
		return txtSubject;
	}

	public Label getLblQuestion() {
		if(lblQuestion == null) {
			lblQuestion = new Label("Questions : ");
			lblQuestion.setFont(GraphicConstants.SMALL_FONT);
			lblQuestion.setTextFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblQuestion.setEffect(ds);
		}
		return lblQuestion;
	}

	public Label getLblAnswer() {
		if(lblAnswer == null) {
			lblAnswer = new Label("Answers : ");
			lblAnswer.setFont(GraphicConstants.SMALL_FONT);
			lblAnswer.setTextFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblAnswer.setEffect(ds);
		}
		return lblAnswer;
	}

	public List<TextField> getTxtQuestions() {
		if(txtQuestions == null) {
			txtQuestions = new ArrayList<>();
			//Creation of the 4 textfields for the questions
			for(int i = 0; i < 4; i++) {
				txtQuestions.add(new TextField());
			}
		}
		return txtQuestions;
	}

	public List<TextField> getTxtAnswers() {
		if(txtAnswers == null) {
			txtAnswers = new ArrayList<>();
			//Creation of the 4 textfields for the answers
			for(int i = 0; i < 4; i++) {
				txtAnswers.add(new TextField());
			}
		}
		return txtAnswers;
	}
	
	public Text getTxtError() {
		if(txtError == null) {
			txtError = new Text("");
			txtError.setFont(GraphicConstants.SMALL_FONT);
			txtError.setFill(Color.RED);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtError.setEffect(ds);
		}
		return txtError;
	}

	public ImageView getImgAdd() {
		if(imgAdd == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.ADD_PATH));
			imgAdd = new ImageView(img);
			//Determining the size of the image
			imgAdd.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgAdd.setPreserveRatio(true);
			//Determining the behavior on the click
			imgAdd.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Clear the error message
				getTxtError().setText("");
				if(!getTxtAuthor().getText().isEmpty() && !getTxtSubject().getText().isEmpty()) {
					//Creation of a new BasicCard based on the input given
					BasicCard card = new BasicCard(getTxtAuthor().getText(), Theme.fromString(getCbTheme().getSelectionModel().getSelectedItem()), getTxtSubject().getText());
					
					//Recovery of all the questions
					for(int i = 0; i < getTxtQuestions().size(); i++) {
						if(!getTxtQuestions().get(i).getText().isEmpty() && !getTxtAnswers().get(i).getText().isEmpty()) {
							Question question = new Question(getTxtAuthor().getText(), Theme.fromString(getCbTheme().getSelectionModel().getSelectedItem()), 
									getTxtSubject().getText(), getTxtQuestions().get(i).getText(), getTxtAnswers().get(i).getText());
							try {
								card.add(question);
							} catch (DoubleException | NullException | TooManyQuestionsException | NotCompatibleException e) {
	
							}
						}
					}
					try {
						deck.add(card);
						//Save the deck with the changes in the json file
						ReadWriteFile.write(FilePath.DECK_JSON_PATH, deck.toJson());
						//We add the card to the tableview containing all the cards in the BasicCardSuppressionVB class
						((WindowMainSP)BasicCardCreationVB.this.getParent()).getVbBasicCardSupression().getTvCards().getItems().add(card);
						//Clear all the input fields
						clearAll();
					} catch (DoubleException | NullException | NotEnoughQuestionsException e) {
						//Setting the error message
						getTxtError().setText("This card already exists or there was similar questions");
					}
				} else {
					//Setting the error message
					getTxtError().setText("The author or the subject is missing");
				}
			});
		}
		return imgAdd;
	}

	public ImageView getImgCancel() {
		if(imgCancel == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.CANCEL_PATH));
			imgCancel = new ImageView(img);
			//Determining the size of the image
			imgCancel.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgCancel.setPreserveRatio(true);
			//Determining the behavior on the click
			imgCancel.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				clearAll();
			});
		}
		return imgCancel;
	}
	
	public ImageView getImgBack() {
		if(imgBack == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.BACK_PATH));
			imgBack = new ImageView(img);
			//Determining the size of the image
			imgBack.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgBack.setPreserveRatio(true);
			//Determining the behavior on the click
			imgBack.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Clear all the input fields
				clearAll();
				//Display main menu
				BasicCardCreationVB.this.setVisible(false);
				((WindowMainSP)BasicCardCreationVB.this.getParent()).getVbCardMenu().setVisible(true);
			});
		}
		return imgBack;
	}
	
	private void clearAll() {
		//Clearing the values in the textfields
		getTxtAuthor().clear();
		getTxtSubject().clear();
		for(TextField txt : getTxtQuestions()) {
			txt.clear();
		}
		for(TextField txt : getTxtAnswers()) {
			txt.clear();
		}
		//Reselecting the first element in the combo box
		getCbTheme().getSelectionModel().selectFirst();
	}
}
