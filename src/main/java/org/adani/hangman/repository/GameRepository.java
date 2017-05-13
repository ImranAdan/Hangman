package org.adani.hangman.repository;

import java.util.List;

import org.adani.hangman.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

	public List<Game> findByGameOver(boolean isGameOver);
}
