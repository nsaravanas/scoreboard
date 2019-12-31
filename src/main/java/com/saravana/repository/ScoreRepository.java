package com.saravana.repository;

import com.saravana.model.Score;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends MongoRepository<Score, ObjectId> {

}
