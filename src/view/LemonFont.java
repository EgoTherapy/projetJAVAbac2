package view;

import javafx.scene.text.Font;

public class LemonFont {
	private Font font;
	private double size;
	
	public LemonFont(double size) {
		this.font = Font.loadFont(this.getClass().getClassLoader().getResourceAsStream("fonts/Lemon-Regular.ttf"), size);
	}
	
	public Font getFont() {
		return font;
	}
}
