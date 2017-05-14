package org.adani.hangman.engine;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RNG {

	private static final Random RAND = new Random();
	
	/**
	 * Generate a random number up to a given limit. 
	 * @param limit The max bounds of the random integer to generate. 
	 * @return A bounded integer value. 
	 */
	public int nextInt(int limit) {
		return RAND.nextInt(limit);
	}
}
