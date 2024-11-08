package view;

import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface GraphicConstants {
	
	double SMALL_IMG_HEIGHT = 50.;
	double NORMAL_IMG_HEIGHT = 200.;
	double NORMAL_IMG_WIDTH = 100.;
	double BIG_IMG_WIDTH = 350.;
	
	
	double BOARD_HEIGHT = 750.;
	double CARD_HEIGHT = 700.;
	double SMALL_BUTTON_HEIGHT = 30.;
	double SMALL_BUTTON_WIDTH = 150.;
	
	
	double NORMAL_PADDING = 10.;
	double BIG_PADDING = 175.;
	Insets BOARD_GRIDPANE_PADDING = new Insets(30, 165, 60, 206);
	
	
	double SMALL_SPACING = 15.;
	double NORMAL_SPACING = 25.;
	double MEDIUM_SPACING = 50.;
	double BIG_SPACING = 100.;
	
	Font QUESTION_FONT = Font.font("comicsansms", FontWeight.BOLD, 20);
	Font TINY_FONT = new LemonFont(13).getFont();
	Font SMALL_FONT = new LemonFont(30).getFont();
	Font BIG_FONT = new LemonFont(50).getFont();
	Font Victory_FONT = new LemonFont(100).getFont();
	
	int VICTORY = 4;
}
