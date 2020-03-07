package com.emiperez.hizk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.LearntTerm;
import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.spring.repository.ExamJpaRepository;
import com.emiperez.hizk.spring.repository.LearntTermJpaRepository;
import com.emiperez.hizk.spring.repository.TermJpaRepository;
import com.emiperez.hizk.spring.repository.TranslationJpaRepository;

public class ExamServiceImpl implements ExamService {

	@Autowired
	private TermJpaRepository termRepository;

	@Autowired
	private TranslationJpaRepository translationRepository;
	
	@Autowired
	private ExamJpaRepository examRepository;
	
	@Autowired
	private LearntTermJpaRepository learntTermRepository;

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
	public List<String> findAnswers(Integer examId, Integer questionId) {
		List<String> answers = translationRepository.findAnswersByExamAndQuestion(examId, questionId);
		LearntTerm learntQuestion = new LearntTerm();
		Exam exam = examRepository.getOne(examId);
		learntQuestion.setExam(exam);
		learntQuestion.setTerm(termRepository.getOne(questionId));
		if(!answers.isEmpty()) {
			learntQuestion.setCorrect(true);		
		}
		else {
			learntQuestion.setCorrect(false);
		}
		learntTermRepository.save(learntQuestion);
		return answers;
	}

}
