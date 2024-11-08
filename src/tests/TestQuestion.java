package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Question;
import model.Theme;

class TestQuestion {
	private Question question;
	
	@BeforeEach
	void setUp() throws Exception {
		question = new Question("Author", Theme.IMPROBABLE, "Subject", "Challenge", "Answer");
	}

	@AfterEach
	void tearDown() throws Exception {
		question = null;
	}

	@Test
	void testCheckAnswer() {
		//Check with the correct answer
		assertTrue(question.checkAnswer("Answer"), "The method returned true");
		
		//Check with the correct answer but without the upper case
		assertTrue(question.checkAnswer("answer"), "The method returned true");
		
		//Check with a wrong answer
		assertFalse(question.checkAnswer("WrongAnswer"), "The method returned false");
	}
}
