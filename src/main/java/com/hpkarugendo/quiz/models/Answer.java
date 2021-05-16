package com.hpkarugendo.quiz.models;

public class Answer {
	private char label;
	private String answer;
	private boolean correct;
	
	public Answer() {
	}
	public Answer(char label, String answer, boolean correct) {
		this.label = label;
		this.answer = answer;
		this.correct = correct;
	}
	public char getLabel() {
		return label;
	}
	public void setLabel(char label) {
		this.label = label;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean getCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	@Override
	public String toString() {
		return "Answer [label=" + label + ", answer=" + answer + ", correct=" + correct + "]";
	}
	
}
