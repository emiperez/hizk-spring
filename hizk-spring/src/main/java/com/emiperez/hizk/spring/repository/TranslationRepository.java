package com.emiperez.hizk.spring.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

@Repository
public interface TranslationRepository extends CrudRepository<Translation, TranslationId> {
	
	@Query("SELECT count(*) FROM Translation t "
			+ " WHERE ((t.origin.locale = :originLocale AND t.meaning.locale = :meaningLocale) "
			+ " 		OR (t.origin.locale = :meaningLocale AND t.meaning.locale = :originLocale)) "
			+ " AND t.level <= :level ")
	long countByLocalesAndLevel(Locale originLocale, Locale meaningLocale, Level level);
	
	List<Translation> findByOrderByOriginIdDesc(Pageable page);
	
	@Query("SELECT t FROM Translation t WHERE t.origin.id = :termId OR t.target.id = :termId")
	List<Translation> findByTerm(@Param("termId") Integer termId);


}
