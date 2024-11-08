package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MenuAdminVB extends VBox {
	private HBox hbTop;
	private GridPane gpLogin;
	private HBox hbButton;
	
	private Text txtTitle;
	
	private Label lblLogin;
	private Label lblPassword;
	private Text txtError;
	
	private TextField txtLogin;
	private PasswordField pwdPassword;
	
	private ImageView imgLogin;
	private ImageView imgBack;
	
	public MenuAdminVB() {
		//Positioning and setting the padding and spacing + adding the elements
		this.setPadding(new Insets(GraphicConstants.BIG_PADDING));
		this.setSpacing(GraphicConstants.NORMAL_SPACING);
		this.getChildren().addAll(getHbTop(), getGpLogin(), getTxtError(), getHbButton());
		this.setAlignment(Pos.CENTER);
	}
	
	public HBox getHbTop() {
		if(hbTop == null) {
			//Adding the elements to the HBox
			hbTop = new HBox(getTxtTitle());
			//Positioning the HBox
			hbTop.setPadding(new Insets(GraphicConstants.NORMAL_PADDING));
			hbTop.setAlignment(Pos.CENTER);
		}
		return hbTop;
	}
	
	public GridPane getGpLogin() {
		if(gpLogin == null) {
			gpLogin = new GridPane();
			//Positioning the elements in the GridPane
			gpLogin.add(getLblLogin(), 0, 0);
			gpLogin.add(getTxtLogin(), 1, 0);
			gpLogin.add(getLblPassword(), 0, 1);
			gpLogin.add(getPwdPassword(), 1, 1);
			//Positioning the gridpane
			gpLogin.setAlignment(Pos.CENTER);
			gpLogin.setVgap(GraphicConstants.NORMAL_SPACING);
			gpLogin.setPadding(new Insets(GraphicConstants.NORMAL_PADDING));
		}
		return gpLogin;
	}
	
	public HBox getHbButton() {
		if(hbButton == null) {
			//Adding all the elements to the HBox
			hbButton = new HBox(getImgBack(), getImgLogin());
			//Positioning the HBox
			hbButton.setAlignment(Pos.CENTER);
			hbButton.setSpacing(GraphicConstants.NORMAL_SPACING);
		}
		return hbButton;
	}
	
	public Text getTxtTitle() {
		if(txtTitle == null) {
			txtTitle = new Text("Login to your admin account");
			txtTitle.setFont(GraphicConstants.BIG_FONT);
			txtTitle.setFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtTitle.setEffect(ds);
		}
		return txtTitle;
	}

	public Label getLblLogin() {
		if(lblLogin==null) {
			lblLogin=new Label("Login : ");
			lblLogin.setFont(GraphicConstants.SMALL_FONT);
			lblLogin.setTextFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblLogin.setEffect(ds);
		}
		return lblLogin;
	}

	public Label getLblPassword() {
		if(lblPassword==null) {
			lblPassword=new Label("Password : ");
			lblPassword.setFont(GraphicConstants.SMALL_FONT);
			lblPassword.setTextFill(Color.ANTIQUEWHITE);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			lblPassword.setEffect(ds);
		}
		return lblPassword;
	}
	
	public Text getTxtError() {
		if(txtError == null) {
			txtError = new Text("");
			txtError.setFont(GraphicConstants.SMALL_FONT);
			txtError.setFill(Color.RED);
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			txtError.setEffect(ds);
		}
		return txtError;
	}

	public TextField getTxtLogin() {
		if(txtLogin==null) {
			txtLogin=new TextField();
			txtLogin.setPrefWidth(250.);
		}
		return txtLogin;
	}

	public PasswordField getPwdPassword() {
		if(pwdPassword==null) {
			pwdPassword=new PasswordField();
		}
		return pwdPassword;
	}

	public ImageView getImgLogin() {
		if(imgLogin==null) {
			//Creating the image
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.LOGIN_PATH));
			imgLogin = new ImageView(img);
			//Determining the size of the image
			imgLogin.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgLogin.setPreserveRatio(true);
			//Determining the behavior on the click
			imgLogin.setOnMouseClicked((event)->{
				//We get the WindowMainSp
				WindowMainSP main = (WindowMainSP)this.getParent();
				//Launch the sound of the button
				main.getMdpButton().play();
				//Check if the values are correct
				if(getTxtLogin().getText().equals("admin") && getPwdPassword().getText().equals("helha")) {
					//Display the CardManagementMenuVB scene and hide the current scene
					MenuAdminVB.this.setVisible(false);
					((WindowMainSP)MenuAdminVB.this.getParent()).getVbCardMenu().setVisible(true);
					clearAll();
				} else {
					getTxtError().setText("The login and/or the password was incorrect");
				}
			});
		}
		return imgLogin;
	}
	
	public ImageView getImgBack() {
		if(imgBack == null) {
			//Creating the image
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.BACK_PATH));
			imgBack = new ImageView(img);
			//Determining the size of the image
			imgBack.setFitHeight(GraphicConstants.SMALL_IMG_HEIGHT);
			imgBack.setPreserveRatio(true);
			//Determining the behavior on the click
			imgBack.setOnMouseClicked((event)->{
				//Launch the sound of the button
				((WindowMainSP)this.getParent()).getMdpButton().play();
				//Display the MainMenu scene and hide the current scene
				MenuAdminVB.this.setVisible(false);
				((WindowMainSP)MenuAdminVB.this.getParent()).getMainMenu().setVisible(true);
				clearAll();
			});
		}
		return imgBack;
	}
	
	public void clearAll() {
		getTxtLogin().clear();
		getPwdPassword().clear();
		getTxtError().setText("");
	}
}
