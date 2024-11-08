package view;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Deck;

public class CardManagementMenuVB extends VBox {
	private Text txtTitle;
	private ImageView imgAddCard;
	private ImageView imgRemoveCard;
	private ImageView imgBack;
	
	public CardManagementMenuVB(Deck deck) {
		//Add all the elements to the VBox
		this.getChildren().addAll(getTxtTitle(), getImgAddCard(), getImgRemoveCard(), getImgBack());
		//Setting the spacing and centering the VBox
		this.setSpacing(GraphicConstants.NORMAL_SPACING);
		this.setAlignment(Pos.CENTER);
	}

	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("Card management");
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtTitle.setEffect(ds);
		}
		return txtTitle;
	}

	public ImageView getImgAddCard() {
		if(imgAddCard == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.ADD_CARD_PATH));
			imgAddCard = new ImageView(img);
			//Determining the size of the image
			imgAddCard.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgAddCard.setPreserveRatio(true);
			//Determining the behavior on the click
			imgAddCard.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Display the BasicCardCreation scene and hide the current scene
				CardManagementMenuVB.this.setVisible(false);
				((WindowMainSP)CardManagementMenuVB.this.getParent()).getVbBasicCardCreation().setVisible(true);
			});
		}
		return imgAddCard;
	}

	public ImageView getImgRemoveCard() {
		if(imgRemoveCard == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.REMOVE_CARD_PATH));
			imgRemoveCard = new ImageView(img);
			//Determining the size of the image
			imgRemoveCard.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgRemoveCard.setPreserveRatio(true);
			//Determining the behavior on the click
			imgRemoveCard.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Display the BasicCardSupression scene and hide the current scene
				CardManagementMenuVB.this.setVisible(false);
				((WindowMainSP)CardManagementMenuVB.this.getParent()).getVbBasicCardSupression().setVisible(true);
			});
		}
		return imgRemoveCard;
	}
	
	public ImageView getImgBack() {
		if(imgBack == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.MAIN_MENU_PATH));
			imgBack = new ImageView(img);
			//Determining the size of the image
			imgBack.setFitWidth(GraphicConstants.BIG_IMG_WIDTH);
			imgBack.setPreserveRatio(true);
			//Determining the behavior on the click
			imgBack.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Display the MainMenu scene and hide the current scene
				CardManagementMenuVB.this.setVisible(false);
				((WindowMainSP)CardManagementMenuVB.this.getParent()).getMainMenu().setVisible(true);
			});
		}
		return imgBack;
	}
}
