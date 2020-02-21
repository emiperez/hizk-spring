package com.emiperez.hizk.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

public interface TranslationJpaRepository extends JpaRepository<Translation, TranslationId> {

}
