package com.emiperez.hizk.spring.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emiperez.hizk.model.Text;

@Repository
public interface TextJpaRepository extends JpaRepository<Text, Integer> {
	
	List<Text> findByLocaleAndText(Locale locale, String text);

}
