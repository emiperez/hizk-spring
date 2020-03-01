package com.emiperez.hizk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Translation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private TranslationId id;

	@ManyToOne
	@JoinColumn(name = "origin_id", insertable = false, updatable = false)
	private Term origin;

	@ManyToOne
	@JoinColumn(name = "meaning_id", insertable = false, updatable = false)
	private Term meaning;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 2)
	private Level level;
	
	public Translation() {
		id = new TranslationId(null, null);
	}
	
	public Translation(Term origin, Term meaning, Level level) {
		this.origin = origin;
		this.meaning = meaning;
		this.level = level;
		this.id = new TranslationId(origin.getId(), meaning.getId());
	}
	
	public Translation(Term origin, Term translation, String level) {
		this.origin = origin;
		this.meaning = translation;
		this.level = Level.valueOf(level);
		this.id = new TranslationId(origin.getId(), translation.getId());
	}
	
	public TranslationId getId() {
		return id;
	}	
	
	public Term getOrigin() {
		return origin;
	}

	public Term getMeaning() {
		return meaning;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void setOrigin(Term origin) {
		this.origin = origin;
		this.id.setOriginId(origin.getId());
	}
	
	public void setMeaning(Term meaning) {
		this.meaning = meaning;
		this.id.setMeaningId(meaning.getId());
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
