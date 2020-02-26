package com.emiperez.hizk.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.NonNull;

@Entity
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private LocalDateTime when = LocalDateTime.now();

	@Column(nullable = false, length = 5)
	@NonNull
	private Locale questionLocale;

	@Column(nullable = false, length = 5)
	@NonNull
	private Locale answerLocale;

	@Column
	private boolean caseSensitive = true;

	@Column
	private int numberOfQuestions = 10;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> questions = new ArrayList<>();

	public Exam() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getWhen() {
		return when;
	}

	public void setWhen(LocalDateTime when) {
		this.when = when;
	}

	public Locale getQuestionLocale() {
		return questionLocale;
	}

	public void setQuestionLocale(Locale questionLocale) {
		this.questionLocale = questionLocale;
	}

	public Locale getAnswerLocale() {
		return answerLocale;
	}

	public void setAnswerLocale(Locale answerLocale) {
		this.answerLocale = answerLocale;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
