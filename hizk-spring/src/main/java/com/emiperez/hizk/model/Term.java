package com.emiperez.hizk.model;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Term implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	Integer id;

	@Column(length = 5)
	private Locale locale;
	
	@Column(length = 50)
	@Size(min = 1, max = 50)
	private String text;
	
	public Term() {}
	
	public Term(Locale locale, String text) {
		this.locale = locale;
		this.text = text;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
	public Integer getId() {
		return id;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Term other = (Term) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}	
}
