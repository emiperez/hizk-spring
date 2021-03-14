package com.emiperez.hizk.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.NonNull;

@Entity
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private LocalDateTime when = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 2, columnDefinition = "CHAR(2) DEFAULT 'B1'")
	@NonNull
	private Level level = Level.C2;

	@Column(nullable = false, length = 5)
	@NonNull
	private Locale questionLocale;

	@Column(nullable = false, length = 5)
	@NonNull
	private Locale answerLocale;

	@Column
	private Integer latest;

	@Column
	private boolean caseSensitive = true;

	@Column
	private int questionAmount = 10;

	@ManyToMany
	@JoinTable(name = "exam_questions", inverseJoinColumns = @JoinColumn(name = "term_id"))
	@JoinColumn(name = "exam_id")
	private List<Term> questions = new ArrayList<>();

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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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

	public Integer getLatest() {
		return latest;
	}

	public void setLatest(Integer latest) {
		this.latest = latest;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public int getQuestionAmount() {
		return questionAmount;
	}

	public void setQuestionAmount(int questionAmount) {
		this.questionAmount = questionAmount;
	}

	public List<Term> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Term> questions) {
		this.questions.clear();
		if (questions != null) {
			this.questions.addAll(questions);
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answerLocale == null) ? 0 : answerLocale.hashCode());
		result = prime * result + (caseSensitive ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + questionAmount;
		result = prime * result + ((questionLocale == null) ? 0 : questionLocale.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((when == null) ? 0 : when.hashCode());
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
		Exam other = (Exam) obj;
		if (answerLocale == null) {
			if (other.answerLocale != null)
				return false;
		} else if (!answerLocale.equals(other.answerLocale))
			return false;
		if (caseSensitive != other.caseSensitive)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questionAmount != other.questionAmount)
			return false;
		if (questionLocale == null) {
			if (other.questionLocale != null)
				return false;
		} else if (!questionLocale.equals(other.questionLocale))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (when == null) {
			if (other.when != null)
				return false;
		} else if (!when.equals(other.when))
			return false;
		return true;
	}

}
