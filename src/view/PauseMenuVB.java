package view;

import javafx.geometry.Pos;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PauseMenuVB extends VBox {
	private ImageView imgResume;
	private ImageView imgBackMenu;
	private ImageView imgQuit;
	
	public PauseMenuVB() {
		//Adding all the elements to the VBox
		this.getChildren().addAll(getImgResume(), getImgBackMenu(), getImgQuit());
		//Positioning the VBox and determining the spacing
		this.setAlignment(Pos.CENTER);
		this.setSpacing(GraphicConstants.MEDIUM_SPACING);
	}
	
	public ImageView getImgResume() {
		if(imgResume == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.RESUME_PATH));
			imgResume = new ImageView(img);
			//Determining the size of the image
			imgResume.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgResume.setPreserveRatio(true);
			//Determining the behavior on the click
			imgResume.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent().getParent().getParent()).getMdpButton().play();
				PauseMenuVB.this.setVisible(false);
			});
		}
		return imgResume;
	}
	
	public ImageView getImgBackMenu() {
		if(imgBackMenu == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.MAIN_MENU_PATH));
			imgBackMenu = new ImageView(img);
			//Determining the size of the image
			imgBackMenu.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgBackMenu.setPreserveRatio(true);
			//Determining the behavior on the click
			imgBackMenu.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent().getParent().getParent()).getMdpButton().play();
				//Getting the board and the selection of pawns scenes
				BoardBP board = (BoardBP)PauseMenuVB.this.getParent().getParent();
				PawnGP pawnSelection = (PawnGP)((WindowMainSP)PauseMenuVB.this.getParent().getParent().getParent()).getPawn();
				//Display the main menu and hide the current scene
				this.setVisible(false);
				board.setVisible(false);
				((WindowMainSP)board.getParent()).getMainMenu().setVisible(true);
				//Remove all the pawns in the list of pawns
				board.getPawns().removeAll();
				board.setImgPawns();
				//We hide the current card
				board.getSpQuestion().setVisible(false);
				board.getSpQuestion().getTimer().stop();
				board.setCurrentPawn(null);
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
				board.getTxtCongratulation().setVisible(false);
				board.getTxtWinner().setVisible(false);
				board.getImgBackMenu().setVisible(false);
			});
		}
		return imgBackMenu;
	}
		
	public ImageView getImgQuit() {
		if(imgQuit == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.QUIT_PATH));
			imgQuit = new ImageView(img);
			//Determining the size of the image
			imgQuit.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgQuit.setPreserveRatio(true);
			//Determining the behavior on the click
			imgQuit.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent().getParent().getParent()).getMdpButton().play();
				((Stage)imgQuit.getScene().getWindow()).close();
			});
		}
		return imgQuit;
	}
}