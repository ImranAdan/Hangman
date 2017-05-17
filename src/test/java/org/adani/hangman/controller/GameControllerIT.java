package org.adani.hangman.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TestApplication.class)
public class GameControllerIT {

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private GameService gameServiceMock;

	private MockMvc mockMvc;
	private Player p;
	private ObjectMapper o;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		p = new Player();
		p.setName("TEST");
		o = new ObjectMapper();
		o.registerModule(new JavaTimeModule());
	}

	@Test
	public void testGetCurrentGamesReturnCorrectHttpResponseAndPayload() throws Exception {
		String gamesUri = GameController.GAME_BASE_URI + "/";

		
		Game g = Game.newGame(p, "HELLO");
		g.setId("TEST");
		g.setCurrentGuess("HELL_");
		
		given(gameServiceMock.getCurrentGames()).willReturn(Arrays.asList(g));
		mockMvc.perform(get(gamesUri)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].currentGuess", equalTo("HELL_"))).andDo(print()).andReturn();
	}

	@Test
	public void testGetGame() throws Exception {
		
		Game g = Game.newGame(p, "HELLO");
		g.setId("TEST");
		g.setCurrentGuess("HELL_");
		
		given(gameServiceMock.getGame(anyString())).willReturn(g);
		String getGameUri = GameController.GAME_BASE_URI + "/{id}";
		String id = "MOCK_ID";
		mockMvc.perform(get(getGameUri, id)).andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.currentGuess", equalTo("HELL_"))).andDo(print()).andReturn();
	}

	@Test
	public void testCreateGame() throws Exception {
		Game g = newGameCase();
		given(gameServiceMock.createGame(any(Player.class))).willReturn(g);

		String createGameUri = GameController.GAME_BASE_URI + "/create";
		String content = o.writeValueAsString(g);
		mockMvc.perform(post(createGameUri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(content))
				.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.currentGuess", equalTo("____"))).andDo(print()).andReturn();
	}

	@Test
	public void testSaveGame() throws Exception {
		
		Game g = Game.newGame(p, "HELLO");
		g.setId("TEST");
		g.setCurrentGuess("HELL_");
		
		given(gameServiceMock.saveGame(any(Game.class))).willReturn(g);

		String saveGameUri = GameController.GAME_BASE_URI + "/save";
		String content = o.writeValueAsString(g);
		mockMvc.perform(put(saveGameUri).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(content))
				.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.currentGuess", equalTo("HELL_"))).andDo(print()).andReturn();
	}

	@Test
	public void testUpdateGame() throws Exception {
		Game game = newGameCase();
		game.setId("TEST");
		Game gameOver = new Game(game);
		gameOver.setGameOver(true);

		String updateGameUri = GameController.GAME_BASE_URI + "/update/{id}/{character}";
		char character = 'I';
		String id = game.getId();
		
		given(gameServiceMock.updateGame(game.getId(), character)).willReturn(gameOver);

		String content = o.writeValueAsString(game);
		
		mockMvc.perform(
				put(updateGameUri, id , character).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(content))
				.andExpect(status().is2xxSuccessful()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.gameOver", equalTo(true))).andDo(print()).andReturn();
	}

	private Game newGameCase() {
		Game newGame = Game.newGame(p, "TEST");
		newGame.setId("TEST");
		return newGame;
	}
	

}
