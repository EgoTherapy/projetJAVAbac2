package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.BasicCard;
import model.Deck;
import model.Theme;
import util.ReadWriteFile;

public class BasicCardSupressionVB extends VBox {
	private Deck deck;
	
	private Text txtTitle;
	private TableView<BasicCard> tvCards;
	private ImageView imgDelete;
	private ImageView imgBack;
	
	public BasicCardSupressionVB(Deck deck) {
		this.deck = deck;
		//Creation of the HBox for the buttons at the bottom
		HBox hbBottom = new HBox(getImgBack(), getImgDelete());
		//Positioning the HBox for the buttons and determining the spacing
		hbBottom.setSpacing(GraphicConstants.NORMAL_SPACING);
		hbBottom.setAlignment(Pos.BASELINE_RIGHT);
		//Adding the elements to the HBox
		this.getChildren().addAll(getTxtTitle(), getTvCards(), hbBottom);
		//Positioning the HBox and determining the spacing and padding
		this.setSpacing(GraphicConstants.NORMAL_SPACING);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(GraphicConstants.BIG_PADDING));
	}

	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("Delete a card");
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtTitle.setEffect(ds);
		}
		return txtTitle;
	}

	public TableView<BasicCard> getTvCards() {
		if(tvCards == null) {
			//Creation of the table view
			tvCards = new TableView<>();
			//Allowing the table view to adapt its size to the space at its disposal
			tvCards.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			//Allowing multiple selection in the table view
			tvCards.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			//Creation of the columns of the table 
			TableColumn<BasicCard, String> colAuthor = new TableColumn<>("Author");
			TableColumn<BasicCard, String> colSubject = new TableColumn<>("Subject");
			TableColumn<BasicCard, Theme> colTheme = new TableColumn<>("Theme");
			//Adding the columns to the table
			tvCards.getColumns().addAll(colAuthor, colSubject, colTheme);
			//Determining the values in the columns
			colAuthor.setCellValueFactory(new PropertyValueFactory<BasicCard, String>("author"));
			colSubject.setCellValueFactory(new PropertyValueFactory<BasicCard, String>("subject"));
			colTheme.setCellValueFactory(new PropertyValueFactory<BasicCard, Theme>("theme"));
			//Setting the cards that should be displayed in the table
			tvCards.setItems(FXCollections.observableArrayList(deck.getCards()));
		}
		return tvCards;
	}

	public ImageView getImgDelete() {
		if(imgDelete == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.DELETE_PATH));
			imgDelete = new ImageView(img);
			//Determining the size of the image
			imgDelete.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgDelete.setPreserveRatio(true);
			//Determining the behavior on the click
			imgDelete.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Getting all the items that was selected in the table
				ObservableList<BasicCard> tmp = getTvCards().getSelectionModel().getSelectedItems();
				//Removing the items selected from the deck
				for(BasicCard card : tmp) {
					deck.remove(card);
				}
				//Removing the items selected from the table
				getTvCards().getItems().removeAll(tmp);
				//Rewriting the json file containing the deck
				ReadWriteFile.write(FilePath.DECK_JSON_PATH, deck.toJson());
			});
		}
		return imgDelete;
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
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Display the MenuMain scene and hide the current scene
				BasicCardSupressionVB.this.setVisible(false);
				((WindowMainSP)BasicCardSupressionVB.this.getParent()).getVbCardMenu().setVisible(true);
			});
		}
		return imgBack;
	}
}
