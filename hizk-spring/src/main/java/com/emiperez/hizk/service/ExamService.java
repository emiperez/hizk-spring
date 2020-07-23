package com.emiperez.hizk.service;

import java.util.List;

import com.emiperez.hizk.model.Exam;

public interface ExamService {

	Exam startExam(Exam exam);

	List<String> findAnswers(Integer examId, Integer questionId);
	
}
