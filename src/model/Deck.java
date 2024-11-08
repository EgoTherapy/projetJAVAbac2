package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import exceptions.DoubleException;
import exceptions.ElementNotFoundException;
import exceptions.NotEnoughQuestionsException;
import exceptions.NullException;

public class Deck {
	private List<BasicCard> cards;
	
	//Constructor
	/**
	 * A deck is made up of a cards list.
	 */
	public Deck() {
		cards = new ArrayList<>();
	}

	//Getters
	/**
	 * Return the list of cards in the deck
	 * 
	 * @return a copy of the cards list
	 */
	public List<BasicCard> getCards() {
		List<BasicCard> tmp = new ArrayList<>();
		for(BasicCard card : cards) {
			tmp.add(card.clone());
		}
		return tmp;
	}
	
	//Setter
	/**
	 * Sets a list of cards to the deck
	 * 
	 * @param cards a list of cards to set to the deck
	 */
	private void setCards(List<BasicCard> cards) {
		this.cards.clear();
		for(BasicCard card : cards) {
			this.cards.add(card.clone());
		}
	}
	
	/**
	 * This method will return the size of the deck.
	 * 
	 * @return the size of the deck
	 */
	public int getNbCards() {
		return cards.size();
	}
	
	/**
	 * This method will add the card that we put in the parameters.
	 * 
	 * @param card the card to add to the deck
	 * @return true if the addition worked or false if it didn't work
	 * @throws DoubleException will be thrown if the card that we want to add is already in the deck
	 * @throws NullException will be thrown if the card is null
	 * @throws NotEnoughQuestionsException will be thrown if there are not enough questions
	 */
	public boolean add(BasicCard card) throws DoubleException, NullException, NotEnoughQuestionsException{
		if(card == null) {
			throw new NullException();
		}
		if(cards.contains(card)) {
			throw new DoubleException();
		}
		if(card.getQuestions().size() != 4) {
			throw new NotEnoughQuestionsException();
		}
		return cards.add(card.clone());
	}
	
	/**
	 * This method will research the card that the user want in function of the index.
	 * 
	 * @param index the index to find the card
	 * @return the copy of the card that we want to find
	 */
	public BasicCard find(int index) {
		return cards.get(index).clone();
	}
	
	/**
	 * This method will research the card that the user want in function of an object (card).
	 * 
	 * @param card the index of the wanted card
	 * @return the card that we want to find
	 */
	
	public int find(BasicCard card) {
		return cards.indexOf(card);
	}
	
	/**
	 * This method will remove the card that the user want to remove.
	 * 
	 * @param card the card that we want to remove
	 * @return true if the card has been correctly removed and false if not
	 */
	public boolean remove(BasicCard card) {
		return cards.remove(card);
	}
	
	/**
	 * Pick a random card in the list with the theme passed in argument
	 * 
	 * @param theme the theme of the card we want to pick
	 * @return a random card with the theme passed in argument
	 * @throws ElementNotFoundException if no card was found
	 */
	public BasicCard pickCard(Theme theme) throws ElementNotFoundException{
		if(theme != null && (theme.equals(Theme.IMPROBABLE) || theme.equals(Theme.PLEASURE) || theme.equals(Theme.SCHOOL) || theme.equals(Theme.INFORMATICS))) {
			List<BasicCard> themeCards = new ArrayList<>();
			for(BasicCard card : cards) {
				if(card.getTheme().equals(theme)) {
					themeCards.add(card);
				}
			}
			if(themeCards.size() > 0) {
				Random rand = new Random();
				int index = rand.nextInt(themeCards.size());
				//When we have a bigger deck. Remove that card from the deck.
				return themeCards.get(index).clone();
			} 
		}
		throw new ElementNotFoundException();
	}
	
	@Override
	public String toString() {
		return "Deck [cards=" + cards + "]";
	}
	
	//Clone
	/**
	 * Return a copy of the deck
	 * 
	 * @return a copy of the deck
	 */
	public Deck clone() {
		Deck tmp = new Deck();
		tmp.setCards(this.getCards());
		return tmp;
	}

	/**
	 * Return a string representing the object in json format
	 * 
	 * @return the object in json format
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	/**
	 * Converts the string passed in arguments in the object represented by it
	 * 
	 * @param json the string of the object in json format
	 * @return the object represented by the string passed in argument
	 */
	public static Deck fromJson(String json) {
		return new Gson().fromJson(json, Deck.class);
	}
}
