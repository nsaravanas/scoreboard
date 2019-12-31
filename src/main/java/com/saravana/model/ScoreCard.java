package com.saravana.model;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScoreCard {

    private ObjectId scoreCardId;

    private Map<ObjectId, Integer> scoreMap = new HashMap<>();

    private Date addedOn;

    private Date updatedOn;

    public ObjectId getScoreCardId() {
        return scoreCardId;
    }

    public void setScoreCardId(ObjectId scoreCardId) {
        this.scoreCardId = scoreCardId;
    }

    public Map<ObjectId, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<ObjectId, Integer> scoreMap) {
        this.scoreMap = scoreMap;
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
