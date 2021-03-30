package com.emiperez.hizk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.emiperez.hizk.model.LearntTerm;
import com.emiperez.hizk.spring.repository.LearntTermRepository;

@Service("learntTermService")
public class AsyncLearntTermService implements LearntTermService {

	@Autowired
	private LearntTermRepository repository;
	
	@Override
	@Async
	public void saveLearntTerms(List<LearntTerm> terms) {
		terms.parallelStream().forEach(lt -> repository.save(lt));	
	}
	
	@Override
	@Async
	public void save(LearntTerm term) {
		repository.save(term);
	}

}
