package org.adani.hangman.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit tests for {@link Player}.
 */
public class PlayerTest {
	private Player player;

	@Before
	public void setUp() {
		player = new Player();
		player.setName("player");

	}

	@Test
	public void testEquality() {
		final Player player2 = new Player();
		player.setName("player");
		final Player player3 = new Player();
		player.setName("foo");

		assertEquals(player2, player);
		assertNotEquals(player, player3);
		assertNotEquals(null, player);

		assertEquals(player.hashCode(), player2.hashCode());
		assertNotEquals(player2.hashCode(), player3.hashCode());
	}

}
