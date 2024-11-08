package model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Boardgame {
	private List<Case> cases;
	
	//Constructor
	/**
	 * The board game is made up of a cases list 
	 */
	public Boardgame() {
		cases = new ArrayList<>();
	}
	
	//Getter
	/**
	 * Return the list of cases of the board
	 * 
	 * @return a copy of the cases list
	 */
	public List<Case> getCases() {
		List<Case> tmp = new ArrayList<>();
		for(Case c : cases) {
			tmp.add(c.clone());
		}
		return tmp;
	}
	
	/**
	 * Add the case passed in argument to the list of cases
	 * 
	 * @param c the case to add to the list
	 * @return true if the case was added or false otherwise
	 */
	public boolean add(Case c) {
		return cases.add(c.clone());
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
	public static Boardgame fromJson(String json) {
		return new Gson().fromJson(json, Boardgame.class);
	}
}
