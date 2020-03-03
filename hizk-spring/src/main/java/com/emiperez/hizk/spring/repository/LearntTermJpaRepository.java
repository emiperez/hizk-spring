package com.emiperez.hizk.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiperez.hizk.model.ExamTermId;
import com.emiperez.hizk.model.LearntTerm;

public interface LearntTermJpaRepository extends JpaRepository<LearntTerm, ExamTermId> {

}
