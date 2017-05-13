package org.adani.hangman.engine;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.adani.hangman.TestApplication;
import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit tests for {@link GameEngine}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestApplication.class)
public class GameEngineTest{

	@MockBean
	private RandomWordGenerator randomWordGenerator;
	
	@Autowired
	private GameEngine engine;
	
	private Player player;
	private Game game;
	
	@Before
	public void setUp(){
		player = new Player("TEST");
		game = new Game(player, "HELLO");
	}
	
	@Test
	public void testMockGetWord(){

		given(randomWordGenerator.getWord()).willReturn("WORD");
		String mockWord = randomWordGenerator.getWord();
		assertThat("Should be first word general!", mockWord, equalTo("WORD"));
	}

	
	@Test
	public void testNextGuessWhenCharacterIsCorrect() {
		assertEquals(10, game.getPermittedGuess());

		engine = new GameEngine(randomWordGenerator);

		final Game gamed = engine.updateGameState(game, 'L');

		assertEquals(game.getCurrentGuess().length(), game.getWordToGuess().length());
		assertEquals("__LL_", game.getCurrentGuess());
		assertEquals(10, gamed.getPermittedGuess());

	}
	
	@Test
	public void testNextGuessIsIncorrectPermittedGuessGoDown() {
		assertEquals(10, game.getPermittedGuess());

		engine = new GameEngine(randomWordGenerator);

		final Game gamed = engine.updateGameState(game, 'X');

		assertEquals(game.getCurrentGuess().length(), game.getWordToGuess().length());
		assertThat(game.getCurrentGuess().toString(), not(containsString("X")));
		assertEquals(9, gamed.getPermittedGuess());
	}
	
	@Test 
	public void testGameOverWhenPermittedGuessIsZero(){
		game.setPermittedGuess(1);

		engine = new GameEngine(randomWordGenerator);

		final Game gamed = engine.updateGameState(game, 'X');

		assertEquals(game.getCurrentGuess().length(), game.getWordToGuess().length());
		assertThat(game.getCurrentGuess().toString(), not(containsString("X")));
		assertEquals(0, gamed.getPermittedGuess());
		assertTrue(game.isGameOver());

	}
	
	@Test 
	public void testNextGuessWithCombinationOfCorrectIncorrectGuesses(){
		assertEquals(10, game.getPermittedGuess());

		engine = new GameEngine(randomWordGenerator);

		final Game gamed = engine.updateGameState(game, 'L');

		assertEquals(game.getCurrentGuess().length(), game.getWordToGuess().length());
		assertEquals("__LL_", game.getCurrentGuess());
		assertEquals(10, gamed.getPermittedGuess());
		
		// Wrong
		final Game gamed2 = engine.updateGameState(gamed, 'X');
		
		assertEquals("__LL_", game.getCurrentGuess());
		assertThat(game.getCurrentGuess().toString(), not(containsString("X")));
		assertEquals(9, gamed.getPermittedGuess());
		
		final Game gamed3 = engine.updateGameState(gamed2, 'H');
		assertEquals("H_LL_", game.getCurrentGuess());
		assertEquals(9, gamed.getPermittedGuess());
		
	}
	
	// You May add additional tests here, e.g. test updating of Hangman, Test GameOver state etc etc

}
