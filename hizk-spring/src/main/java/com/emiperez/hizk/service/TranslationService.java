package com.emiperez.hizk.service;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

public interface TranslationService {	
	
	Translation save(Translation translation);
	
	int delete(TranslationId translationId);

}
