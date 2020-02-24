package com.emiperez.hizk.spring.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Text;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.service.TranslationService;

@CrossOrigin(origins = "http://10.0.3.32:4200")
@RestController
public class TranslationController {
	
	private List<Level> levels = List.of(Level.values());
	@Autowired
	TranslationService translationService;
	
	@GetMapping("/levels")
	List<Level> listAllLevels() {
		return levels;
	}
	
	@GetMapping("/test")
	Translation test() {
		Translation t = new Translation(
								new Text(new Locale("de"), "und"),
								new Text(new Locale("es"), "y"),
								Level.A1);
		return t;
	}
		
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
