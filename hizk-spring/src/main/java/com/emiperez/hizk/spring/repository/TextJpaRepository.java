package com.emiperez.hizk.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiperez.hizk.model.Text;
import com.emiperez.hizk.model.TextId;

public interface TextJpaRepository extends JpaRepository<Text, TextId> {

}
