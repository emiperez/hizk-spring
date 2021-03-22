package com.emiperez.hizk.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.emiperez.hizk.service.AsyncLearntTermService;
import com.emiperez.hizk.service.ExamService;
import com.emiperez.hizk.service.ExamServiceImpl;
import com.emiperez.hizk.service.LearntTermService;
import com.emiperez.hizk.service.TranslationService;
import com.emiperez.hizk.service.TranslationServiceImpl;

@Configuration
@EnableJpaRepositories("com.emiperez.hizk.spring.repository")
@EntityScan("com.emiperez.hizk.model")
public class BeanConfiguration {
	
	@Bean
	public TranslationService translationService() {
		return new TranslationServiceImpl();
	}
	
	@Bean
	public ExamService examService() {
		return new ExamServiceImpl();
	}
	
	@Bean 
	public LearntTermService learntTermService() {
		return new AsyncLearntTermService();
	}
}
