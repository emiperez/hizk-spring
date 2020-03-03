package com.emiperez.hizk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.spring.repository.TermJpaRepository;
import com.emiperez.hizk.spring.repository.TranslationJpaRepository;

public class TranslationServiceImpl implements TranslationService {
	
	@Autowired
	private TermJpaRepository termRepository;
	
	@Autowired
	private TranslationJpaRepository translationRepository;

	@Override
	public List<Translation> listLatest() {
		return translationRepository.findByOrderByOriginIdDesc(PageRequest.of(0, 5));
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Translation save(Translation translation) {
		var result = termRepository.getOneByLocaleAndText(translation.getOrigin().getLocale(), translation.getOrigin().getText());
		if(result == null) {			
			translation.setOrigin(termRepository.save(translation.getOrigin()));
		} else {
			translation.setOrigin(result);
		}
		
		result = termRepository.getOneByLocaleAndText(translation.getMeaning().getLocale(), translation.getMeaning().getText());
		if(result == null) {			
			translation.setMeaning(termRepository.save(translation.getMeaning()));
		} else {
			translation.setMeaning(result);
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
