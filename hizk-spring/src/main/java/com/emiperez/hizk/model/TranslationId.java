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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((translation == null) ? 0 : translation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranslationId other = (TranslationId) obj;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}


	
}
