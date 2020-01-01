package com.saravana.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saravana.common.GameType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * {
 * gameId : "",
 * gameType : "",
 * addedOn: "",
 * updatedOn : "",
 * maxScore: "",
 * players : [
 * {
 * playerId : "",
 * name : "",
 * addedOn : "",
 * updatedOn : ""
 * },
 * {
 * playerId : "",
 * name : "",
 * addedOn : "",
 * updatedOn : ""
 * }
 * ],
 * "scores" : [
 * {
 * score : [
 * {
 * playerId : "",
 * score : ""
 * },{
 * playerId : "",
 * score : ""
 * }
 * ],
 * addedOn : "",
 * updatedOn : ""
 * }
 * ]
 * }
 */


@Document
public class Game {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ObjectId id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer gameId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private GameType gameType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date addedOn;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedOn;

    private int maxScore = Integer.MAX_VALUE;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
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

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

}
