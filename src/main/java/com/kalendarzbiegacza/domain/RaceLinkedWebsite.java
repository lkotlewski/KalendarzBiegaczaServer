package com.kalendarzbiegacza.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Łukasz on 2018-04-18.
 */
@Entity
public class RaceLinkedWebsite {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    String url;

    int raceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }
}
