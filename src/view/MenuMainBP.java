package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuMainBP extends BorderPane {
	private HBox hbTop;
	private HBox hbCenter;
		
	private ImageView imgPlay;
	private ImageView imgAdmin;
	private ImageView imgQuit;
	private ImageView imgSettings;

	public MenuMainBP() {
		//Adding the elements to the BorderPane
		this.setTop(getHbTop());
		this.setCenter(getHbCenter());
	}

	public HBox getHbTop() {
		if(hbTop == null) {
			//Creation of the image view of the logo
			Image imgLogo = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.LOGO_PATH));
			ImageView imgLogoView = new ImageView(imgLogo);
			//Determining the size of the image
			imgLogoView.setFitHeight(GraphicConstants.BIG_IMG_WIDTH);
			imgLogoView.setPreserveRatio(true);
			//Adding the image to the HBox
			hbTop = new HBox(imgLogoView);
			//Positioning the HBox and determining the padding
			hbTop.setPadding(new Insets(GraphicConstants.NORMAL_PADDING));
			hbTop.setAlignment(Pos.CENTER);	
		}
		return hbTop;
	}

	public HBox getHbCenter() {
		if(hbCenter == null) {
			//Adding the elements to the HBox
			hbCenter = new HBox(getImgPlay(), getImgAdmin(), getImgSettings(), getImgQuit());
			//Positioning the HBox and determining the spacing
			hbCenter.setSpacing(GraphicConstants.NORMAL_SPACING);
			hbCenter.setAlignment(Pos.CENTER);
		}
		return hbCenter;
	}
	
	public ImageView getImgPlay() {
		if(imgPlay == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.PLAYMENU_PATH));
			imgPlay = new ImageView(img);
			//Determining the size of the image
			imgPlay.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgPlay.setPreserveRatio(true);
			//Determining the behavior on the click
			imgPlay.setOnMouseClicked((event)->{
				//We get the WindowMainSp
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				//Display the scene for the choice of players
				this.setVisible(false);
				main.getTeam().setVisible(true);
			});
		}
		return imgPlay;
	}
	
	public ImageView getImgAdmin() {
		if(imgAdmin == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.ADMIN_PATH));
			imgAdmin = new ImageView(img);
			//Determining the size of the image
			imgAdmin.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgAdmin.setPreserveRatio(true);
			//Determining the behavior on the click
			imgAdmin.setOnMouseClicked((event)->{
				//We get the WindowMainSp
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				//Display the MenuAdminVB and hide the current scene
				this.setVisible(false);
				main.getAdminMenu().setVisible(true);
			});
		}
		return imgAdmin;
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
				((WindowMainSP)this.getParent()).getMdpButton().play();
				((Stage)imgQuit.getScene().getWindow()).close();
			});
		}
		return imgQuit;
	}

	public ImageView getImgSettings() {
		if(imgSettings == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.SETTINGS_PATH));
			imgSettings = new ImageView(img);
			//Determining the size of the image
			imgSettings.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgSettings.setPreserveRatio(true);
			//Determining the behavior on the click
			imgSettings.setOnMouseClicked((event)->{
				//We get the WindowMainSp
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				this.setVisible(false);
				main.getGpSetting().setVisible(true);
			});
		}
		return imgSettings;
	}
}