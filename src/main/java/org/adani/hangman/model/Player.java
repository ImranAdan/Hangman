package org.adani.hangman.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Player {

	private final String name;

	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
