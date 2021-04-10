package com.emiperez.hizk.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;
import com.emiperez.hizk.spring.repository.TermRepository;
import com.emiperez.hizk.spring.repository.TranslationRepository;

public class TranslationServiceImpl implements TranslationService {
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private TranslationRepository translationRepository;
	
	@Override
	public long countByLocalesAndLevel(Locale originLocale, Locale meaningLocale, Level level) {
		return translationRepository.countByLocalesAndLevel(originLocale, meaningLocale, level);
	}

	@Override
	public List<Translation> listLatest() {
		return translationRepository.findByOrderByOriginIdDesc(PageRequest.of(0, 10));
	}
		
	@Override
	public List<Translation> findByTerm(int id) {
		return translationRepository.findByTerm(id);
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
		translationRepository.findById(translationId).ifPresent(tr -> {
			translationRepository.deleteById(tr.getId());
			deleteTermIfNoTransactions(tr.getOrigin());
			deleteTermIfNoTransactions(tr.getMeaning());
			
		});
		return 1;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	private int deleteTermIfNoTransactions(Term term) {
		if(!termRepository.hasTranslations(term.getId())) {
			termRepository.deleteById(term.getId());
			return 1;
		}
		return 0;
	}

}
