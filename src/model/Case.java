package model;

public class Case {
	private Theme theme;
	
	//Constructor
	/**
	 * A case is linked to a theme to know which type of card will be shown.
	 * 
	 * @param theme the theme of the case
	 */
	public Case(Theme theme) {
		this.theme = theme;
	}

	//Getter
	/**
	 * Return the theme of the case
	 * 
	 * @return the theme of the case
	 */
	public Theme getTheme() {
		return theme;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (theme != other.theme)
			return false;
		return true;
	}

	//Clone
	/**
	 * Return a copy of this object
	 * 
	 * @return a copy of this object
	 */
	public Case clone() {
		return new Case(theme);
	}
}
