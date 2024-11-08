package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TeamGP extends GridPane{
	private ImageView imgPlayer1;
	private ImageView imgPlayer2;
	private ImageView imgPlayer3;
	private ImageView imgPlayer4;
	
	private Text txtTitle;
	
	private MediaPlayer mdpButton;
	
	public TeamGP() {
		this.setPadding(new Insets(10));
		
		//Determining the width of the cells in the gridpane
		int nbCol=2;
		for(int i=0; i<nbCol; i++) {
			ColumnConstraints colConstr = new ColumnConstraints();
			colConstr.setPercentWidth(100./nbCol);
			this.getColumnConstraints().add(colConstr);
		}
		//Determining the height of the cells in the gridpane
		int nbRows = 3;
		for(int i = 0; i < nbRows; i++) {
			RowConstraints constraint = new RowConstraints();
			constraint.setPercentHeight(100./nbRows);
			this.getRowConstraints().add(constraint);
		}
		//Positioning all the elements to the gridpane
		this.add(getImgPlayer1(), 0, 1);
		this.add(getImgPlayer2(), 1, 1);
		this.add(getImgPlayer3(), 0, 2);
		this.add(getImgPlayer4(), 1, 2);
		this.add(getTxtTitle(), 0,	0, 2, 1);
		//Positioning all the elements in the center of their cells
		for(Node node : this.getChildren()) {
			GridPane.setHalignment(node, HPos.CENTER);
		}
	}

	public ImageView getImgPlayer1() {
		if(imgPlayer1 == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.PLAYER1_PATH));
			imgPlayer1 = new ImageView(img);
			//Determining the size of the image
			imgPlayer1.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgPlayer1.setPreserveRatio(true);
			//Determining the behavior on the click
			imgPlayer1.setOnMouseClicked((event)->selectNumber(1));
		}
		return imgPlayer1;
	}

	public ImageView getImgPlayer2() {
		if(imgPlayer2 == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.PLAYER2_PATH));
			imgPlayer2 = new ImageView(img);
			//Determining the size of the image
			imgPlayer2.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgPlayer2.setPreserveRatio(true);
			//Determining the behavior on the click
			imgPlayer2.setOnMouseClicked((event)->selectNumber(2));
		}
		return imgPlayer2;
	}

	public ImageView getImgPlayer3() {
		if(imgPlayer3 == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.PLAYER3_PATH));
			imgPlayer3 = new ImageView(img);
			//Determining the size of the image
			imgPlayer3.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgPlayer3.setPreserveRatio(true);
			//Determining the behavior on the click
			imgPlayer3.setOnMouseClicked((event)->selectNumber(3));
		}
		return imgPlayer3;
	}

	public ImageView getImgPlayer4() {
		if(imgPlayer4 == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.PLAYER4_PATH));
			imgPlayer4 = new ImageView(img);
			//Determining the size of the image
			imgPlayer4.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgPlayer4.setPreserveRatio(true);
			//Determining the behavior on the cick
			imgPlayer4.setOnMouseClicked((event)->selectNumber(4));
		}
		return imgPlayer4;
	}
	
	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("How many players will play?");
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtTitle.setEffect(ds);
		}
		return txtTitle;
	}
	
	public void selectNumber(int number) {
		//We get the WindowMainSp
		WindowMainSP main = (WindowMainSP)this.getParent();
		//Launch the sound of the button
		main.getMdpButton().play();
		//Display the selection of pawns and hide the selection of the number of players
		this.setVisible(false);
		main.getPawn().setVisible(true);
		//Set the number of player for the selection of pawns
		main.getPawn().setNbPlayers(number);
	}
	
}
