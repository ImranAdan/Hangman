package org.adani.hangman.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Game {

	private static final int GAME_GUESS_ALLOWANCE = 5;
	
	@Id private final String id;
	private final Player player;
	private final String word;
	private final LocalDateTime startTs;
	
	private String currentGuess;
	private int numberOfPermittedGuesses;
	
	public Game(Player player, String wordToGuess) {
		this.player = player;
		this.word = wordToGuess;
		this.startTs= LocalDateTime.now();
		this.id = player.getName() + "_" + this.word + "_" + this.startTs.toString();
		
		this.setCurrentGuess(this.word.replaceAll(".", "_"));
		this.setNumberOfPermittedGuesses(wordToGuess.length() + GAME_GUESS_ALLOWANCE);
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
	
	public int getNumberOfPermittedGuesses() {
		return numberOfPermittedGuesses;
	}
	
	public void setNumberOfPermittedGuesses(int numberOfPermittedGuesses) {
		this.numberOfPermittedGuesses = numberOfPermittedGuesses;
	}

	public boolean isGameOver() { //??
		return this.getNumberOfPermittedGuesses() == 0;
	}
	

	
	
	public void setCurrentGuess(String currentGuess) {
		this.currentGuess = currentGuess;
	}
	
	public String getCurrentGuess() {
		return currentGuess;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public static int getGuessAllowance() {
		return GAME_GUESS_ALLOWANCE;
	}

}
