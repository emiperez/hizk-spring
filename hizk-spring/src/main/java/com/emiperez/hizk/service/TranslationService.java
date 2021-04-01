package com.emiperez.hizk.service;

import java.util.List;
import java.util.Locale;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

public interface TranslationService {
	
	long countByLocalesAndLevel(Locale originLocale, Locale meaningLocale, Level level);
	
	List<Translation> listLatest();
	
	List<Translation> findByTerm(int id);
	
	Translation save(Translation translation);
	
	int delete(TranslationId translationId);

}
