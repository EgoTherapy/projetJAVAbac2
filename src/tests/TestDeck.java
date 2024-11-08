package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DoubleException;
import exceptions.ElementNotFoundException;
import exceptions.NotEnoughQuestionsException;
import exceptions.NullException;
import model.BasicCard;
import model.Deck;
import model.Question;
import model.Theme;

class TestDeck {
	private Deck deck;
	private List<BasicCard> cards;
	private BasicCard card;
	private BasicCard card2 = new BasicCard("Author2", Theme.IMPROBABLE, "Subject2");
	private BasicCard card3 = new BasicCard("Author3", Theme.IMPROBABLE, "Subject3");
	
	@BeforeEach
	void setUp() throws Exception {
		deck = new Deck();
		Field field = deck.getClass().getDeclaredField("cards");
		field.setAccessible(true);
		cards = (List<BasicCard>)field.get(deck);
		card = new BasicCard("Author", Theme.IMPROBABLE, "Subject");
		card.add(new Question("Author", Theme.IMPROBABLE, "Subject", "C1", "A1"));
		card.add(new Question("Author", Theme.IMPROBABLE, "Subject", "C2", "A2"));
		card.add(new Question("Author", Theme.IMPROBABLE, "Subject", "C3", "A3"));
		card.add(new Question("Author", Theme.IMPROBABLE, "Subject", "C4", "A4"));
	}

	@AfterEach
	void tearDown() throws Exception {
		deck = null;
		cards = null;
		card = null;
	}

	@Test
	void testAdd() {
		//Check to add the card to the deck
		assertDoesNotThrow(()->deck.add(card), "No exceptions thrown");
		assertTrue(cards.contains(card), "The card is present in the deck");
		assertEquals(1, cards.size(), "The deck contains one card");
	}
	@Test
	void testAddDouble() {
		//Adding one card to the list
		cards.add(card);
		
		//Check with a double of the card that has been added
		assertThrows(DoubleException.class, ()->deck.add(card), "DoubleException thrown");
		assertTrue(cards.contains(card), "The card is still present in the deck");
		assertEquals(1, cards.size(), "The deck still contains one card");
	}
	
	@Test 
	void testAddNull() {
		//Check with a card null
		assertThrows(NullException.class, ()->deck.add(null), "NullException thrown");
		assertEquals(0, cards.size(), "No card in the deck");
	}
	
	@Test
	void testAddNotEnoughQuestion() {
		assertThrows(NotEnoughQuestionsException.class, ()->deck.add(card2), "NotEnoughQuestionsException thrown");
		assertEquals(0, cards.size(), "No card in the deck");
	}

	@Test
	void testFindInt() {
		//Adding the cards to the deck
		cards.addAll(Arrays.asList(card, card2, card3));
		
		//Check with correct values
		assertEquals(card, deck.find(0), "The card returned was the correct one");
		assertEquals(card2, deck.find(1), "The card returned was the correct one");
		assertEquals(card3, deck.find(2), "The card returned was the correct one");
	}
	
	@Test
	void testFindIntIndexOut() {
		//Adding the cards to the deck
		cards.addAll(Arrays.asList(card, card2, card3));
		
		//Check with incorrect indexes
		assertThrows(IndexOutOfBoundsException.class, ()->deck.find(-1), "IndexOutOfBoundsException thrown");
		assertThrows(IndexOutOfBoundsException.class, ()->deck.find(cards.size()), "IndexOutOfBoundsException thrown");
	}

	@Test
	void testFindBasicCard() throws ElementNotFoundException {
		//Adding the card to the deck
		cards.addAll(Arrays.asList(card, card2, card3));
		
		//Check with correct values
		assertEquals(0, deck.find(card), "The index returned was the correct one");
		assertEquals(1, deck.find(card2), "The index returned was the correct one");
		assertEquals(2, deck.find(card3), "The index returned was the correct one");
		
		//Check with a card not present in the deck
		assertEquals(-1, deck.find(new BasicCard("AuthorError", Theme.INFORMATICS, "SubjectError")), "The index returned was -1");
		//Check with a card null
		assertEquals(-1, deck.find(null), "The index returned was -1");
	}

	@Test
	void testRemove() {
		//Creation of cards
		BasicCard card2 = new BasicCard("Author2", Theme.IMPROBABLE, "Subject2");
		BasicCard card3 = new BasicCard("Author3", Theme.IMPROBABLE, "Subject3");
		
		//Adding the card to the deck
		cards.addAll(Arrays.asList(card, card2, card3));
		
		//Check with a correct value
		assertTrue(deck.remove(card), "The method returned true");
		assertFalse(cards.contains(card), "The deck does not contain the card anymore");
		assertEquals(2, cards.size(), "The deck now contains 2 cards");
		
		//Check with a card not present in the deck
		assertFalse(deck.remove(card), "The method returned false");
		assertEquals(2, cards.size(), "The deck still contains 2 cards");
		
		//Check with a card null
		assertFalse(deck.remove(null), "The method returned false");
		assertEquals(2, cards.size(), "The deck still contains 2 cards");
	}

	@Test
	void testPickCard() throws ElementNotFoundException {
		BasicCard cardPicked = null;
		BasicCard card4 = new BasicCard("Author4", Theme.PLEASURE, "Subject4");
		//Adding the card to the deck
		cards.addAll(Arrays.asList(card, card2, card3, card4));
		
		//Check with correct values
		cardPicked = deck.pickCard(Theme.IMPROBABLE);
		assertEquals(Theme.IMPROBABLE, cardPicked.getTheme(), "The theme of the card picked matches the theme sent in argument");
		assertTrue(cardPicked.equals(card) || cardPicked.equals(card3), "The card matches with the possible ones");
		
		cardPicked = deck.pickCard(Theme.PLEASURE);
		assertEquals(Theme.PLEASURE, cardPicked.getTheme(), "The theme of the card picked matches the theme sent in argument");
		assertEquals(cardPicked, card4, "The card matches with the possible one");
	}
	
	@Test
	void testPickCardElementNotFound() {
		//Check with a Theme not present in the deck
		assertThrows(ElementNotFoundException.class, ()->deck.pickCard(Theme.INFORMATICS), "ElementNotFoundException thrown");
		//Checking with a Theme null
		assertThrows(ElementNotFoundException.class, ()->deck.pickCard(null), "ElementNotFoundException thrown");
	}
}
