package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Boardgame;
import model.Deck;
import model.PawnManagement;

public class WindowMainSP extends StackPane {
	private Deck deck;
	private PawnManagement pawns;
	private Boardgame boardgame;
	
	private ImageView imgBackground;
	private MenuMainBP mainMenu;
	private BoardBP board;
	private MenuAdminVB adminMenu;
	private PawnGP pawn;
	private BasicCardCreationVB vbBasicCardCreation;
	private TeamGP team;
	private CardManagementMenuVB vbCardMenu;
	private BasicCardSupressionVB vbBasicCardSupression;
	private HelpSP spHelp;
	private SettingGP gpSetting;

	private MediaPlayer mdpMusic;
	private MediaPlayer mdpButton;

	public WindowMainSP(Deck deck, PawnManagement pawns, Boardgame boardgame) {
		this.deck = deck;
		this.pawns = pawns;
		this.boardgame = boardgame;
		//Adding all the elements to the StackPane
		this.getChildren().addAll(getImgBackground(), getMainMenu(), getBoard(), getAdminMenu(), getPawn(), getTeam(), getVbCardMenu(), getVbBasicCardCreation(), getVbBasicCardSupression(), getSpHelp(), getGpSetting());
		//Setting the size of the image background of the HelpSP
		getSpHelp().setImgBackgroundSize();
		//Starting the music
		getMdpMusic().play();
	}
	
	public ImageView getImgBackground() {
		if(imgBackground == null) {
			//Creation of the image view
			Image img = new Image(this.getClass().getClassLoader().getResourceAsStream(FilePath.BACKGROUND_PATH));
			imgBackground = new ImageView(img);
			//Determining the size of the image
			imgBackground.fitHeightProperty().bind(this.heightProperty());
			imgBackground.fitWidthProperty().bind(this.widthProperty());
		}
		return imgBackground;
	}

	public MenuMainBP getMainMenu() {
		if(mainMenu == null) {
			mainMenu = new MenuMainBP();
		}
		return mainMenu;
	}

	public BoardBP getBoard() {
		if(board == null) {
			board = new BoardBP(deck, pawns, boardgame);
			board.setVisible(false);
		}
		return board;
	}

	public MenuAdminVB getAdminMenu() {
		if(adminMenu == null) {
			adminMenu = new MenuAdminVB();
			adminMenu.setVisible(false);
		}
		return adminMenu;
	}
	
	public BasicCardCreationVB getVbBasicCardCreation() {
		if(vbBasicCardCreation == null) {
			vbBasicCardCreation = new BasicCardCreationVB(deck);
			vbBasicCardCreation.setVisible(false);
		}
		return vbBasicCardCreation;
	}
	
	public CardManagementMenuVB getVbCardMenu() {
		if(vbCardMenu == null) {
			vbCardMenu = new CardManagementMenuVB(deck);
			vbCardMenu.setVisible(false);
		}
		return vbCardMenu;
	}
	
	public BasicCardSupressionVB getVbBasicCardSupression() {
		if(vbBasicCardSupression == null) {
			vbBasicCardSupression = new BasicCardSupressionVB(deck);
			vbBasicCardSupression.setVisible(false);
		}
		return vbBasicCardSupression;
	}
	
	public PawnGP getPawn() {
		if(pawn == null) {
			pawn = new PawnGP(pawns);
			pawn.setVisible(false);
		}
		return pawn;
	}
	
	public TeamGP getTeam() {
		if(team == null) {
			team = new TeamGP();
			team.setVisible(false);
		}
		return team;
	}
	
	public HelpSP getSpHelp() {
		if(spHelp==null)
		{
			spHelp = new HelpSP();
			spHelp.setVisible(false);
		}
		return spHelp;
	}
	
	public SettingGP getGpSetting() {
		if(gpSetting == null) {
			gpSetting = new SettingGP();
			gpSetting.setVisible(false);
		}
		return gpSetting;
	}
	
	public MediaPlayer getMdpMusic() {
		if(mdpMusic == null) {
			//Récupération de la musique
			Media buzzer = new Media(this.getClass().getResource("/sounds/Sound.wav").toExternalForm());
			//Association de la musique au MediaPlayer
			mdpMusic = new MediaPlayer(buzzer);
			//On met le volume un peu plus bas
			mdpMusic.setVolume(0.1);
			//On lance la musique
			//mdpMusic.play();
			mdpMusic.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					//Quand elle se termine on la remet à zéro et on la relance
					mdpMusic.seek(Duration.ZERO);
					mdpMusic.play();
				}
			});
		}
		return mdpMusic;
	}
	
	public MediaPlayer getMdpButton() {
		if(mdpButton == null) {
			//Récupération de la musique
			Media clickSound = new Media(this.getClass().getResource("/sounds/Button.wav").toExternalForm());
			//Association de la musique au MediaPlayer
			mdpButton = new MediaPlayer(clickSound);
			//On met le volume un peu plus bas
			mdpButton.setVolume(0.1);
			//On lance la musique
			//mdpButton.play();
			mdpButton.setOnEndOfMedia(new Runnable() {
		        @Override
		        public void run() {
		            mdpButton.stop();
		        }
		    });
		};
		return mdpButton;
	}
}
