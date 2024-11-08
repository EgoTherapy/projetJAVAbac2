package view;

import java.time.LocalTime;

import exceptions.DoubleException;
import exceptions.NullException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Pawn;
import model.PawnManagement;

public class PawnGP extends GridPane {
	private ImageView imgBurger;
	private ImageView imgCap;
	private ImageView imgKey;
	private ImageView imgPencilSharpener;
	private ImageView imgRubber;
	private ImageView imgUsb;
	
	private int nbPlayers;
	
	private PawnManagement pawns;
	
	private Text txtTitle;
	
	public PawnGP(PawnManagement pawns) {
		this.pawns=pawns;
		this.setPadding(new Insets(10));
		
		//Determining the width of the cells in the gridpane
		int nbCol=3;
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
		//Positioning all the elements in the gridpane
		this.add(getImgBurger(), 0, 1);
		this.add(getImgCap(), 1, 1);
		this.add(getImgkey(), 0, 2);
		this.add(getImgPencilSharpener(), 1, 2);
		this.add(getImgRubber(), 2, 1);
		this.add(getImgUsb(), 2, 2);
		this.add(getTxtTitle(), 0,	0, 3, 1);
		
		//Positioning all the elements in the center of their cells
		for(Node node : this.getChildren()) {
			GridPane.setHalignment(node, HPos.CENTER);
		}
	}

	public ImageView getImgBurger() {
		if(imgBurger == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.BURGER_PATH));
			imgBurger = new ImageView(img);
			//Determining the size of the image
			imgBurger.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgBurger.setPreserveRatio(true);
			//Determining the behavior on the click
			imgBurger.setOnMouseClicked((event)->selectPawn(imgBurger, FilePath.BURGER_PATH));
		}
		return imgBurger;
	}

	public ImageView getImgCap() {
		if(imgCap == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.CAP_PATH));
			imgCap = new ImageView(img);
			//Determining the size of the image
			imgCap.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgCap.setPreserveRatio(true);
			//Determining the behavior on the click
			imgCap.setOnMouseClicked((event)->selectPawn(imgCap, FilePath.CAP_PATH));
		}
		return imgCap;
	}

	public ImageView getImgkey() {
		if(imgKey == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.KEY_PATH));
			imgKey = new ImageView(img);
			//Determining the size of the image
			imgKey.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgKey.setPreserveRatio(true);
			//Determining the behavior on the click
			imgKey.setOnMouseClicked((event)->selectPawn(imgKey, FilePath.KEY_PATH));
		}
		return imgKey;
	}

	public ImageView getImgPencilSharpener() {
		if(imgPencilSharpener == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.PENCIL_SHARPENER_PATH));
			imgPencilSharpener = new ImageView(img);
			//Determining the size of the image
			imgPencilSharpener.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgPencilSharpener.setPreserveRatio(true);
			//Determining the behavior on the click
			imgPencilSharpener.setOnMouseClicked((event)->selectPawn(imgPencilSharpener, FilePath.PENCIL_SHARPENER_PATH));
		}
		return imgPencilSharpener;
	}

	public ImageView getImgRubber() {
		if(imgRubber == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.RUBBER_PATH));
			imgRubber = new ImageView(img);
			//Determining the size of the image
			imgRubber.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgRubber.setPreserveRatio(true);
			//Determining the behavior on the click
			imgRubber.setOnMouseClicked((event)->selectPawn(imgRubber, FilePath.RUBBER_PATH));
		}
		return imgRubber;
	}

	public ImageView getImgUsb() {
		if(imgUsb == null) {
			//Creating the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.USB_PATH));
			imgUsb = new ImageView(img);
			//Determining the size of the image
			imgUsb.setFitHeight(GraphicConstants.NORMAL_IMG_HEIGHT);
			imgUsb.setPreserveRatio(true);
			//Determining the behavior on the click
			imgUsb.setOnMouseClicked((event)->selectPawn(imgUsb, FilePath.USB_PATH));
		}
		return imgUsb;
	}

	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("Please select a pawn");
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtTitle.setEffect(ds);
		}
		return txtTitle;
	}

	public void setNbPlayers(int nbPlayers) {
		this.nbPlayers = nbPlayers;
	}

	public void selectPawn(ImageView img, String path) {
		//Putting the image in dark when it is clicked
		Lighting lighting = new Lighting();
		lighting.setDiffuseConstant(100.0);
		lighting.setLight(new Light.Distant(30, 30, Color.BLACK));
		img.setEffect(lighting);
		//Creating and adding the pawn to the list
		Pawn p= new Pawn(path);
		try {
			pawns.add(p);
		} catch (NullException | DoubleException e) {
			e.printStackTrace();
		}
		//When we reach the right number of pawns we display the board and hide the selection of pawns
		if(nbPlayers == pawns.getNbPawns()) {
			//We get the WindowMainSp and the board
			WindowMainSP main = (WindowMainSP)this.getParent();
			BoardBP board = main.getBoard();
			//Display the board and hide the selection of pawns
			this.setVisible(false);
			board.setVisible(true);
			//Display the starting element of the board
			board.displayPawn();
			board.displayCard();
			board.setImgPawns();
			//Starting the chrono of the game
			board.setLetsGo(LocalTime.now());
		}
	}
}
