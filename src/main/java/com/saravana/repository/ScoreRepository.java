package com.saravana.repository;

import com.saravana.model.Score;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends MongoRepository<Score, ObjectId> {

    List<Score> findAllByGameId(ObjectId gameId);

}
