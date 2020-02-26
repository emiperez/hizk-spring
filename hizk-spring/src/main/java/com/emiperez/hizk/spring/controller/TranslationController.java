package com.emiperez.hizk.spring.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.service.TranslationService;

@RestController
@RequestMapping("translations")
@CrossOrigin(origins = "http://10.0.3.32:4200")
public class TranslationController {
	
	private List<Level> levels = List.of(Level.values());
	@Autowired
	TranslationService translationService;
	
	@GetMapping("/")
	public RepresentationModel<?> root() {
		RepresentationModel<?> rootResource = new RepresentationModel<>();
		rootResource.add(
				linkTo(methodOn(TranslationController.class).root()).withSelfRel(),
				linkTo(methodOn(TranslationController.class).listAllLevels()).withRel("levels"));
		return rootResource;
	}
	
	@GetMapping("/levels")
	public List<Level> listAllLevels() {
		return levels;
	}
		
	@PostMapping()
	Translation newTranslation(@RequestBody Translation translation) {
		return translationService.save(translation);
	}
	
	@DeleteMapping("/{originId}/{translationId}")
	ResponseEntity<Void> deleteTranslation(@PathVariable Integer originId, @PathVariable Integer translationId) {
		translationService.delete(new TranslationId(originId, translationId));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
