package com.emiperez.hizk.spring.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Term;

@Repository
public interface TermJpaRepository extends JpaRepository<Term, Integer> {
	
	Term getOneByLocaleAndText(Locale locale, String text);
	
	@Query("SELECT DISTINCT t.locale FROM Term t")
	List<Locale> findDistinctLocales();

	@Query(value = "SELECT * FROM Term t "
				+ "	WHERE locale = :locale "
				+ " ORDER BY CEILING(RAND() * 20) "
				+ "				+ (SELECT count(*) FROM learnt_term WHERE term_id = t.id AND is_correct) "
				+ "				- (SELECT count(*) FROM learnt_term WHERE term_id = t.id AND NOT is_correct) "
				+ " LIMIT :numberOfQuestions", nativeQuery=true)
	List<Term> createQuestions(@Param("locale") Locale locale, @Param("numberOfQuestions") int numberOfQuestions);
}
