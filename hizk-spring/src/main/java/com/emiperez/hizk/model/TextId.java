package com.emiperez.hizk.model;

import java.io.Serializable;
import java.util.Locale;

public class TextId implements Serializable {
	
	private Locale locale;
	private String text;

	public TextId(Locale locale, String text) {
		this.locale = locale;
		this.text = text;
	}
}
