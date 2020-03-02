package com.emiperez.hizk.service;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.Term;

public interface ExamService {

	Exam startExam(Exam exam);
	
	/**
	 * 
	 * @param answer Term object with the id of the question and the locale
	 * and text of the answer.
	 * @return true if exists a translation with that text for that id
	 * otherwise, false.
	 */
	boolean checkAnswer(Term answer);
	
}
