package com.emiperez.hizk.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.spring.repository.ExamJpaRepository;
import com.emiperez.hizk.spring.repository.TermJpaRepository;

public class ExamServiceImpl implements ExamService {

	@Autowired
	private TermJpaRepository termRepository;
	
	@Autowired
	private ExamJpaRepository examRepository;

	@Override
	@Transactional
	public Exam startExam(Exam exam) {
		exam.setWhen(LocalDateTime.now());
		exam.setQuestions(termRepository.createQuestions(exam.getQuestionLocale(), exam.getNumberOfQuestions()));
		exam = examRepository.save(exam);
		return exam;
	}
	
	@Override
	@Transactional
	public boolean checkAnswer(Term answer) {
		return false;
	}

}
