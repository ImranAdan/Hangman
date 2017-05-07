package org.adani.hangman.repository;

import org.adani.hangman.model.Game;
import org.adani.hangman.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

}
