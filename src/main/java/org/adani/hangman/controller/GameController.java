package org.adani.hangman.controller;

import java.util.List;

import org.adani.hangman.model.Game;
import org.adani.hangman.service.GameService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/games")
public class GameController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Game> getCurrentGames(){
		throw new NotImplementedException("Write test!");
	} 
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Game> getGame(@PathVariable(value = "id") int id){
		throw new NotImplementedException("Write test!");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Game createGame(@RequestBody(required = true) Game game){
		throw new NotImplementedException("Write test!");
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Game> updateGame(@RequestBody(required = true) Game game, @PathVariable(value = "id") char nextGuess){
		throw new NotImplementedException("Write test!");
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Game> saveGame(@RequestBody(required = true) Game game){
		throw new NotImplementedException("Write test!");
	} 
		
}
