package com.emiperez.hizk.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.service.ExamService;

@RestController
@RequestMapping("exams")
public class ExamController {
	
	@Autowired
	ExamService examService;
	
	@PostMapping
	Exam newExam(@RequestBody Exam exam) {
		Exam output =  examService.startExam(exam);
		return output;
	}
	
	@GetMapping("/{examId}/{questionId}/**")
	ResponseEntity<Term> checkAnswer(@PathVariable Integer examId, @PathVariable Integer questionId, HttpServletRequest request) {
		String answerText = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		answerText = answerText.replace("/exams/" + examId + "/" + questionId + "/", "");
		if (examService.checkAnswer(examId, questionId, answerText)) {
			return new ResponseEntity<Term>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Term>(HttpStatus.NOT_FOUND);
	}
}

