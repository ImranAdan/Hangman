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
	
	/**
	 * Update the state of the game based on the next character. 
	 * @param game The game to be updated. 
	 * @param nextCharacter The next guessed character.
	 * @return The updated game based on the character that was provided. 
	 */
	public Game updateGameState(Game game, char nextCharacter) {
		final String wordToGuess = game.getWordToGuess();
		final String correctGuessSoFar = game.getCurrentGuess();

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
		}

		if (game.getPermittedGuess() == 0) {
			game.setGameOver(true);
		}
		
		/**
		 * TODO: Given a game and a next character, update the state of the game
		 * based on the next character in relation to the word that is to be
		 * guessed. Return the updated game.
		 * 
		 * The method should:
		 * 
		 * 	1 - Check if the character is in the word to guess
		 * 		if YES THEN
		 * 			- 	Update the guess, e.g. word = HELLO, nextCharacter = L, guess = _ _ L L _ 
		 * 		if NO THEN
		 * 			- 	Update the number of incorrect guesses, e.g. word = HELLO, nextCharacter = W, guess = _ _ _ _ _ , incorrectGuess = 1
		 * 	 		- 	Update the state of the game's Hangman
		 * 			- 	Updating the state of Hangman.
		 * 				-	Updating Hangman depends on the number of permitted guess and number of incorrect guesses 
		 * 						you dont want to update the state of hangman for each incorrect guess, think about how this should be done, give the player
		 * 						a chance based on the number of permitted guess, i.e. every other incorrect guess. 
		 * 
		 * 	2 - Compute game over state
		 * 			- Game is over when the number of permitted guesses = 0 && all part of the hangman are to be drawn
		 * 
		 * 
		 * 	Thats the basis of it you may want to introduce additional helper methods.
		 * 
		 *  Also have a look at GameEngineTest, we would want all those test to pass. 
		 * 
		 */
		
		return game;
	}
}
