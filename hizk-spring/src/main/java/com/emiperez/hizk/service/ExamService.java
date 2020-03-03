package com.emiperez.hizk.service;

import com.emiperez.hizk.model.Exam;

public interface ExamService {

	Exam startExam(Exam exam);
	
	/**
	 * @param exam to know whether the answer is case sensitive or not.
	 * @param answer Term object with the id of the question and the locale
	 * and text of the answer.
	 * @return true if exists a translation with that text for that id
	 * otherwise, false.
	 */
	boolean checkAnswer(Integer examId, Integer questionId, String answerText);
	
}
