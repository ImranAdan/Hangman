package org.adani.hangman.engine;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestApplication.class)
public class GameEngineTest{

	@MockBean private RNG rng;
	@Autowired private GameEngine engine;
	
	private Player p;
	
	@Before
	public void setUp(){
		p = new Player("IMMY");
	}
	
	@Test
	public void testGetWord() {
		String word = engine.getWord();
		assertTrue(word != null && !word.isEmpty());
	}
	
	@Test
	public void testGameEquality(){
		Game createGame = engine.createGame(p);
		Game copy = createGame;
		
		copy.setCurrentGuess("Random");
		
		assertThat("Should be equal!", copy, equalTo(createGame));
	}
	
	@Test
	public void testCreateGame(){
		
		
		Game game = engine.createGame(p);
		Player player = game.getPlayer();
		assertThat("Should equal p", player, equalTo(p));
		
		String word = game.getWord();
		assertTrue("A word is generated" ,!word.isEmpty());
		
		int numberOfPermittedGuesses = game.getNumberOfPermittedGuesses();
		assertThat("Number of permitted letters to guess equal to length of word plus an allowance!", numberOfPermittedGuesses, equalTo(word.length() + Game.getGuessAllowance()));
		
		boolean gameOver = game.isGameOver();
		assertFalse("New game that is created can not be over!", gameOver);
	}
	
	
	// TODO: use the incorrect number of guesses property!
	
	@Test
	public void testGetNextCorrectGuess(){
		given(rng.nextIndex(anyInt())).willReturn(0);
		String word = engine.getWord();
		assertThat("Should be first word general!", word, equalTo("lolly"));
	
		Game game = engine.createGame(p);
		int beforeguess = game.getNumberOfPermittedGuesses();
		
		char character = 'l';
		boolean correctGuess = engine.nextGuess(game, character);
		assertTrue("Correct guess!", correctGuess);
		
		int afterGuess = game.getNumberOfPermittedGuesses();
		assertThat(afterGuess, equalTo(beforeguess));
	
		boolean gameOver = game.isGameOver();
		assertFalse("New game that is created can not be over!", gameOver);
	}
	
	
	@Test
	public void testGetNextIncorrectGuess(){
		given(rng.nextIndex(anyInt())).willReturn(0);
		String word = engine.getWord();
		assertThat("Should be first word general!", word, equalTo("lolly"));
	
		Game game = engine.createGame(p);
		int beforeguess = game.getNumberOfPermittedGuesses();
		
		char character = 'e';
		boolean correctGuess = engine.nextGuess(game, character);
		assertTrue("Incorrect guess!", !correctGuess);
		
		int afterGuess = game.getNumberOfPermittedGuesses();
		assertThat(afterGuess , equalTo(beforeguess-1));
		
		//TODO: add "draw" next hangman part test
	
		boolean gameOver = game.isGameOver();
		assertFalse("New game that is created can not be over!", gameOver);
	}
	
	
	@Test
	public void testGameOverWithWinState(){
		given(rng.nextIndex(anyInt())).willReturn(1);
		String word = engine.getWord();
		assertThat("Should be word aac!", word, equalTo("aac"));
		
		Game game = engine.createGame(p);
		int beforeguess = game.getNumberOfPermittedGuesses();
		
		char character = 'a';
		boolean correctGuess = engine.nextGuess(game, character);
		assertTrue("Correct guess!", correctGuess);
		
		int afterGuess = game.getNumberOfPermittedGuesses();
		assertThat(afterGuess, equalTo(beforeguess));
		
		//TODO: add "draw" next hangman part test
		
		int beforeNextGuess = game.getNumberOfPermittedGuesses();
		char nextCharacter = 'c';
		boolean correctNextGuess = engine.nextGuess(game, nextCharacter);
		assertTrue("Correct guess!", correctNextGuess);
		
		int afterNextGuess = game.getNumberOfPermittedGuesses();
		assertThat(afterNextGuess, equalTo(beforeNextGuess));
		
		//TODO: add "draw" next hangman part test
		
		boolean gameOver = game.isGameOver();
		assertTrue("Game is over guessed correctly all characters!", gameOver);
	}
	

	@Test
	public void testGameOverWithLossState(){
		given(rng.nextIndex(anyInt())).willReturn(1);
		String word = engine.getWord();
		assertThat("Should be first word aac!", word, equalTo("aac"));
		
		Game game = engine.createGame(p);
		int beforeguess = game.getNumberOfPermittedGuesses();
		
		char character = 'b';
		boolean correctGuess = engine.nextGuess(game, character);
		assertTrue("Correct guess!", correctGuess);
		
		int afterGuess = game.getNumberOfPermittedGuesses();
		assertThat(afterGuess, equalTo(beforeguess - 1));
		
		//TODO: add "draw" next hangman part test
		int beforeNextGuess = game.getNumberOfPermittedGuesses();
		char nextCharacter = 's';
		boolean correctNextGuess = engine.nextGuess(game, nextCharacter);
		assertTrue("Correct guess!", correctNextGuess);
		
		int afterNextGuess = game.getNumberOfPermittedGuesses();
		assertThat(afterNextGuess, equalTo(beforeNextGuess - 2));
		
		//TODO: add "draw" next hangman part test
		int beforeFinalGuess = game.getNumberOfPermittedGuesses();
		char finalCharacter = 's';
		boolean correctfinalGuess = engine.nextGuess(game, finalCharacter);
		assertTrue("Correct guess!", !correctfinalGuess);
		
		int afterFinalGuess = game.getNumberOfPermittedGuesses();
		assertThat(afterFinalGuess, equalTo(beforeFinalGuess - 3));
		
		//TODO: add "draw" next hangman part test
		boolean gameOver = game.isGameOver();
		assertTrue("Game is over guessed incorrectly all characters!", gameOver);
	}
	
}
