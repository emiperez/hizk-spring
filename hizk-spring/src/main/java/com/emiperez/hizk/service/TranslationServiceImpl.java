package com.emiperez.hizk.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.spring.repository.TextJpaRepository;
import com.emiperez.hizk.spring.repository.TranslationJpaRepository;

public class TranslationServiceImpl implements TranslationService {
	
	@Autowired
	private TextJpaRepository textRepository;
	
	@Autowired
	private TranslationJpaRepository translationRepository;

	@Override
	public Translation save(Translation translation) {
		var results = textRepository.findByLocaleAndText(translation.getOrigin().getLocale(), translation.getOrigin().getText());
		if(results.isEmpty()) {			
			translation.setOrigin(textRepository.save(translation.getOrigin()));
		} else {
			translation.setOrigin(results.get(0));
		}
		
		results = textRepository.findByLocaleAndText(translation.getTranslation().getLocale(), translation.getTranslation().getText());
		if(results.isEmpty()) {			
			translation.setTranslation(textRepository.save(translation.getTranslation()));
		} else {
			translation.setTranslation(results.get(0));
		}
		if(!translationRepository.existsById(translation.getId())
			&& !translationRepository.existsById(translation.getId().getReverseTranslationId())) {
			return translationRepository.save(translation);
		}
		return translation;
	}

}
