package view;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import exceptions.ElementNotFoundException;
import exceptions.NullException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BasicCard;
import model.Boardgame;
import model.Deck;
import model.Pawn;
import model.PawnManagement;
import model.Question;
import model.Theme;

public class BoardBP extends BorderPane {
	//Declaration
	private Deck deck;
	private PawnManagement pawns;
	private Boardgame boardgame;
	private BasicCard currentCard;
	private Pawn currentPawn;
	private boolean challenge;

	private AnchorPane apTop;
	private StackPane spCenter;
	private AnchorPane apBottom;

	private GridPane gpCenter;
	private LocalTime letsGo;
	private StackPane spTimer;
	private ImageView imgTimer;
	private ImageView imgPause;
	private ImageView imgHelp;
	private List<ImageView> imgPawns;
	private Text txtCurrentPlayer;

	private DifficultyChoiceVB vbDifficulty;
	private QuestionSP spQuestion;
	private SpecialCardDisplaySP spSpecialCard;
	private Text txtAnswer;
	private PauseMenuVB vbPause;
	private Stage stgHelp;

	private VBox vbWinTop;
	private VBox vbWinBottom;
	private Text txtCongratulation;
	private Text txtWinner;
	private ImageView imgBackMenu;
	
	private HelpSP spHelp;

	//Constructor
	public BoardBP(Deck deck, PawnManagement pawns, Boardgame boardgame) {
		this.deck = deck;
		this.pawns = pawns;
		this.boardgame = boardgame;
		try {
			this.currentCard = deck.pickCard(Theme.IMPROBABLE);
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.challenge = false;
		this.currentPawn = null;

		this.setTop(getApTop());
		this.setCenter(getSpCenter());
		this.setBottom(getApBottom());
		displayPawn();
	}

	//Getter
	public Deck getDeck() {
		return deck;
	}
	
	public PawnManagement getPawns() {
		return pawns;
	}
	
	public Boardgame getBoardgame() {
		return boardgame;
	}

	public BasicCard getCurrentCard() {
		return currentCard;
	}
	
	public Pawn getCurrentPawn() {
		return currentPawn;
	}
	
	public void setCurrentPawn(Pawn pawn) {
		this.currentPawn = pawn;
		getTxtCurrentPlayer().setText("Player " + (pawns.find(currentPawn) + 1));
	}
	
	public void nextPlayer() {
		currentPawn = pawns.nextPawn(currentPawn);
		getTxtCurrentPlayer().setText("Player " + (pawns.find(currentPawn) + 1));
	}
	
	public boolean isChallenge() {
		return challenge;
	}
	
	public void setChallenge(boolean challenge) {
		this.challenge = challenge;
	}

	public AnchorPane getApTop() {
		//We use an AnchorPane to display the pause button and the timer
		if(apTop == null) {
			apTop = new AnchorPane(getSpTimer(), getImgPause());
			//we put the timer is on the top left
			AnchorPane.setTopAnchor(getSpTimer(), GraphicConstants.NORMAL_PADDING);
			AnchorPane.setLeftAnchor(getSpTimer(), GraphicConstants.NORMAL_PADDING);
			//we put the button Pause is on the top right
			AnchorPane.setTopAnchor(getImgPause(), GraphicConstants.NORMAL_PADDING);
			AnchorPane.setRightAnchor(getImgPause(), GraphicConstants.NORMAL_PADDING);
		}
		return apTop;
	}

	public StackPane getSpCenter() {
		//We use a StackPane to display the Board
		if(spCenter == null) {
			spCenter = new StackPane();
			//Creation of the image view
			Image imgBoard = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.BOARD_PATH));
			ImageView imgBoardView = new ImageView(imgBoard);
			//Determining the size of the image 
			imgBoardView.setFitHeight(GraphicConstants.BOARD_HEIGHT);
			imgBoardView.setPreserveRatio(true);

			//we add to the StackPane the board, the GridPane, the buttons to chose the difficulty, the question, the textFiel to answer and the button pause.
			spCenter.getChildren().addAll(imgBoardView, getGpCenter(), getVbDifficulty(), getSpQuestion(), getSpSpecialCard(), getVbWinTop(), getVbWinBottom(), getVbPause());
		}
		return spCenter;
	}

	public AnchorPane getApBottom() {
		//We use an AnchorPane to display the component that will be below the board
		if(apBottom == null) {
			apBottom = new AnchorPane();
			//Creation of the image view
			Image imgHelp = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.HELP_PATH));
			ImageView imgHelpView = new ImageView(imgHelp);
			//Determining the size of the image
			imgHelpView.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgHelpView.setPreserveRatio(true);
			//Adding the elements to the AnchorPane and positioning them
			apBottom.getChildren().addAll(getTxtCurrentPlayer(), getImgHelp(), getTxtAnswer());
			AnchorPane.setBottomAnchor(getTxtCurrentPlayer(), 10.);
			AnchorPane.setLeftAnchor(getTxtCurrentPlayer(), 10.);
			AnchorPane.setBottomAnchor(getImgHelp(), 10.);
			AnchorPane.setRightAnchor(getImgHelp(), 10.);
			
			AnchorPane.setBottomAnchor(getTxtAnswer(), 20.);
			AnchorPane.setRightAnchor(getTxtAnswer(), 350.);
		}
		return apBottom;
	}

	public GridPane getGpCenter() {
		//We use a GridPane each cell of the gridpane represents one case of the board
		if(gpCenter == null) {
			gpCenter = new GridPane();
			//Determining the width of the cells of the GridPane
			int nbCols = 8;
			for(int i = 0; i < nbCols; i++) {
				ColumnConstraints constraint = new ColumnConstraints();
				constraint.setPercentWidth(100./nbCols);
				gpCenter.getColumnConstraints().add(constraint);
			}
			//Determining the height of the cells of the GridPane
			int nbRows = 6;
			for(int i = 0; i < nbRows; i++) {
				RowConstraints constraint = new RowConstraints();
				constraint.setPercentHeight(100./nbRows);
				gpCenter.getRowConstraints().add(constraint);
			}
			//Determining the dimension of the gridpane
			gpCenter.setMaxHeight(GraphicConstants.BOARD_HEIGHT - 80);
			gpCenter.setMaxWidth(1193);
			gpCenter.setPadding(new Insets(0, 0, 22, 35));
		}
		return gpCenter;
	}

	public ImageView getImgTimer() {
		if(imgTimer == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.TIMER_PATH));
			imgTimer = new ImageView(img);
			//Determining the size of the image
			imgTimer.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgTimer.setPreserveRatio(true);
		}
		return imgTimer;
	}

	public ImageView getImgPause() {
		if(imgPause == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.PAUSE_PATH));
			imgPause = new ImageView(img);
			//Determining the size of the image
			imgPause.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgPause.setPreserveRatio(true);
			//Determining the behavior on the click
			imgPause.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				getVbPause().setVisible(true);
			});
		}
		return imgPause;
	}

	public ImageView getImgHelp() {
		if(imgHelp == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.HELP_PATH));
			imgHelp = new ImageView(img);
			//Determining the size of the image
			imgHelp.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgHelp.setPreserveRatio(true);
			//Determining the behavior on the click
			imgHelp.setOnMouseClicked((event)->{
				//We get the WindowMainSp
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				this.setVisible(false);
				main.getSpHelp().setVisible(true);
			});
		}
		return imgHelp;
	}

	public List<ImageView> getImgPawns() {
		if(imgPawns == null && pawns.getNbPawns() > 0) {
			imgPawns = new ArrayList<>();
			//For each pawn in the list
			for(int i=0; i<pawns.getPawns().size(); i++) {
				//Creation of the image view
				Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(pawns.find(i).getImgPath()));
				ImageView imgPawn = new ImageView(img);
				//Determining the size of the image
				imgPawn.setFitWidth(GraphicConstants.SMALL_IMG_HEIGHT);
				imgPawn.setPreserveRatio(true);
				//Adding the image created to the list
				imgPawns.add(imgPawn);
			}
		}
		return imgPawns;
	}
	
	public void setImgPawns() {
		if(pawns.getNbPawns() > 0) {
			//For each pawns in the list
			for(int i=0; i<pawns.getNbPawns(); i++) {
				//Creation of the image view
				Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(pawns.find(i).getImgPath()));
				ImageView imgPawn = new ImageView(img);
				//Determining the size of the image
				imgPawn.setFitWidth(GraphicConstants.SMALL_IMG_HEIGHT);
				imgPawn.setPreserveRatio(true);
				//Adding the image created to the list
				getImgPawns().add(imgPawn);
			}
		} else {
			//Removing all images in case the list of pawns is empty
			imgPawns = null;
			getGpCenter().getChildren().clear();
		}
	}

	public DifficultyChoiceVB getVbDifficulty() {
		if(vbDifficulty == null) {
			vbDifficulty = new DifficultyChoiceVB(currentCard);
			vbDifficulty.setVisible(false);
		}
		return vbDifficulty;
	}

	public QuestionSP getSpQuestion() {
		if(spQuestion == null) {
			spQuestion = new QuestionSP(deck.find(0).find(0));
			spQuestion.setVisible(false);
		}
		return spQuestion;
	}
	
	public SpecialCardDisplaySP getSpSpecialCard() {
		if(spSpecialCard == null) {
			spSpecialCard = new SpecialCardDisplaySP();
			spSpecialCard.setVisible(false);
		}
		return spSpecialCard;
	}

	public Text getTxtAnswer() {
		if(txtAnswer == null) {
			txtAnswer = new Text();
			txtAnswer.setVisible(false);
			txtAnswer.setFont(GraphicConstants.BIG_FONT);
			txtAnswer.setFill(Color.ANTIQUEWHITE);
			//Adding a shadow to the text
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtAnswer.setEffect(ds);
		}
		return txtAnswer;
	}
	
	public void displayCard() {
		if(currentPawn == null) {
			//Starting by the first player by default
			setCurrentPawn(pawns.getPawns().get(0));
		}
		//Getting the theme of the case
		Theme caseTheme = boardgame.getCases().get(currentPawn.getCasePawn() - 1).getTheme();
		//If the theme is a special one
		if(caseTheme.equals(Theme.CHALLENGE) || caseTheme.equals(Theme.NOTHING_WELL) || caseTheme.equals(Theme.WONDERFUL)) {
			displaySpecialCard(caseTheme);
		} else {
			try {
				setCurrentCard(deck.pickCard(caseTheme));
			} catch (ElementNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayDifficulty();
		}
	}

	public void displayDifficulty() {
		//Display the choice of difficulty and clearing the former answer that was given
		getVbDifficulty().setCard(currentCard);
		getVbDifficulty().setVisible(true);
		getSpQuestion().getTxtAnswer().clear();
	}

	public void displayQuestion(Question question) {
		//Display the card with the question
		getSpQuestion().setQuestion(question);
		getSpQuestion().setVisible(true);
	}
	
	public void displaySpecialCard(Theme theme) {
		//Display the special card with the text
		getSpSpecialCard().setTheme(theme);
		getSpSpecialCard().setVisible(true);
	}

	public PauseMenuVB getVbPause() {
		if(vbPause == null) {
			vbPause = new PauseMenuVB();
			vbPause.setVisible(false);
		}
		return vbPause;
	}

	public void displayPawn() {
		//To display the pawn
		if(pawns.getNbPawns() > 0) {
			//Getting the list of the images in case it is null
			getImgPawns();
			//For each pawn in the list
			for(int i=0; i<pawns.getNbPawns(); i++) {
				//Remove the pawn from its former cell in the gridpane
				getGpCenter().getChildren().remove(getImgPawns().get(i));
				//Adding the pawn the the new cell in the gridpane
				getGpCenter().add(getImgPawns().get(i), pawns.find(i).getColumn(), pawns.find(i).getRow());
				//Positioning the pawns in the cells accordingly to their number
				if(pawns.getNbPawns() == 1) {
					GridPane.setHalignment(getImgPawns().get(i), HPos.CENTER);
					GridPane.setValignment(getImgPawns().get(i), VPos.CENTER);
				} else if(pawns.getNbPawns() == 2) {
					if(i==0) {
						GridPane.setHalignment(getImgPawns().get(i), HPos.CENTER);
						GridPane.setValignment(getImgPawns().get(i), VPos.TOP);
					}else {
						GridPane.setHalignment(getImgPawns().get(i), HPos.CENTER);
						GridPane.setValignment(getImgPawns().get(i), VPos.BOTTOM);
					}
				} else if(pawns.getNbPawns() == 3) {
					if(i==0) {
						GridPane.setHalignment(getImgPawns().get(i), HPos.LEFT);
						GridPane.setValignment(getImgPawns().get(i), VPos.TOP);
					}else if (i==1) {
						GridPane.setHalignment(getImgPawns().get(i), HPos.CENTER);
						GridPane.setValignment(getImgPawns().get(i), VPos.TOP);
					} else {
						GridPane.setHalignment(getImgPawns().get(i), HPos.RIGHT);
						GridPane.setValignment(getImgPawns().get(i), VPos.TOP);
					}
				} else {
					if(i==0) {
						GridPane.setHalignment(getImgPawns().get(i), HPos.LEFT);
						GridPane.setValignment(getImgPawns().get(i), VPos.TOP);
					} else if(i==1) {
						GridPane.setHalignment(getImgPawns().get(i), HPos.RIGHT);
						GridPane.setValignment(getImgPawns().get(i), VPos.TOP);
					} else if(i==2) {
						GridPane.setHalignment(getImgPawns().get(i), HPos.LEFT);
						GridPane.setValignment(getImgPawns().get(i), VPos.BOTTOM);
					}else{
						GridPane.setHalignment(getImgPawns().get(i), HPos.RIGHT);
						GridPane.setValignment(getImgPawns().get(i), VPos.BOTTOM);
					}
				}
				
			}
		}
	}

	//Setter
	public void setCurrentCard(BasicCard card) {
		this.currentCard = card;
	}
	public void setLetsGo(LocalTime now) {
		this.letsGo = now;
	}

	public LocalTime getLetsGo() {
		if(letsGo == null) {
			letsGo = LocalTime.now();
		}
		return letsGo;
	}

	public StackPane getSpTimer() {
		//We create the timer
		if(spTimer == null) {
			spTimer = new StackPane(getImgTimer());
			Text txtTimer = new Text();
			txtTimer.setFont(GraphicConstants.SMALL_FONT);
			txtTimer.setFill(Color.ANTIQUEWHITE);
			//Creation of the timer using KeyFrame
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					txtTimer.setText(LocalTime.now().minusSeconds(getLetsGo().toSecondOfDay()).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				}
			});
			//Associating the keyframe created to the timeline
			Timeline timeline = new Timeline(keyFrame);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.playFromStart();
			spTimer.getChildren().add(txtTimer);
		}
		return spTimer;
	}

	//Code to back to the menu
	public ImageView getImgBackMenu() {
		if(imgBackMenu == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.BACKTOTHEMENU_PATH));
			imgBackMenu = new ImageView(img);
			//Determining the size of the image
			imgBackMenu.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgBackMenu.setPreserveRatio(true);
			//Determining the behavior on the click
			imgBackMenu.setOnMouseClicked((event)->{
				//We get the WindowMainSp
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				PawnGP pawnSelection = ((WindowMainSP)this.getParent()).getPawn();
				//Display the main menu and hide the current scene
				this.setVisible(false);
				main.getMainMenu().setVisible(true);
				//Remove all the pawns in the list of pawns
				getPawns().removeAll();
				setImgPawns();
				//We hide the current card
				getSpQuestion().setVisible(false);
				getSpQuestion().getTimer().stop();
				setCurrentPawn(null);
				//Creation of the lighting by default
				Lighting lighting = new Lighting();
				lighting.setDiffuseConstant(100.0);
				lighting.setLight(new Light.Distant(30, 30, null));
				//Applying the lighting by default to the images 
				pawnSelection.getImgBurger().setEffect(lighting);
				pawnSelection.getImgCap().setEffect(lighting);
				pawnSelection.getImgRubber().setEffect(lighting);
				pawnSelection.getImgPencilSharpener().setEffect(lighting);
				pawnSelection.getImgkey().setEffect(lighting);
				pawnSelection.getImgUsb().setEffect(lighting);
				//Hide the message of victory
				getVbWinTop().setVisible(false);
				getVbWinBottom().setVisible(false);
			});
		}
		return imgBackMenu;
	}

	public void displayWinner()
	{
		getSpQuestion().setVisible(false);
		getVbWinTop().setVisible(true);
		getVbWinBottom().setVisible(true);
	}

	public VBox getVbWinTop() {
		if(vbWinTop == null)
		{
			//Adding the elements to the VBox
			vbWinTop = new VBox(getTxtCongratulation());
			//Positioning the VBox and determining the spacing
			vbWinTop.setSpacing(GraphicConstants.NORMAL_SPACING);
			vbWinTop.setVisible(false);
			vbWinTop.setAlignment(Pos.TOP_CENTER);
		}
		return vbWinTop;
	}
	
	public VBox getVbWinBottom() {
		if(vbWinBottom == null)
		{
			//Adding the elements to the VBox
			vbWinBottom = new VBox(getTxtWinner(), getImgBackMenu());
			//Positioning the VBox and determining the spacing
			vbWinBottom.setSpacing(GraphicConstants.NORMAL_SPACING);
			vbWinBottom.setVisible(false);
			vbWinBottom.setAlignment(Pos.BOTTOM_CENTER);
		}
		return vbWinBottom;
	}

	public Text getTxtCongratulation() {
		if(txtCongratulation == null)
		{
			txtCongratulation = new Text("Congratulation");
			txtCongratulation.setFont(GraphicConstants.Victory_FONT);
			txtCongratulation.setFill(Color.ANTIQUEWHITE);
			//Creation of the shadow effect
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtCongratulation.setEffect(ds);
		}
		return txtCongratulation;
	}

	public Text getTxtWinner() {
		if(txtWinner == null)
		{
			txtWinner = new Text("Player ? won the game !");
			txtWinner.setFont(GraphicConstants.Victory_FONT);
			txtWinner.setFill(Color.ANTIQUEWHITE);
			//Creation of the shadow effect
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtWinner.setEffect(ds);
		}
		return txtWinner;
	}
	
	public Text getTxtCurrentPlayer() {
		if(txtCurrentPlayer == null) {
			txtCurrentPlayer = new Text("Player 1");
			txtCurrentPlayer.setFont(GraphicConstants.SMALL_FONT);
			txtCurrentPlayer.setFill(Color.ANTIQUEWHITE);
			//Creation of the shadow effect
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtCurrentPlayer.setEffect(ds);
		}
		return txtCurrentPlayer;
	}
	
	public HelpSP getSpHelp() {
		if(spHelp == null) {
			spHelp = new HelpSP();
		}
		return spHelp;
	}
	
	public Stage getStgHelp() {
		if(stgHelp == null) {
			stgHelp = new Stage();
			stgHelp.setTitle("Help");
			stgHelp.setScene(new Scene(new HelpSP()));
		}
		return stgHelp;
	}
}