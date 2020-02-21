package com.emiperez.hizk.model;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Size;

@Entity
@IdClass(TextId.class)
public class Text implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 5)
	private Locale locale;
	
	@Id
	@Column(length = 50)
	@Size(min = 1, max = 50)
	private String text;
	
	public Text setLocale(Locale locale) {
		this.locale = locale;
		return this;
	}

	public Text setText(String text) {
		this.text = text;
		return this;
	}
	
	public TextId getId() {
		return new TextId(locale, text);
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getText() {
		return text;
	}
}
