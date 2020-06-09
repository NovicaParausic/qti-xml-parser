package me.youtest.model;

public class Answer {

	private String id;
	private String text;
	private boolean correct;
	
	public Answer() {
		this.correct = false;
	}
	
	public Answer(String id, String text, boolean correct) {
		this.id = id;
		this.text = text;
		this.correct = correct;
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

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", text=" + text + ", correct=" + correct + "]";
	}
	
	
	
}
