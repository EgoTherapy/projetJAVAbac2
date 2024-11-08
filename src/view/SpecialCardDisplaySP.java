package view;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Pawn;
import model.PawnManagement;
import model.Theme;

public class SpecialCardDisplaySP extends StackPane {
	private Theme theme;
	private int bonus;
	
	private ImageView imgCard;
	private VBox vbContent;
	
	private Text txtInfos;
	private Button btnOk;
	
	public SpecialCardDisplaySP() {
		//Adding all the elements to the StackPane
		this.getChildren().addAll(getImgCard(), getVbContent());
	}
	
	public void setTheme(Theme theme) {
		this.theme = theme;
		//Adapt the image of the card to the theme
		setImgCard(theme);
		if(theme.equals(Theme.CHALLENGE)) {
			getTxtInfos().setText("Go twice further !");
		} else if(theme.equals(Theme.WONDERFUL)) {
			bonus = 1 + new Random().nextInt(4);
			getTxtInfos().setText("Go " + bonus + " case(s) further !");
		} else {
			bonus = -(1 + new Random().nextInt(4));
			getTxtInfos().setText("Go " + -bonus + " case(s) back !");
		}
	}

	public ImageView getImgCard() {
		if(imgCard == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.CHALLENGE_PATH));
			imgCard = new ImageView(img);
			//Determining the size of the image
			imgCard.setFitHeight(GraphicConstants.CARD_HEIGHT);
			imgCard.setPreserveRatio(true);
		}
		return imgCard;
	}
	
	private void setImgCard(Theme theme) {
		if(imgCard != null) {
			//Select the good image for the theme 
			Image img;
			if(theme.equals(Theme.CHALLENGE)) {
				img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.CHALLENGE_PATH));
			} else if(theme.equals(Theme.NOTHING_WELL)) {
				img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.NOTHING_WELL_PATH));
			} else {
				img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.WONDERFUL_PATH));
			}
			//Display the good image
			getImgCard().setImage(img);
		}
	}

	public VBox getVbContent() {
		if(vbContent == null) {
			//Adding all the elements to the VBox
			vbContent = new VBox(getTxtInfos(), getBtnOk());
			//Positioning the VBox and determining the spacing
			vbContent.setAlignment(Pos.CENTER);
			vbContent.setSpacing(GraphicConstants.NORMAL_SPACING);
		}
		return vbContent;
	}

	public Text getTxtInfos() {
		if(txtInfos == null) {
			txtInfos = new Text("");
			txtInfos.setFont(GraphicConstants.QUESTION_FONT);
			//Setting a width for the text and positioning it
			txtInfos.setWrappingWidth(350);
			txtInfos.setTextAlignment(TextAlignment.CENTER);
		}
		return txtInfos;
	}

	public Button getBtnOk() {
		if(btnOk == null) {
			btnOk = new Button("Ok");
			//Determining the size of the button
			btnOk.setPrefSize(GraphicConstants.SMALL_BUTTON_WIDTH, GraphicConstants.SMALL_BUTTON_HEIGHT);
			//Determining the behavior on the click
			btnOk.setOnAction((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Getting the board and hiding the current scene
				BoardBP board = ((BoardBP)SpecialCardDisplaySP.this.getParent().getParent());
				this.setVisible(false);
				//Check if the theme is challenge or not
				if(!theme.equals(Theme.CHALLENGE)) {
					//Getting the current pawn
					Pawn pawn = board.getCurrentPawn();
					PawnManagement pawns = board.getPawns();
					//Moving the pawn from the bonus or the malus
					board.getPawns().movePawn(pawn, bonus);
					//Display the pawns to their new positions
					board.displayPawn();
					//Check if the player has won
					if(pawns.find(pawns.find(pawn)).getCasePawn() == GraphicConstants.VICTORY)
					{
						//Modifying the Victory text to know which player won
						board.getTxtWinner().setText(board.getTxtWinner().getText().replace('?', Character.forDigit(board.getPawns().find(board.getCurrentPawn())+1, 10)));
						board.displayWinner();
					} else {
						//Passing on to the next pawn, displaying the next card
						board.nextPlayer();
						board.displayCard();
					}
				} else {
					//Choosing a random card in the deck
					Random rand = new Random();
					board.setCurrentCard(board.getDeck().find(rand.nextInt(board.getDeck().getNbCards())));
					//Set the variable challenge to true and display the difficulty for the question that was chosen
					board.setChallenge(true);
					board.displayDifficulty();
				}
			});
		}
		return btnOk;
	}
	
}
