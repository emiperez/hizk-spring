package com.emiperez.hizk.service;

import java.util.List;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.Term;

public interface ExamService {

	Exam startExam(Exam exam);

	List<Term> checkAnswers(Integer examId, List<Term> answers);

	List<String> findQuestionCorrectAnswers(Integer examId, Integer questionId);
	
}
