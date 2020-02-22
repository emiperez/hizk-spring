package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Translation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private TranslationId id;

	@OneToOne
	@JoinColumn(name = "origin_id", insertable = false, updatable = false)
	private Text origin;

	@OneToOne
	@JoinColumn(name = "translation_id", insertable = false, updatable = false)
	private Text translation;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private Level level;
	
	public Translation() {}
	
	public Translation(Text origin, Text translation, Level level) {
		this.origin = origin;
		this.translation = translation;
		this.level = level;
		this.id = new TranslationId(origin.getId(), translation.getId());
	}
	
	public TranslationId getId() {
		return id;
	}	

	
	public Text getOrigin() {
		return origin;
	}

	public Text getTranslation() {
		return translation;
	}
	
	public void setOrigin(Text origin) {
		this.origin = origin;
		this.id.setOrigin(origin.getId());
	}
	
	public void setTranslation(Text translation) {
		this.translation = translation;
		this.id.setTranslation(translation.getId());
	}

}
