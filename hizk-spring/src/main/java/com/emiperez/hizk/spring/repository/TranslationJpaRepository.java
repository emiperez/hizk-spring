package com.emiperez.hizk.spring.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Translation;
import com.emiperez.hizk.model.TranslationId;

@Repository
public interface TranslationJpaRepository extends JpaRepository<Translation, TranslationId> {
	
	List<Translation> findByOrderByOriginIdDesc(Pageable page);
	
	@Query(value = "SELECT m.text FROM translation t INNER JOIN term m ON (t.origin_id = :originId AND m.id = t.meaning_id)"
																	+ " OR (t.meaning_id = :originId AND m.id = t.origin_id)"
					+ " WHERE m.locale = (SELECT answer_locale FROM exam WHERE id = :examId)", nativeQuery = true)
	List<String> findAnswersByExamAndQuestion(
			@Param("examId") Integer examId, //to get if it's case sensitive
			@Param("originId") Integer originId);

}
