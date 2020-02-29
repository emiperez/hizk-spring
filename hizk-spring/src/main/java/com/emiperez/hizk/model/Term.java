package com.emiperez.hizk.model;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Term implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	Integer id;

	@Column(length = 5)
	private Locale locale;
	
	@Column(length = 50)
	@Size(min = 1, max = 50)
	private String text;
	
	public Term() {}
	
	public Term(Locale locale, String text) {
		this.locale = locale;
		this.text = text;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
	public Integer getId() {
		return id;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getText() {
		return text;
	}
}
