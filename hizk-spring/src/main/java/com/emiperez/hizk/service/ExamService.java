package com.emiperez.hizk.service;

import java.util.List;

import com.emiperez.hizk.model.Exam;

public interface ExamService {

	Exam startExam(Exam exam);
	
	/**
	 * @param exam to know whether the answer is case sensitive or not.
	 * @param termId of the question.
	 * @return list of correct answers.
	 */
	List<String> findAnswers(Integer examId, Integer questionId);
	
}
