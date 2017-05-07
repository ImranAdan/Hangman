package org.adani.hangman.engine;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RNG {
	
	private static final Random RAND = new Random();
	
	public int nextIndex(int limit) {
		return RAND.nextInt((limit - 1) - 0 + 1) + 0;
	}
}
