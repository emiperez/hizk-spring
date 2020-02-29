package com.emiperez.hizk.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.spring.repository.TextJpaRepository;

@RestController
@RequestMapping("terms")
public class TextController {
	
	@Autowired
	TextJpaRepository textRepository;
	
	@PutMapping("/{termId}")
	public ResponseEntity<Term> editText(@RequestBody Term term) {
		Optional<Term> current = textRepository.findById(term.getId());
		if(current.isEmpty()) {
			return new ResponseEntity<Term>(HttpStatus.NOT_FOUND);
		}
		textRepository.save(term);
		return new ResponseEntity<Term>(term, HttpStatus.OK);
	}
	

}
