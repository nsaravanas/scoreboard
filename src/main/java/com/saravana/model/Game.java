package com.saravana.model;

import com.saravana.common.GameType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


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
    private ObjectId id;

    private Integer gameId;

    private GameType gameType;

    private List<Player> players;

    private List<Score> scores;

    private Date addedOn;

    private Date updatedOn;

    private int maxScore;

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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
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
