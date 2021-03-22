package com.emiperez.hizk.service;

import java.util.List;

import com.emiperez.hizk.model.LearntTerm;

public interface LearntTermService {
	
	void saveLearntTerms(List<LearntTerm> terms);
	
	void save(LearntTerm term);

}
