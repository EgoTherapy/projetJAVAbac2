package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HelpSP extends StackPane {
	private VBox vbContainer;
	private Text txtTitle;
	private Text txtExplication;
	private Text txtSpecialCards;
	private Text txtTechnicalCharacteristics;
	private Text txtInter;
	
	private ImageView imgBack;
	private ImageView imgBackground;
	
	public HelpSP() {
		//Positioning the StackPane
		this.setAlignment(Pos.CENTER);
		//Adding the element to the StackPane
		this.getChildren().addAll(getImgBackground(), getVbContainer());
	}
	
	public VBox getVbContainer() {
		if(vbContainer == null) {
			//Adding the elements to the VBox
			vbContainer = new VBox(getTxtTitle(), getTxtExplication(), getTxtSpecialCards(), getTxtInter(), getTxtTechnicalCharacteristics(), getImgBack());
			//Positioning the VBox and determining the spacing and padding
			vbContainer.setAlignment(Pos.CENTER);
			vbContainer.setSpacing(GraphicConstants.NORMAL_SPACING);
			vbContainer.setPadding(new Insets(GraphicConstants.NORMAL_PADDING));
		}
		return vbContainer;
	}
	
	public Text getTxtInter() {
		if(txtInter == null) {
			txtInter = new Text("_________________________________________________________");
			txtInter.setTextAlignment(TextAlignment.CENTER);
			txtInter.setFont(GraphicConstants.TINY_FONT);
			txtInter.setFill(Color.ANTIQUEWHITE);
    	}
		return txtInter;
	}

	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("Game Rules");
			txtTitle.setTextAlignment(TextAlignment.CENTER);
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
    	}
		return txtTitle;
	}
	
	public Text getTxtExplication() {
        if(txtExplication == null) {
            txtExplication = new Text("The object of the game is to walk around the board answering the questions asked. \r\n"
                    + "Estimate your level of 'knowledge', from 1 to 4, on one of the 24 subjects as serious as they are wacky. \r\n"
                    + "each level corresponds to a more or less difficult question \r\n"
                    + "on the theme of school, computers, pleasure or even improbable. \r\n"
                    + "\r\n"
                    + "If the answer is correct : \r\n"
                    + "The player will advance the number of squares corresponding to the difficulty level \r\n"
                    + "of the question he/she answered. \r\n"
                    + "\r\n"
                    + "In case of wrong answer : \r\n"
                    + "The player will stay on the same square until the next turn.");
            txtExplication.setTextAlignment(TextAlignment.CENTER);
            txtExplication.setFont(GraphicConstants.TINY_FONT);
            txtExplication.setFill(Color.ANTIQUEWHITE);
        }
        return txtExplication;
    }
	
	public Text getTxtSpecialCards() {
        if(txtSpecialCards == null) {
            txtSpecialCards = new Text("But beware !\r\n"
                    + "There are also special squares ! \r\n"
                    + "\r\n"
                    + "The 'Challenge' square : \r\n"
                    + "Once on this square, the player will be able to move forward twice \r\n"
                    + "the difficulty level of the question he answered ! \r\n"
                    + "However, impossible to know what the question will be about... \r\n"
                    + "The subject will be drawn completely at random. \r\n"
                    + "\r\n"
                    + "The square 'Nothing is going well' : \r\n"
                    + "Once on this square, the player will have the chance to move 1 to 4 spaces forward ! \r\n"
                    + "But you might not be lucky... \r\n"
                    + "In this case, the player can move back up to 4 squares as well. \r\n"
                    + "\r\n"
                    + "The 'Wonderful' square : \r\n"
                    + "Once on this square, the player will have the chance to move forward 1 to 4 square ! \r\n"
                    + "On the way to victory !!");
            txtSpecialCards.setTextAlignment(TextAlignment.CENTER);
            txtSpecialCards.setFont(GraphicConstants.TINY_FONT);
            txtSpecialCards.setFill(Color.ANTIQUEWHITE);
        }
        return txtSpecialCards;
    }

	public Text getTxtTechnicalCharacteristics() {
        if(txtTechnicalCharacteristics == null) {
            txtTechnicalCharacteristics = new Text("Technical characteristics: \r\n"
                    + "- Minimum age : 14 years old \r\n"
                    + "- Number of players : 1 to 4 players \r\n"
                    + "- Duration of a game : Between 30 minutes and 1 hour");
            txtTechnicalCharacteristics.setTextAlignment(TextAlignment.CENTER);
            txtTechnicalCharacteristics.setFont(GraphicConstants.TINY_FONT);
            txtTechnicalCharacteristics.setFill(Color.ANTIQUEWHITE);
        }
        return txtTechnicalCharacteristics;
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
				//We get the WindowMainSp
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				//Display the BoardBP and hide the current scene
				this.setVisible(false);
				main.getBoard().setVisible(true);
			});
		}
		return imgBack;
	}
	
	public ImageView getImgBackground() {
		if(imgBackground == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.RULES_BACKGROUND_PATH));
			imgBackground = new ImageView(img);
		}
		return imgBackground;
	}
	
	public void setImgBackgroundSize() {
		//Getting the WindowMainSP
		WindowMainSP main = (WindowMainSP)this.getParent();
		//Adapting the size to the screen base on the background from the WindowMainSP
		getImgBackground().fitHeightProperty().bind(main.getImgBackground().fitHeightProperty());
		getImgBackground().fitWidthProperty().bind(main.getImgBackground().fitWidthProperty());
	}
}
