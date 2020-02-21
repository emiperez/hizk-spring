package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

@Entity
@IdClass(TranslationId.class)
public class Translation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private Text origin;
	
	@Id
	@OneToOne
	private Text translation;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private Level level;
	
	public Translation(Text origin, Text translation, Level level) {
		this.origin = origin;
		this.translation = translation;
		this.level = level;
	}
	
	public TranslationId getId() {
		return new TranslationId(origin, translation);
	}
	
	public Text getOrigin() {
		return origin;
	}

	public Text getTranslation() {
		return translation;
	}

}
