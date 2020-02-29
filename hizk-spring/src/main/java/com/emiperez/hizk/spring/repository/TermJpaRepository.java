package com.emiperez.hizk.spring.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Term;

@Repository
public interface TermJpaRepository extends JpaRepository<Term, Integer> {
	
	List<Term> findByLocaleAndText(Locale locale, String text);
	
	@Query("SELECT DISTINCT t.locale FROM Term t")
	List<Locale> findDistinctLocales();

}
