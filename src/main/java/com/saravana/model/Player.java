package com.saravana.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * {
 * playerId : "",
 * name : "",
 * addedOn : "",
 * updatedOn : ""
 * }
 */

@Document
public class Player {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ObjectId id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ObjectId gameId;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
