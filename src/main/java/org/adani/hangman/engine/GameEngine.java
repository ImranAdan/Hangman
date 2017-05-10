package org.adani.hangman.engine;

import java.util.ArrayList;
import java.util.List;

import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameEngine.class);

	private final RNG rng;
	
	@Autowired
	public GameEngine(RNG rng){
		this.rng = rng;
	}

	public Game createGame(Player p) {
		Game g = createHangmanGame(p);
		return g;
	}

	private Game createHangmanGame(Player p) {
		Game g = new Game(p, getWord());
		LOGGER.debug("Creating new game: {}", g);
		return g;
	}

	public boolean nextGuess(Game game, char character) {
		String word = game.getWord();
		String correctGuess = game.getCurrentGuess();

		List<Integer> indicies = new ArrayList<>();
		for (int i = 0; i < word.length(); ++i) {
			if (word.charAt(i) == character) {
				indicies.add(i);
			}
		}

		// TODO: add drawing of hangman character
		if (!indicies.isEmpty()) {
			char[] charArrayReplace = correctGuess.toCharArray();
			for (int index : indicies) {
				charArrayReplace[index] = character;
				game.setCurrentGuess(String.valueOf(charArrayReplace));
			}

		} else {
			game.setNumberOfPermittedGuesses(game.getNumberOfPermittedGuesses() - 1);
		}

		LOGGER.info("Game {}", game);

		return indicies.isEmpty() ? false : true;
	}
	
	
	public void updateHangmanState(Game game, boolean drawNextPart) {
		throw new NotImplementedException("Write test first");
	}
	
	
	public Game saveGame(Player p) {
		throw new NotImplementedException("Write test");
	}

	public Game loadGame(Player p) {
		throw new NotImplementedException("Write test");
	}

	// WORD GENERATION

	private static final String[] WORDS = { "lolly", "aac", "order", "brother" };

	public String getWord() {
		return WORDS[rng.nextIndex(WORDS.length)];
	}

}
