package me.youtest.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

	private String id;
	private String text;
	private String type;
	private List<Answer> answers;
		
	public Question() {
		this.answers = new ArrayList<Answer>();
	}

	public Question(String id, String text) {
		this.id = id;
		this.text = text;
		this.answers = new ArrayList<Answer>();
	}
	
	public Question(String id, String text, List<Answer> answers) {
		this.id = id;
		this.text = text;
		this.answers = answers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addAnswers(List<Answer> answers) {
		this.answers.addAll(answers);
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", text=" + text + ", type=" + type + ", answers=" + answers + "]";
	}	
}
