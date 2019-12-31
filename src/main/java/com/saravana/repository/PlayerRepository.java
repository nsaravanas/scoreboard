package com.saravana.repository;

import com.saravana.model.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, ObjectId> {

    List<Player> findAllByGameId(ObjectId gameId);

}
