package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DoubleException;
import exceptions.ElementNotFoundException;
import exceptions.NotCompatibleException;
import exceptions.NullException;
import exceptions.TooManyQuestionsException;
import model.BasicCard;
import model.Question;
import model.Theme;

class TestBasicCard {
	
	private BasicCard card;
	private List<Question> questions;
	
	private Question q1 = new Question("Author", Theme.IMPROBABLE, "Subject", "What time is it ?", "12H00");
	private Question q2 = new Question("JS", Theme.IMPROBABLE, "Subject", "What is the weather like ?", "Cloudy");
	private Question q3 = new Question("Author", Theme.INFORMATICS, "Subject", "How are you ?", "Fine");
	private Question q4 = new Question("Author", Theme.IMPROBABLE, "Age", "How old are you ?", "19");
	private Question q5 = new Question("Author", Theme.IMPROBABLE, "Subject", "What is the subject of this question", "Subject");
	private Question q6 = new Question("Author", Theme.IMPROBABLE, "Subject", "What is the name of the author of this question ?", "Author");
	private Question q7 = new Question("Author", Theme.IMPROBABLE, "Subject", "What is the theme of this question ?", "Improbable");
	private Question q8 = new Question("Author", Theme.IMPROBABLE, "Subject", "Will this question be added ?", "No");
	
	@BeforeEach
	void setUp() throws Exception {
		card = new BasicCard("Author", Theme.IMPROBABLE, "Subject");

		Field field = card.getClass().getDeclaredField("questions");
		field.setAccessible(true);
		questions = (List<Question>)field.get(card);
	}

	@AfterEach
	void tearDown() throws Exception {
		card = null;
		questions = null;
	}

	@Test
	void testAdd() {
		//Check if it works when it should
		assertDoesNotThrow(()->card.add(q1), "No exceptions throw");
		assertEquals(1, questions.size(), "The list contains one question");
		assertTrue(questions.contains(q1), "The list contains the question");
		
		//Check with 3 more different questions
		assertDoesNotThrow(()->card.add(q5));
		assertDoesNotThrow(()->card.add(q6));
		assertDoesNotThrow(()->card.add(q7));
		assertEquals(4, questions.size(), "The list contains 4 questions");
		assertTrue(questions.contains(q1), "The list still contains the first question");
		assertTrue(questions.contains(q5), "The list contains the question q5");
		assertTrue(questions.contains(q6), "The list contains the question q6");
		assertTrue(questions.contains(q7), "The list contains the question q7");
	}
	
	@Test
	void testAddNull() {
		//Check with a question null
		assertThrows(NullException.class, ()->card.add(null), "NullException thrown");
		assertEquals(0, questions.size(), "The list still contains one question");
	}
	
	@Test
	void testAddNotCompatible() {
		//Check with a different author than the card
		assertThrows(NotCompatibleException.class, ()->card.add(q2), "NotCompatibleException thrown");
		assertEquals(0, questions.size(), "The list still contains one question");
		
		//Check with a different theme than the card
		assertThrows(NotCompatibleException.class, ()->card.add(q3), "NotCompatibleException thrown");
		assertEquals(0, questions.size(), "The list still contains one question");
		
		//Check with a different subject than the card
		assertThrows(NotCompatibleException.class, ()->card.add(q4), "NotCompatibleException thrown");
		assertEquals(0, questions.size(), "The list still contains one question");
	}
	
	@Test
	void testAddDouble() {
		//Check with a double of the first card
		questions.add(q1);
		assertThrows(DoubleException.class, ()->card.add(q1), "DoubleException thrown");
		assertEquals(1, questions.size(), "The list still contains one question");
		assertTrue(questions.contains(q1), "The list still contains the first question");
	}
	
	@Test
	void testAddTooMany() {
		//Adding questions to the card
		questions.add(q1);
		questions.add(q5);
		questions.add(q6);
		questions.add(q7);
		//Check with a 5th question
		assertThrows(TooManyQuestionsException.class, ()->card.add(q8), "TooManyQuestionsException");
		assertEquals(4, questions.size(), "The list still contains 4 questions");
		assertFalse(questions.contains(q8), "The list does not contain q8");
	}

	@Test
	void testFindInt() {
		//Add 4 cards to the list of questions
		questions.add(q1);
		questions.add(q5);
		questions.add(q6);
		questions.add(q7);
		
		//Check with correct values
		assertEquals(q1, card.find(questions.indexOf(q1)), "The question returned is the first");
		assertEquals(q7, card.find(questions.indexOf(q7)), "The question returned is the last");
	}
	
	@Test
	void testFindIntIndexOutOfBound() {
		//Add 4 cards to the list of questions
		questions.add(q1);
		questions.add(q5);
		questions.add(q6);
		questions.add(q7);
		
		//Check with incorrect index
		assertThrows(IndexOutOfBoundsException.class, ()->card.find(-1), "IndexOutOfBoundsException thrown");
		assertThrows(IndexOutOfBoundsException.class, ()->card.find(questions.size()), "IndexOutOfBoundsException thrown");
	}

	@Test
	void testFindQuestion() throws ElementNotFoundException {
		//Add 4 cards to the list of questions
		questions.add(q1);
		questions.add(q5);
		questions.add(q6);
		questions.add(q7);
		
		//Check with correct values
		assertEquals(questions.indexOf(q1), card.find(q1), "The index returned is the correct one");
		assertEquals(questions.indexOf(q7), card.find(q7), "The index returned is the correct one");
		
		//Check with a question not present in the card
		assertEquals(-1, card.find(q8), "The index returned was -1");
		//Check with a card null
		assertEquals(-1, card.find(null), "The index returned was -1");
	}

	@Test
	void testRemove() {
		//Add 4 cards to the list of questions
		questions.add(q1);
		questions.add(q5);
		questions.add(q6);
		questions.add(q7);
		
		//Check with a correct value 
		assertTrue(card.remove(q1), "The method returned true");
		assertEquals(3, questions.size(), "The list contains now 3 questions");
		assertFalse(questions.contains(q1), "The question q1 is not in the list anymore");
		
		//Check with a null question (Should return false)
		assertFalse(card.remove(null), "The method returned false");
		assertEquals(3, questions.size(), "The list still contains 3 questions");
		
		//Check with a question not present in the list (Should return false)
		assertFalse(card.remove(q8), "The method returned false");
		assertEquals(3, questions.size(), "The list still contains 3 questions");
	}
}
