package com.saravana.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document
public class Score {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ObjectId id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ObjectId gameId;

    private Map<ObjectId, Integer> score = new HashMap<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date addedOn;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedOn;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getGameId() {
        return gameId;
    }

    public void setGameId(ObjectId gameId) {
        this.gameId = gameId;
    }

    public Map<ObjectId, Integer> getScore() {
        return score;
    }

    public void setScore(Map<ObjectId, Integer> score) {
        this.score = score;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}
