package org.adani.hangman.engine;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import org.adani.hangman.TestApplication;
import org.adani.hangman.model.Player;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestApplication.class)
@Ignore
public class GameEngineTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameEngineTest.class);

	@MockBean
	private RandomWordGenerator randomWordGenerator;

	// SUBJECT UNDER TEST
	@Autowired
	private GameEngine engine;

	private Player p;

	@Before
	public void setUp() {
		p = new Player("TEST");
	}

	@Test
	public void testMockGetWord() {
		given(randomWordGenerator.getWord()).willReturn("WORD");
		String mockWord = randomWordGenerator.getWord();
		assertThat("Should be first word general!", mockWord, equalTo("WORD"));
	}

	@Test
	public void testNextGuessWhenCharacterIsCorrect() {
		fail("Test not implemented");
	}

	@Test
	public void testNextGuessWhenCharacterIsInCorrect() {
		fail("Test not implemented");
	}

	@Test
	public void testNextGuessWithCombinationOfCorrectIncorrectGuesses() {
		fail("Test not implemented");
	}

	// You May add additional tests here, e.g. test updating of Hangman, Test
	// GameOver state etc etc
}
