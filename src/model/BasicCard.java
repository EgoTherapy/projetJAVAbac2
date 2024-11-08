package model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import exceptions.DoubleException;
import exceptions.NotCompatibleException;
import exceptions.NullException;
import exceptions.TooManyQuestionsException;

public class BasicCard{
	
	//Variable declaration
	private String author, subject;
	private Theme theme;
	private List<Question> questions;
	
	//Constructor
	/**
	 * A basic card is made up of an author, a theme and a subject
	 * 
	 * @param author author of the card
	 * @param theme theme of the card
	 * @param subject subject of the card
	 */
	public BasicCard(String author, Theme theme, String subject) {
		this.author = author;
		this.theme = theme;
		this.subject = subject;
		questions = new ArrayList<>();
	}
	
	//Getters
	/**  
	 * Return the list of questions
	 * 
	 * @return copy of the list of questions
	 */
	public List<Question> getQuestions() {
		List<Question> tmp = new ArrayList<>();
		for(Question question : questions) {
			tmp.add(question.clone());
		}
		return tmp;
	}
	
	/**
	 * Return the author of the card
	 * 
	 * @return the author of the card
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Return the subject of the card
	 * 
	 * @return the subject of the card
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Return the theme of the card
	 * 
	 * @return the theme of the card
	 */
	public Theme getTheme() {
		return theme;
	}
	
	//Setters
	/**
	 * Will add a question to the questions list.
	 * 
	 * @param q the list of questions to set to the card
	 */
	private void setQuestions(List<Question> q) {
		for(Question question : q) {
			try {
				this.add(question);
			} catch (DoubleException | NullException | TooManyQuestionsException | NotCompatibleException e) {
				e.printStackTrace();
			}
		}
	}
		
	//Method Add. 
	/**
	 * This method will add the question that we put in the parameter.
	 * 
	 * @param question question to add to the list
	 * @return true if the addition work or false if it didn't work
	 * @throws DoubleException will be thrown if the question that we want to add is already in the list
	 * @throws NullException will be thrown if the question is null
	 * @throws TooManyQuestionsException will be thrown if there are too many questions
	 * @throws NotCompatibleException will be thrown if one of the element of the question is not compatible
	 */
	public boolean add(Question question) throws DoubleException, NullException, TooManyQuestionsException, NotCompatibleException{
		if(question == null) {
			throw new NullException();
		}
		if(questions.contains(question)) {
			throw new DoubleException();
		}
		if(questions.size() >= 4) {
			throw new TooManyQuestionsException();
		}
		if(!question.getSubject().equals(subject) || !question.getAuthor().equals(author) || !question.getTheme().equals(theme)) {
			throw new NotCompatibleException();
		}
		return questions.add(question.clone());
	}
	
	/**
	 * Return the question at the index passed in argument
	 * 
	 * @param index the index of the question
	 * @return the copy of the question at the index passed in argument
	 */
	public Question find(int index) {
		return questions.get(index).clone();
	}
	
	/**
	 * Return the index of the question passed in argument
	 * 
	 * @param q the question that we want to find
	 * @return index of the question passed in argument
	 */
	public int find(Question q) {
		return questions.indexOf(q);
	}
	
	/**
	 * This method will remove the question passed in argument
	 * 
	 * @param q the question that we want to remove
	 * @return true if the question has been removed and false if not
	 */
	public boolean remove(Question q) {
		return questions.remove(q);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		BasicCard other = (BasicCard) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (theme != other.theme)
			return false;
		return true;
	}

	//Clone
	/**
	 * Will return a copy of the basic card.
	 * 
	 * @return a copy of this object
	 */
	public BasicCard clone() {
		BasicCard result = new BasicCard(author, theme, subject);
		result.setQuestions(questions);
		return result;
	}
	
	@Override
	public String toString() {
		return "BasicCard [author=" + author + ", subject=" + subject + ", theme=" + theme + ", questions=" + questions
				+ "]";
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
	public static BasicCard fromJson(String json) {
		return new Gson().fromJson(json, BasicCard.class);
	}
}
