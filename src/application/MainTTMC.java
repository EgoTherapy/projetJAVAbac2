package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BasicCard;
import model.Boardgame;
import model.Case;
import model.Deck;
import model.Pawn;
import model.PawnManagement;
import model.Question;
import model.Theme;
import util.ReadWriteFile;
import view.BoardBP;
import view.FilePath;
import view.MenuMainBP;
import view.PawnGP;
import view.WindowMainSP;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MainTTMC extends Application {
	MediaPlayer mdpMusic;
	@Override
	public void start(Stage primaryStage) {
		//Creation of the Boardgame object
		Boardgame boardgame = Boardgame.fromJson(ReadWriteFile.read(FilePath.BOARDGAME_JSON_PATH));
		
		//Creation of a deck with one card
		Deck deck = Deck.fromJson(ReadWriteFile.read(FilePath.DECK_JSON_PATH));
		
		//Creation of the pawn management
		PawnManagement pawns = new PawnManagement();
		
		try {
			Scene scene = new Scene(new WindowMainSP(deck, pawns, boardgame));
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			primaryStage.show();
			
			//Récupération de la musique
			Media buzzer = new Media(getClass().getResource("/sounds/Sound.wav").toExternalForm());
			//Association de la musique au MediaPlayer
			mdpMusic = new MediaPlayer(buzzer);
			//On met le volume un peu plus bas
			mdpMusic.setVolume(0);
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}