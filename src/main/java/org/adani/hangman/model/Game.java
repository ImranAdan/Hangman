package org.adani.hangman.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Game {

	private final Player player;
	private final String word;
	private final LocalDateTime startTs;
	
	public Game(Player player, String wordToGuess) {
		this.player = player;
		this.word = wordToGuess;
		this.startTs= LocalDateTime.now();
	}
	
	public Player getPlayer() {
		return player;
	}

	public String getWord() {
		return word;
	}

	public LocalDateTime getStartTs() {
		return startTs;
	}
}
