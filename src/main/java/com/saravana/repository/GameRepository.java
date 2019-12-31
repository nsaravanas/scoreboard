package com.saravana.repository;

import com.saravana.model.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<Game, ObjectId> {

    Optional<Game> findByGameId(Integer gameNo);

    Optional<Game> findTopByOrderByIdDesc();

}
