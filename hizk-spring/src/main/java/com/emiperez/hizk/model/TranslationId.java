package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TranslationId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "origin_id")
	private Integer originId;
	
	@Column(name = "meaning_id")
	private Integer meaningId;

	public TranslationId() {}

	public TranslationId(Integer originId, Integer meaningId) {
		this.originId = originId;
		this.meaningId = meaningId;
	}
	public Integer getOriginId() {
		return originId;
	}

	public void setOriginId(Integer origin) {
		this.originId = origin;
	}

	public Integer getMeaningId() {
		return meaningId;
	}

	public void setMeaningId(Integer meaningId) {
		this.meaningId = meaningId;
	}
	
	public TranslationId getReverseTranslationId() {
		return new TranslationId(meaningId, originId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((originId == null) ? 0 : originId.hashCode());
		result = prime * result + ((meaningId == null) ? 0 : meaningId.hashCode());
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
		if (originId == null) {
			if (other.originId != null)
				return false;
		} else if (!originId.equals(other.originId))
			return false;
		if (meaningId == null) {
			if (other.meaningId != null)
				return false;
		} else if (!meaningId.equals(other.meaningId))
			return false;
		return true;
	}


	
}
