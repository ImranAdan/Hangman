package org.adani.hangman.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Player {

	private String name;

	public Player(){
	}
	

	public void setName(String name) {
		this.name = name;
	}
	

	public String getName() {
		return name;

	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
