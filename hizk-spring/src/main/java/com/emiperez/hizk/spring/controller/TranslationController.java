package com.emiperez.hizk.spring.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.service.TranslationService;

@RestController
@RequestMapping("translations")
public class TranslationController {

	private List<Level> levels = List.of(Level.values());
	@Autowired
	TranslationService translationService;

	@GetMapping
	public List<Translation> latestTranslations() {
		return translationService.listLatest();
	}

	@GetMapping(params = "termId")
	public List<Translation> listByTerm(@RequestParam int termId) {
		return translationService.findByTerm(termId);
	}

	@GetMapping("/levels")
	public List<Level> listAllLevels() {
		return levels;
	}

	@GetMapping("/count/{originLocale}/{meaningLocale}/{level}")
	public long count(@PathVariable Locale originLocale, @PathVariable Locale meaningLocale,
			@PathVariable Level level) {
		return translationService.countByLocalesAndLevel(originLocale, meaningLocale, level);
	}

	@PostMapping
	Translation newTranslation(@RequestBody Translation translation) {
		return translationService.save(translation);
	}

	@PutMapping
	Translation updateTranslation(@RequestBody Translation translation) {
		return translationService.save(translation);
	}

	@DeleteMapping("/{originId}/{translationId}")
	ResponseEntity<Void> deleteTranslation(@PathVariable Integer originId, @PathVariable Integer translationId) {
		translationService.delete(new TranslationId(originId, translationId));
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
