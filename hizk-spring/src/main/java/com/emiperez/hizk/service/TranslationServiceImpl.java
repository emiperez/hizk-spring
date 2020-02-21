package com.emiperez.hizk.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.spring.repository.TextJpaRepository;
import com.emiperez.hizk.spring.repository.TranslationJpaRepository;

public class TranslationServiceImpl implements TranslationService {
	
	@Autowired
	private TextJpaRepository textRepository;
	
	@Autowired
	private TranslationJpaRepository translationRepository;

	@Override
	public int saveTranslation(Translation translation) {
		boolean saveTranslation = false;
		if(!textRepository.existsById(translation.getOrigin().getId())) {
			textRepository.save(translation.getOrigin());
			saveTranslation = true;
		}
		if(!textRepository.existsById(translation.getTranslation().getId())) {
			textRepository.save(translation.getTranslation());
			saveTranslation = true;
		}
		if(saveTranslation && !translationRepository.existsById(new TranslationId(translation.getOrigin(), translation.getTranslation()))) {
			translationRepository.save(translation);
		}
		return 0;
	}

}
