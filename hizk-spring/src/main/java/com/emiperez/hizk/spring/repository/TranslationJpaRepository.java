package com.emiperez.hizk.spring.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

@Repository
public interface TranslationJpaRepository extends JpaRepository<Translation, TranslationId> {
	
	List<Translation> findByOrderByOriginIdDesc(Pageable page);

}
