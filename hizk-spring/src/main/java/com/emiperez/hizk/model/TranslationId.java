package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TranslationId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "origin_id")
	private Integer origin;
	
	@Column(name = "translation_id")
	private Integer translation;

	public TranslationId() {}

	public TranslationId(Integer originId, Integer translationId) {
		this.origin = originId;
		this.translation = translationId;
	}
	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public Integer getTranslation() {
		return translation;
	}

	public void setTranslation(Integer translation) {
		this.translation = translation;
	}
	
	public TranslationId getReverseTranslationId() {
		return new TranslationId(translation, origin);
	}


	
}
