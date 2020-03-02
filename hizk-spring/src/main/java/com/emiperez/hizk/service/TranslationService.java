package com.emiperez.hizk.service;

import java.util.List;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

public interface TranslationService {
	
	List<Translation> listLatest();
	
	Translation save(Translation translation);
	
	int delete(TranslationId translationId);

}
