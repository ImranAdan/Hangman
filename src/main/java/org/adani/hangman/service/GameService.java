package org.adani.hangman.service;

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

	public Player createPlayer(String name) {
		throw new NotImplementedException("Write test!");
	}

	public Player createPlayer(Player p) {
		throw new NotImplementedException("Write test!");
	}

	public Player loadPlayer(Player p) {
		throw new NotImplementedException("Write test!");
	}

	public void deletePlayer(Player p) {
		throw new NotImplementedException("Write test!");
	}
}
