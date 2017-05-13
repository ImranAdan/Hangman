package org.adani.hangman.service;

import java.util.List;

import org.adani.hangman.engine.GameEngine;
import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.adani.hangman.repository.GameRepository;
import org.apache.commons.lang3.NotImplementedException;
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
	
	public Game createGame(Game game){
		Game g = gameRepo.save(game);
		return g;
	}
	
	public Game getGame(String id){
		Game game = gameRepo.findOne(id);
		return game;
	}
	
	public Game saveGame(Game g) {
		Game updatedGame = gameRepo.save(g);
		return updatedGame;
	}
	
	public Game updateGame(Game g, char nextGuess){
		Game updatedGame = gameEngine.updateGameState(g, nextGuess);
		Game savedGame = saveGame(updatedGame);
		return savedGame;
	}

}
