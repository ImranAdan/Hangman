package org.adani.hangman.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;

import org.adani.hangman.TestApplication;
import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.adani.hangman.service.GameService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes =TestApplication.class)
public class GameControllerIT {

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private GameService gameServiceMock;

	private MockMvc mockMvc;
	private Player p;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		p = new Player("TEST");
	}

	@Test
	public void testGetCurrentGamesReturnCorrectHttpResponseAndPayload() throws Exception {
		String gamesUri = GameController.GAME_BASE_URI + "/";
		
		given(gameServiceMock.getCurrentGames()).willReturn(Arrays.asList(activeGameCase()));
		mockMvc.perform(get(gamesUri)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].currentGuess", equalTo("HELL_")))
				.andDo(print()).andReturn();
	}

	@Test
	public void testGetGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveGame() {
		fail("Not yet implemented");
	}

	
	private Game newGameCase() {
		Game g = new Game(p, "TEST");
		return g;
	}
	
	private Game activeGameCase(){
		Game g = new Game(p, "HELLO");
		g.setCurrentGuess("HELL_");
		return g;
	}
	
	private Game gameIsOverCase(){
		Game g = new Game(p, "TEST");
		g.setGameOver(true);
		return g;
	}
}
