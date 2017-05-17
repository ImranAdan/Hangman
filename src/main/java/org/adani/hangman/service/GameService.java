package org.adani.hangman.service;

import java.util.List;

import org.adani.hangman.engine.GameEngine;
import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.adani.hangman.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private GameEngine gameEngine;
	
	public List<Game> getCurrentGames() {
		List<Game> currentGames = gameRepo.findByGameOver(false);
		return currentGames;
	}
	
	public Game createGame(Player player){
		Game game = Game.newGame(player, gameEngine.getWord());
		LOGGER.info("New Game: {}", game);
		return gameRepo.save(game);
	}
	
	public Game getGame(String id){
		return gameRepo.findOne(id);
	}
	
	public Game saveGame(Game g) {
		return gameRepo.save(g);
	}
	
	public Game updateGame(String id, char nextGuess){
		Game g = gameRepo.findOne(id);
		Game updatedGame = gameEngine.updateGameState(g, nextGuess);
		Game savedGame = saveGame(updatedGame);
		return savedGame;
	}

}
