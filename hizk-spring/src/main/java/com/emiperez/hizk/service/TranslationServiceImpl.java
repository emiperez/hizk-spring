package com.emiperez.hizk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.spring.repository.TermJpaRepository;
import com.emiperez.hizk.spring.repository.TranslationJpaRepository;

public class TranslationServiceImpl implements TranslationService {
	
	@Autowired
	private TermJpaRepository textRepository;
	
	@Autowired
	private TranslationJpaRepository translationRepository;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Translation save(Translation translation) {
		var results = textRepository.findByLocaleAndText(translation.getOrigin().getLocale(), translation.getOrigin().getText());
		if(results.isEmpty()) {			
			translation.setOrigin(textRepository.save(translation.getOrigin()));
		} else {
			translation.setOrigin(results.get(0));
		}
		
		results = textRepository.findByLocaleAndText(translation.getMeaning().getLocale(), translation.getMeaning().getText());
		if(results.isEmpty()) {			
			translation.setMeaning(textRepository.save(translation.getMeaning()));
		} else {
			translation.setMeaning(results.get(0));
		}
		if(!translationRepository.existsById(translation.getId())
			&& !translationRepository.existsById(translation.getId().getReverseTranslationId())) {
			return translationRepository.save(translation);
		}
		return translation;
	}	

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public int delete(TranslationId translationId) {
		translationRepository.deleteById(translationId);
		return 1;
	}

}
