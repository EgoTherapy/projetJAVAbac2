package exceptions;

public class ElementNotFoundException extends Exception{
	public ElementNotFoundException() {
		super("Element not found");
	}
}