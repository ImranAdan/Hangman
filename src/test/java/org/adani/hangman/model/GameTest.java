package org.adani.hangman.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit tests for {@link Game}.
 */
public class GameTest {
	
	private static final Player PLAYER = new Player("player");

	@Test
	public void testEquality() {
		final Game g1 = new Game(PLAYER, "g1");
		final Game g2 = new Game(PLAYER, "g1");
		final Game g3 = new Game(PLAYER, "g3");
		final Game g4 = null;
		
		assertEquals(g1, g2);
		assertNotEquals(g1, g3);
		assertNotEquals(g2, g3);
		assertEquals(g1, g1);
		assertNotEquals(null, g1);
		assertEquals(null, g4);
		
		
		assertEquals(g1.hashCode(), g2.hashCode());
		assertEquals(g2.hashCode(), g2.hashCode());
		assertNotEquals(g3.hashCode(), g2.hashCode());
	}
	
	@Test
	public void testCurrentGuessIsEmpty() {
		final Game g1 = new Game(PLAYER, "HELLO");
		assertNotNull(g1.getCurrentGuess());
		assertEquals("_____", g1.getCurrentGuess());
	}

}
