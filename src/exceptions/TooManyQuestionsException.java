package exceptions;

public class TooManyQuestionsException extends Exception {
	public TooManyQuestionsException() {
		super("Too many questions for this card !");
	}
}
