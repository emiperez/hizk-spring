package com.emiperez.hizk.spring.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Text;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.service.TranslationService;

@RestController
public class TranslationController {
	
	@Autowired
	TranslationService translationService;
	
	@GetMapping("/init")
	Translation init() {
		return translationService.save(
				new Translation(
						new Text(new Locale("es"), "rojo"), 
						new Text(new Locale("de"), "rot"), 
						Level.A1));
	}
	
	@PostMapping("/new")
	Translation newTranslation(@RequestBody Translation translation) {
		return translationService.save(translation);
	}
	

}
