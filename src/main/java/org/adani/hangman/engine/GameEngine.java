package org.adani.hangman.engine;

import java.util.ArrayList;
import java.util.List;

import org.adani.hangman.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);

	private final RandomWordGenerator rng;
	
	@Autowired
	public GameEngine(RandomWordGenerator rng){
		this.rng = rng;
	}
	
	public String getWord() {
		return rng.getWord();
	}
	
	
	/**
	 * Update the state of the game based on the next character. 
	 * @param game The game to be updated. 
	 * @param nextCharacter The next guessed character.
	 * @return The updated game based on the character that was provided. 
	 */
	public Game updateGameState(Game game, char nextCharacter) {
		String wordToGuess = game.getWordToGuess();
		String correctGuessSoFar = game.getCurrentGuess();

		List<Integer> indicies = new ArrayList<>();
		for (int i = 0; i < wordToGuess.length(); i++) {
			if (wordToGuess.charAt(i) == nextCharacter) {
				indicies.add(i);
			}
		}

		if (!indicies.isEmpty()) {
			char[] charArrayReplace = correctGuessSoFar.toCharArray();
			for (int index : indicies) {
				charArrayReplace[index] = nextCharacter;
				game.setCurrentGuess(String.valueOf(charArrayReplace));
			}

		} else {
			game.setPermittedGuess((game.getPermittedGuess() - 1));
			// TODO: Update Hangman State
		}

		if (game.getPermittedGuess() == 0) {
			LOGGER.info("Out of guesses, player {} has lost the game", game.getPlayer().getName());
			game.setGameOver(true);
		}
		
		return game;
	}




}
