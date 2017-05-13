package org.adani.hangman.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.stream.Collectors;

import org.adani.hangman.TestApplication;
import org.adani.hangman.engine.GameEngine;
import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.adani.hangman.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestApplication.class)
public class GameServiceIT {

	@MockBean
	private GameRepository gameRepo;

	@MockBean
	private GameEngine gameEngine;

	@Autowired
	private GameService gameService;

	private Player p; 

	@Before
	public void setUp(){
		p = new Player("TEST");
	}

	@Test
	public void testCreateGame() {
		Game g = newGameCase();
		given(gameRepo.save(any(Game.class))).willReturn(g);
		Game game = gameService.createGame(g);
		assertThat("Games are equal!", game.getId(), equalTo(g.getId()));
	}

	@Test 
	public void testGetCurrentGames(){
		Game created = newGameCase();
		Game inPlay = activeGameCase();
		Game over = gameIsOverCase();
		
		given(gameRepo.save(created)).willReturn(created);
		given(gameRepo.save(inPlay)).willReturn(inPlay);
		given(gameRepo.save(over)).willReturn(over);

		List<Game> currentGames = gameService.getCurrentGames();
		verify(gameRepo, times(1)).findByGameOver(false);
		
		List<Game> gamesThatAreOver = currentGames.stream().filter(g -> g.isGameOver()).collect(Collectors.toList());
		assertTrue("There should be no games that are over in the current list of games!", gamesThatAreOver.isEmpty());
	}
	
	@Test
	public void testGetGame() {
		Game inPlay = activeGameCase();
		given(gameRepo.findOne(inPlay.getId())).willReturn(inPlay);
		
		Game game = gameService.getGame(inPlay.getId());
		assertThat(inPlay.getId(), equalTo(game.getId()));
	}

	@Test
	public void testUpdateGame() {
		Game game = newGameCase();
		Game gameOver = gameIsOverCase();
		char nextGuess = 'l';
		
		given(gameEngine.updateGameState(game, nextGuess)).willReturn(gameOver);
		given(gameRepo.save(any(Game.class))).willReturn(gameOver);
		
		Game updateGame = gameService.updateGame(game, nextGuess);
		verify(gameEngine, times(1)).updateGameState(game, nextGuess);
		verify(gameRepo, times(1)).save(game);
		assertTrue("Game is over!", updateGame.isGameOver());
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
