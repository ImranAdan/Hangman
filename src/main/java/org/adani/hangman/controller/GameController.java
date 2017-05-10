package org.adani.hangman.controller;

import java.util.List;

import org.adani.hangman.model.Game;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/game")
public class GameController {

	// Management 
	
	@RequestMapping(method = RequestMethod.GET, value = "/current/games", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Game> getCurrentGames(){
		throw new NotImplementedException("Write test!");
	} 
	
	
	// Game
	
	@RequestMapping(method = RequestMethod.GET, value = "/current/games/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Game> getGame(){
		throw new NotImplementedException("Write test!");
	} 
	
}
