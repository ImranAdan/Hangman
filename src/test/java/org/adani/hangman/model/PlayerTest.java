package org.adani.hangman.model;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link Player}.
 */
public class PlayerTest {
	private Player player;

	@Before
	public void setUp() {
		player = new Player("player");
	}

	@Test
	public void testGetterss() {
		assertNotNull(player.getId());
		assertNotNull(player.getCreationTs());
		assertEquals("player", player.getName());
	}

	@Test
	public void testEquality() {
		final Player player2 = new Player("player");
		final Player player3 = new Player("foo");

		assertEquals(player2, player);
		assertNotEquals(player, player3);
		assertNotEquals(null, player);

		assertEquals(player.hashCode(), player2.hashCode());
		assertNotEquals(player2.hashCode(), player3.hashCode());

	}

	@Test
	public void testToString() {
		final String s = player.toString();
		assertThat(s, allOf(containsString("player"), containsString("creationTs")));
	}

}
