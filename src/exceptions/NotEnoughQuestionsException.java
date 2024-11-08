package exceptions;

public class NotEnoughQuestionsException extends Exception {
	public NotEnoughQuestionsException() {
		super("Not enough questions");
	}
}
