package org.adani.hangman.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.adani.hangman.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

	@Autowired private GameService gameService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateGame() {
		Game createdGame = gameService.createGame(null);
		assertThat("Game should not be null!", createdGame, is(notNullValue()));
	}

	@Test
	public void testSaveGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadGame() {
		fail("Not yet implemented");
	}

}
