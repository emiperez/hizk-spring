package com.emiperez.hizk.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.service.TranslationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TranslationController {
	
	@Autowired
	TranslationService translationService;
	
	@PostMapping("/translation/new")
	Translation newTranslation(@RequestBody Translation translation) {
		return translationService.save(translation);
	}
	
	@DeleteMapping("/translation/delete/{originId}/{translationId}")
	ResponseEntity<Void> deleteTranslation(@PathVariable Integer originId, @PathVariable Integer translationId) {
		translationService.delete(new TranslationId(originId, translationId));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
