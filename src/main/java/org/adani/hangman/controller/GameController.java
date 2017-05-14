package org.adani.hangman.controller;

import java.util.List;

import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.adani.hangman.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *	Request Controller. 
 */
@RestController
@RequestMapping(GameController.GAME_BASE_URI)
public class GameController {

	public static final String GAME_BASE_URI = "/games";
	
	@Autowired
	private GameService gameService;

	/**
	 * Get a list of current active game.
	 * 
	 * @return Games that are not over.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Game> getCurrentGames() {
		List<Game> currentGames = gameService.getCurrentGames();
		return currentGames;
	}

	/**
	 * Get a Game by Id.
	 * 
	 * @param id
	 *            Id of game.
	 * @return The game.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Game getGame(@PathVariable(value = "id") String id) {
		Game game = gameService.getGame(id);
		return game;
	}

	/**
	 * Create a new Game.
	 * 
	 * @param game
	 *            The game.
	 * @return A new Game.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Game createGame(@RequestBody(required = true) Player player) {
		Game createdGame = gameService.createGame(player);
		return createdGame;
	}

	/**
	 * Update the state of the game based on the next guess that is provided.
	 * 
	 * @param game
	 *            The game to be updated.
	 * @param nextGuess
	 *            The next character to be played in game.
	 * @return updated game.
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{character}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Game updateGame(@RequestBody(required = true) Game game, @PathVariable(value = "character") char nextGuess) {
		Game updatedGame = gameService.updateGame(game, nextGuess);
		return updatedGame;
	}

	/**
	 * Save a game.
	 * 
	 * @param game
	 *            The game to be saved
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Game saveGame(@RequestBody(required = true) Game game) {
		Game saveGame = gameService.saveGame(game);
		return saveGame;
	}

}
