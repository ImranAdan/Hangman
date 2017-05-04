package org.adani.hangman.service;

import java.util.Random;

public class WordEngine {

	private static final Random RAND = new Random();
	
	private static final String[] WORDS = {
		"general", "order", "brother"	
	};
	
	public static String getWord(){
		return WORDS[generateRandomIndex()];
	}

	private static int generateRandomIndex(){
		return RAND.nextInt((WORDS.length-1) - 0 + 1) + 0;
	}
}
