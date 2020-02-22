package com.emiperez.hizk.model;

import java.io.Serializable;

public class TranslationId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Text origin;
	private Text translation;

	public TranslationId() {}
	
	public TranslationId(Text origin, Text translation) {
		this.origin = origin;
		this.translation = translation;
	}

	public void setOrigin(Text origin) {
		this.origin = origin;
	}

	public void setTranslation(Text translation) {
		this.translation = translation;
	}

	public Text getOrigin() {
		return origin;
	}
	
	public Text getTranslation() {
		return translation;
	}
}
