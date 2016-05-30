package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rating {

    private long id;
    private int val;
    private long gameId;

    public Rating(long id, int val, int gameId) {
        super();
        this.id = id;
        this.val = val;
        this.gameId = gameId;
    }

    public Rating() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
}
