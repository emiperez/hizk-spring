package com.emiperez.hizk.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.service.ExamService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("exams")
@Log4j2
public class ExamController {
	
	@Autowired
	ExamService examService;
	
	@PostMapping
	Exam newExam(@RequestBody Exam exam) {
		Exam output =  examService.startExam(exam);
		return output;
	}
	
	@PutMapping("/answers/{examId}")
	List<Term> checkAnswers(@PathVariable Integer examId, @RequestBody List<Term> answers) {
		List<Term> result = examService.checkAnswers(examId, answers);
		log.info("=================================================RESULTS calculated================================================");
		return result;
		
	}
	
	@GetMapping("/{examId}/{questionId}")
	ResponseEntity<List<String>> findQuestionAnswers(@PathVariable Integer examId, @PathVariable Integer questionId, HttpServletRequest request) {
		
		List<String> correctAnswers = examService.findQuestionCorrectAnswers(examId, questionId);
		if (!correctAnswers.isEmpty()) {
			return new ResponseEntity<List<String>>(correctAnswers, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
	}
}

