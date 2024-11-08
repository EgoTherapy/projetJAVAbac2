package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SettingGP extends GridPane {
	private Text txtTitle;
	private Label lblSound;
	private Slider sldSound;
	private Label lblCheat;
	private ImageView imgBack;
	private CheckBox cbCheat;
	
	public SettingGP() {
		this.setPadding(new Insets(10));
		this.setGridLinesVisible(true);
		//Determining the width of the cells in the gridpane
		int nbCol=3;
		for(int i=0; i<nbCol; i++) {
			ColumnConstraints colConstr = new ColumnConstraints();
			colConstr.setPercentWidth(100./nbCol);
			this.getColumnConstraints().add(colConstr);
		}
		//Determining the height of the cells in the gridpane
		int nbRows = 4;
		for(int i = 0; i < nbRows; i++) {
			RowConstraints constraint = new RowConstraints();
			constraint.setPercentHeight(100./nbRows);
			this.getRowConstraints().add(constraint);
		}
		
		//Positioning all the elements in the gridpane
		this.add(getTxtTitle(), 1, 0);
		this.add(getLblSound(), 0, 1);
		this.add(getSldSound(), 1, 1);
		this.add(getLblCheat(), 0, 2);
		this.add(getCbCheat(), 1, 2);
		this.add(getImgBack(), 1, 3);
		
		//Positioning all the elements in the center of their cells
		for(Node node : this.getChildren()) {
			GridPane.setHalignment(node, HPos.CENTER);
		}
	}

	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("Settings");
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
			//Adding the shadow to the text
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtTitle.setEffect(ds);
		}
		return txtTitle;
	}

	public Label getLblSound() {
		if(lblSound == null) {
			lblSound = new Label("Volume of the sound : ");
			lblSound.setFont(GraphicConstants.SMALL_FONT);
			lblSound.setTextFill(Color.ANTIQUEWHITE);
			//Adding the shadow to the text
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblSound.setEffect(ds);
		}
		return lblSound;
	}

	public Slider getSldSound() {
		if(sldSound == null) {
			sldSound = new Slider(0., 1., 0.1);
			sldSound.valueProperty().addListener((observable, oldVal, newVal)->{
				((WindowMainSP)this.getParent()).getMdpMusic().setVolume(newVal.doubleValue());
			});
		}
		return sldSound;
	}
	
	public Label getLblCheat() {
		if(lblCheat == null) {
			lblCheat = new Label("Cheat mode");
			lblCheat.setFont(GraphicConstants.SMALL_FONT);
			lblCheat.setTextFill(Color.ANTIQUEWHITE);
			//Adding the shadow to the text
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblCheat.setEffect(ds);
		}
		return lblCheat;
	}
	
	public CheckBox getCbCheat() {
		if(cbCheat == null) {
			cbCheat = new CheckBox("Cheat");
		}
		return cbCheat;
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
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				//Display the BoardBP and hide the current scene
				this.setVisible(false);
				main.getMainMenu().setVisible(true);
			});
		}
		return imgBack;
	}

}
