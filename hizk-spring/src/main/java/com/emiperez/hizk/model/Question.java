package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Exam exam;
	
	@OneToOne
	@JoinColumn(name="question_text_id")
	private Text question;
	
	@OneToOne
	@JoinColumn(name="answer_text_id")
	private Text answer;
	
	public Question() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Text getQuestion() {
		return question;
	}

	public void setQuestion(Text question) {
		this.question = question;
	}

	public Text getAnswer() {
		return answer;
	}

	public void setAnswer(Text answer) {
		this.answer = answer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
