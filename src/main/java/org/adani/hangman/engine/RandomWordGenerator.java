package org.adani.hangman.engine;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Random number generator.
 */
@Component
public class RandomWordGenerator {

	private static final String[] WORDS = { "lolly", "aac", "order", "brother" };
	
	private final RNG rng; 
	
	@Autowired
	public RandomWordGenerator(RNG rng){
		this.rng = rng;
	} 

	public String getWord() {
		return WORDS[rng.nextInt(WORDS.length)];
	}
}
