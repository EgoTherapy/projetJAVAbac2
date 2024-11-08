package model;

public enum Theme {
	IMPROBABLE("Improbable"),
	PLEASURE("Pleasure"),
	INFORMATICS("Informatics"),
	SCHOOL("School"),
	WONDERFUL("Wonderful"),
	CHALLENGE("Challenge"),
	NOTHING_WELL("Nothing is going well"),
	VICTORY("Victory");
	
	private String theme;
	
	//Constructor
	/**
	 * The theme is important to know wich card will be shown.
	 * 
	 * @param theme giving the theme
	 */
	private Theme(String theme) {
		this.theme = theme;
	}
	
	public String toString() {
		return theme;
	}
	
	public static Theme fromString(String str) {
		for(Theme t : Theme.values()) {
			if(t.toString().equals(str)) {
				return t;
			}
		}
		return null;
	}
}
