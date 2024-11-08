package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.BasicCard;

public class DifficultyChoiceVB extends VBox {
	private BasicCard card;
	
	private Text txtSubject;
	private HBox hbDifficulty;
	
	private ImageView imgDifficulty1;
	private ImageView imgDifficulty2;
	private ImageView imgDifficulty3;
	private ImageView imgDifficulty4;
	
	public DifficultyChoiceVB(BasicCard card) {
		this.card = card;
		//Adding the elements to the VBox
		this.getChildren().addAll(getTxtSubject(), getHbDifficulty());
		//Positioning the VBox and determining the spacing
//		this.setAlignment(Pos.CENTER);
//		this.setSpacing(GraphicConstants.MEDIUM_SPACING);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setSpacing(GraphicConstants.SMALL_SPACING);
	}
	
	public void setCard(BasicCard card) {
		this.card = card;
		//Adapt the subject to the one of the card
		getTxtSubject().setText("Subject : " + card.getSubject());
	}

	public Text getTxtSubject() {
		if(txtSubject == null) {
			txtSubject = new Text("Subject : " + card.getSubject());
			txtSubject.setFont(GraphicConstants.BIG_FONT);
			txtSubject.setFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtSubject.setEffect(ds);
		}
		return txtSubject;
	}

	public HBox getHbDifficulty() {
		if(hbDifficulty == null) {
			//Adding all the elements to the HBox
			hbDifficulty = new HBox(getImgDifficulty1(), getImgDifficulty2(), getImgDifficulty3(), getImgDifficulty4());
			//Positioning the HBox and determing the spacing
			hbDifficulty.setAlignment(Pos.CENTER);
			hbDifficulty.setSpacing(GraphicConstants.NORMAL_SPACING);
		}
		return hbDifficulty;
	}

	public ImageView getImgDifficulty1() {
		if(imgDifficulty1 == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.DIFFICULTY1_PATH));
			imgDifficulty1 = new ImageView(img);
			//Determining the size of the image
			imgDifficulty1.setFitWidth(GraphicConstants.NORMAL_IMG_WIDTH);
			imgDifficulty1.setPreserveRatio(true);
			//Determining the behavior on the click
			imgDifficulty1.setOnMouseClicked((event)->selectDifficulty(0));
		}
		return imgDifficulty1;
	}

	public ImageView getImgDifficulty2() {
		if(imgDifficulty2 == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.DIFFICULTY2_PATH));
			imgDifficulty2 = new ImageView(img);
			//Determining the size of the image
			imgDifficulty2.setFitWidth(GraphicConstants.NORMAL_IMG_WIDTH);
			imgDifficulty2.setPreserveRatio(true);
			//Determining the behavior on the click
			imgDifficulty2.setOnMouseClicked((event)->selectDifficulty(1));
		}
		return imgDifficulty2;
	}

	public ImageView getImgDifficulty3() {
		if(imgDifficulty3 == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.DIFFICULTY3_PATH));
			imgDifficulty3 = new ImageView(img);
			//Determining the size of the image
			imgDifficulty3.setFitWidth(GraphicConstants.NORMAL_IMG_WIDTH);
			imgDifficulty3.setPreserveRatio(true);
			//Determining the behavior on the click
			imgDifficulty3.setOnMouseClicked((event)->selectDifficulty(2));
		}
		return imgDifficulty3;
	}

	public ImageView getImgDifficulty4() {
		if(imgDifficulty4 == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.DIFFICULTY4_PATH));
			imgDifficulty4 = new ImageView(img);
			//Determining the size of the image
			imgDifficulty4.setFitWidth(GraphicConstants.NORMAL_IMG_WIDTH);
			imgDifficulty4.setPreserveRatio(true);
			//Determining the behavior on the click
			imgDifficulty4.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					//Hide the difficulty choice window and display the question
					DifficultyChoiceVB.this.setVisible(false);
					((BoardBP)DifficultyChoiceVB.this.getParent().getParent()).displayQuestion(card.find(3));
				}
			});
			imgDifficulty4.setOnMouseClicked((event)->selectDifficulty(3));
		}
		return imgDifficulty4;
	}
	
	public void selectDifficulty(int difficulty) {
		//Launch the sound of the button
		((WindowMainSP)this.getParent().getParent().getParent()).getMdpButton().play();
		//Hidding the current scene
		this.setVisible(false);
		//Displaying the question and giving the question chosen
		((BoardBP)this.getParent().getParent()).displayQuestion(card.find(difficulty));
	}
}
