package model;

import com.google.gson.Gson;

public class Question {
	private String author, subject, challenge, answer;
	private Theme theme;
	
	//Constructor
	/**
	 * A question is made up of an author, a theme, a subject, the question and the answer.
	 * 
	 * @param author the informations of the author who made question
	 * @param theme the theme of the question
	 * @param subject the subject of the question
	 * @param challenge the question
	 * @param answer the answer of the question
	 */
	public Question(String author, Theme theme, String subject, String challenge, String answer) {
		this.author = author;
		this.theme = theme;
		this.subject = subject;
		this.challenge = challenge;
		this.answer = answer;
	}
	
	//Getters
	/**
	 * Return the author of the question
	 * 
	 * @return the author of the question
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Return the subject of the question
	 * 
	 * @return the subject of the question
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Return the question of the object
	 * 
	 * @return the question of the object
	 */
	public String getChallenge() {
		return challenge;
	}

	/**
	 * Return the answer of the question
	 * 
	 * @return the answer of the question
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Return the theme of the question
	 * 
	 * @return the theme of the question
	 */
	public Theme getTheme() {
		return theme;
	}

	//Setters
	/**
	 * Will set the text for the subject of the question.
	 * 
	 * @param subject the given subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Will set the text for the author of the question.
	 * 
	 * @param author the author of the question
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * Will set the asking text of the question.
	 * 
	 * @param challenge the challenge of the question
	 */
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	
	/**
	 * Will set the text for the answer of the question.
	 * 
	 * @param answer the answer of the question
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Will set the text for the theme of the question.
	 * 
	 * @param theme the theme of the question
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	/**
	 * Will check if the answer gave is correct.
	 * 
	 * @param answer the answer gave by the user
	 * @return true if the answer is correct and false if not
	 */
	public boolean checkAnswer(String answer) {
		if(answer.toUpperCase().equals(this.answer.toUpperCase())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
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
		Question other = (Question) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
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
	 * Will return a copy of a question
	 */
	public Question clone() {
		return new Question(author, theme, subject, challenge, answer);
	}
	
	@Override
	public String toString() {
		return "Question [author=" + author + ", subject=" + subject + ", challenge=" + challenge + ", answer=" + answer
				+ ", theme=" + theme + "]";
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
	public static Question fromJson(String json) {
		return new Gson().fromJson(json, Question.class);
	}
}
