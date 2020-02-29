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
	@JoinColumn(name="question_term_id", referencedColumnName = "origin_id")
	@JoinColumn(name="answer_term_id", referencedColumnName = "meaning_id")
	private Translation terms;
	
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

	public Translation getTerms() {
		return terms;
	}

	public void setTerms(Translation terms) {
		this.terms = terms;
	}
	
}
