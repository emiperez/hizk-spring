package com.emiperez.hizk.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Exam;
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
}
