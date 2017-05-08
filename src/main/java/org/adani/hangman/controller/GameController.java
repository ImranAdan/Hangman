package org.adani.hangman.controller;

import java.util.List;

import org.adani.hangman.model.Game;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.RestController;

@RestController("/game")
public class GameController {

	// Management 
	
	public List<Game> currentGames(){
		throw new NotImplementedException("Write test!");
	} 
	
	
	// Game
	
}
