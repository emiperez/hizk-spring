package com.emiperez.hizk.spring.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Level;
import com.emiperez.hizk.model.Term;
import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

@Repository
public interface TranslationJpaRepository extends JpaRepository<Translation, TranslationId> {
	
	@Query("SELECT count(*) FROM Translation t "
			+ " WHERE ((t.origin.locale = :originLocale AND t.meaning.locale = :meaningLocale) "
			+ " 		OR (t.origin.locale = :meaningLocale AND t.meaning.locale = :originLocale)) "
			+ " AND t.level <= :level ")
	long countByLocalesAndLevel(Locale originLocale, Locale meaningLocale, Level level);
	
	List<Translation> findByOrderByOriginIdDesc(Pageable page);	
	
	@Query("SELECT m FROM Translation t INNER JOIN Term m ON (t.origin.id = :originId AND m.id = t.meaning.id) "
																	+ " OR (t.meaning.id = :originId AND m.id = t.origin.id)"
					+ " WHERE m.locale = (SELECT e.answerLocale FROM Exam e WHERE e.id = :examId)")
	List<Term> findCorrectAnswersByExamAndQuestion(@Param("examId") Integer examId, @Param("originId") Integer originId);


}
