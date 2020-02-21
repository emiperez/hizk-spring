package com.emiperez.hizk.model;

import java.io.Serializable;

public class TranslationId implements Serializable{
	
	private Text origin;
	private Text translation;

	public TranslationId() {}
	
	public TranslationId(Text origin, Text translation) {
		this.origin = origin;
		this.translation = translation;
	}

	
	

}
