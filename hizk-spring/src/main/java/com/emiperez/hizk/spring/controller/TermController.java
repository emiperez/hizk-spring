package com.emiperez.hizk.spring.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.spring.repository.TermJpaRepository;

@RestController
@RequestMapping("terms")
public class TermController {
	
	@Autowired
	TermJpaRepository textRepository;
	
	@GetMapping("/locales")
	public ResponseEntity<List<Locale>> listLocales() {
		var locales = textRepository.findDistinctLocales();
		if (locales.isEmpty()) {
			return new ResponseEntity<List<Locale>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Locale>>(locales, HttpStatus.OK);
	}
	
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
