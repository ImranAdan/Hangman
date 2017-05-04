package org.adani.hangman.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WordEngineTest {
	
	@Test
	public void testGetWord() {
		for(int i =0; i<1000; ++i){
			String word = WordEngine.getWord();
			assertTrue(word != null && !word.isEmpty());
		}
	}

}
