package com.emiperez.hizk.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiperez.hizk.model.Exam;

public interface ExamJpaRepository extends JpaRepository<Exam, Integer> {

}
