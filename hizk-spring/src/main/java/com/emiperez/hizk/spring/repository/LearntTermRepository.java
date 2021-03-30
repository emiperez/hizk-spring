package com.emiperez.hizk.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.emiperez.hizk.model.ExamTermId;
import com.emiperez.hizk.model.LearntTerm;

public interface LearntTermRepository extends CrudRepository<LearntTerm, ExamTermId> {

}
