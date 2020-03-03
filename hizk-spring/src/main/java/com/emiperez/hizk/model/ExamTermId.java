package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamTermId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "exam_id")
	private Integer examId;
	
	@Column(name = "term_id")
	private Integer termId;

	public ExamTermId() {
	}
	
	public ExamTermId(Integer examId, Integer termId) {
		this.examId = examId;
		this.termId = termId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getTermId() {
		return termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + examId;
		result = prime * result + termId;
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
		ExamTermId other = (ExamTermId) obj;
		if (examId != other.examId)
			return false;
		if (termId != other.termId)
			return false;
		return true;
	}

}
