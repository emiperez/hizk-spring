package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 * After an answer to an Exam question, 2 LearnTerm instances are created.
 * One for the question Term, and another one for the answer Term (if it exists).
 * These, with the isCorrect attribute are used to increase/decrease the probability
 * of these terms to appear in further Exams.
 */
@Entity
public class LearntTerm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	ExamTermId id;
	
	@ManyToOne
	@MapsId("examId")
	Exam exam;

	@ManyToOne
	@MapsId("termId")
	Term term;
	
	@Column
	boolean isCorrect;
	
	public LearntTerm() {
		this.id = new ExamTermId(null, null);
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
		id.setExamId(exam.getId());
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
		id.setTermId(term.getId());
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
		
}
