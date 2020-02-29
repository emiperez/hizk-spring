package com.emiperez.hizk.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emiperez.hizk.model.Exam;

@Controller
@RequestMapping("exams")
public class ExamController {
	
	@Autowired
	JpaRepository<Exam, Integer> repository;
	
	@PostMapping()
	Exam newExam(@RequestBody Exam exam) {
		return repository.save(exam);
	}
}
