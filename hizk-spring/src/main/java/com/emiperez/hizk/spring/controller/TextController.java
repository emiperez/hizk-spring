package com.emiperez.hizk.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Text;
import com.emiperez.hizk.spring.repository.TextJpaRepository;

@RestController
@RequestMapping("texts")
@CrossOrigin(origins = "http://10.0.3.32:4200")
public class TextController {
	
	@Autowired
	TextJpaRepository textRepository;
	
	@PutMapping("/{translationId}")
	public ResponseEntity<Text> editText(@RequestBody Text text) {
		Optional<Text> current = textRepository.findById(text.getId());
		if(current.isEmpty()) {
			return new ResponseEntity<Text>(HttpStatus.NOT_FOUND);
		}
		textRepository.save(text);
		return new ResponseEntity<Text>(text, HttpStatus.OK);
	}
	

}
