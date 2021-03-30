package com.emiperez.hizk.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.LearntTerm;
import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.spring.repository.ExamRepository;
import com.emiperez.hizk.spring.repository.TermRepository;

public class ExamServiceImpl implements ExamService {

	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private LearntTermService learntTermService;

	@Override
	@Transactional
	public Exam startExam(Exam exam) {
		exam.setWhen(LocalDateTime.now());
		exam.setQuestions(termRepository.createQuestions(exam));
		exam = examRepository.save(exam);
		return exam;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	/**
	 * returns the list of terms that are the correct answer for the exam questions
	 */
	public List<Term> checkAnswers(Integer examId, List<Term> userAnswers) {
		Exam exam = examRepository.findById(examId).get();
		List<Term> checkedAnswers = new ArrayList<>();
		List<LearntTerm> learntQuestions = new ArrayList<>();
		
		userAnswers.stream().forEach(userAnswer -> {
			LearntTerm learntQuestion = new LearntTerm();
			learntQuestion.setExam(exam);
			learntQuestion.setTerm(termRepository.findById(userAnswer.getId()).get());
			List<Term> correctAnswers = termRepository.findCorrectAnswersByExamAndQuestion(examId, userAnswer.getId());
			String userAnswerText = exam.isCaseSensitive() ? userAnswer.getText() : userAnswer.getText().toUpperCase();
			if (uppercaseTermListIfNotCaseSensitive(correctAnswers,	exam.isCaseSensitive())
					.stream().anyMatch(t -> t.getText().equals(userAnswerText))) {				
				checkedAnswers.add(userAnswer);
				learntQuestion.setCorrect(true);
			} else {
				Term correctAnswer = correctAnswers.get(0);
				Term checkedAnswer = new Term(correctAnswer.getLocale(), correctAnswer.getText());
				checkedAnswer.setId(userAnswer.getId());
				checkedAnswers.add(checkedAnswer);
				learntQuestion.setCorrect(false);
			}
			learntQuestions.add(learntQuestion);
		});
		learntTermService.saveLearntTerms(learntQuestions);
		return checkedAnswers;
	}

	@Override
	@Transactional
	/**
	 * returns a list of text that are correct answers for the given question
	 */
	public List<String> findQuestionCorrectAnswers(Integer examId, Integer questionId) {
		List<Term> answers = termRepository.findCorrectAnswersByExamAndQuestion(examId, questionId);
		LearntTerm learntQuestion = new LearntTerm();
		Exam exam = examRepository.findById(examId).get();
		learntQuestion.setExam(exam);
		learntQuestion.setTerm(termRepository.findById(questionId).get());
		if(!answers.isEmpty()) {
			learntQuestion.setCorrect(true);		
		}
		else {
			learntQuestion.setCorrect(false);
		}
		learntTermService.save(learntQuestion);
		return answers.parallelStream().map(a -> a.getText()).collect(Collectors.toList());
	}
	
	private List<Term> uppercaseTermListIfNotCaseSensitive(List<Term> list, boolean isCaseSensitive) {
		return isCaseSensitive ?
				list : 
				list.stream().map(t -> {
					t.setText(t.getText().toUpperCase());
					return t;
				}).collect(Collectors.toList());
	}

}
