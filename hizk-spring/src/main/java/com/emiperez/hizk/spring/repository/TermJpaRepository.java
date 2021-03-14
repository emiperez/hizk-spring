package com.emiperez.hizk.spring.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Exam;
import com.emiperez.hizk.model.Term;

@Repository
public interface TermJpaRepository extends JpaRepository<Term, Integer> {
	
	Term getOneByLocaleAndText(Locale locale, String text);
	
	@Query("SELECT DISTINCT t.locale FROM Term t")
	List<Locale> findDistinctLocales();

	@Query(value = "SELECT * FROM "
				+ "		(SELECT * FROM term, translation tr "
				+ "			WHERE (term.id = tr.origin_id OR term.id = tr.meaning_id) "
				+ "			AND locale = :#{#exam.questionLocale}"
				+ " 		AND tr.level <= :#{#exam.level?.toString()}"
				+ "		ORDER BY term.id DESC "
				+ "		LIMIT :#{#exam.latest} ) as t"	
				+ " ORDER BY CEILING(RAND() * 20) "
				+ "				+ (SELECT count(*) FROM learnt_term WHERE term_id = t.id AND is_correct) "
				+ "				- ( 3 * (SELECT count(*) FROM learnt_term WHERE term_id = t.id AND NOT is_correct)) "
				+ " LIMIT :#{#exam.questionAmount}", nativeQuery=true)
	List<Term> createQuestions(@Param("exam") Exam exam);
	
	@Query(value = "SELECT count(*) > 0 FROM translation tr WHERE tr.origin_id = :termId OR tr.meaning_id = :termId", nativeQuery=true)
	Boolean hasTranslations(@Param("termId") int termId);
}
