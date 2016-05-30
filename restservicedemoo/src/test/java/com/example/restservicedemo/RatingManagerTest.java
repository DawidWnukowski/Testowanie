package com.example.restservicedemo;

import com.example.restservicedemo.domain.Rating;
import com.example.restservicedemo.service.RatingManager;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RatingManagerTest {
    RatingManager rm = new RatingManager();

    @Test
    public void addRating() {
        Rating r = new Rating();
        r.setVal(10);
        r.setGameId(0);

        assertEquals(1, rm.addRating(r));
    }

    @Test
    public void getRatingsByGameId() {
        rm.clearRatings();

        Rating r = new Rating();
        r.setVal(10);
        r.setGameId(0);
        assertEquals(1, rm.addRating(r));

        Rating r2 = new Rating();
        r2.setVal(20);
        r2.setGameId(1);
        assertEquals(1, rm.addRating(r2));

        Rating r3 = new Rating();
        r3.setVal(30);
        r3.setGameId(1);
        assertEquals(1, rm.addRating(r3));

        List<Rating> list = rm.getRatingsByGameId(1l);
        assertEquals(2, list.size());

        assertEquals(20, list.get(0).getVal());
        assertEquals(30, list.get(1).getVal());
    }

    @Test
    public void clearRatings() {
        rm.clearRatings();

        Rating r = new Rating();
        r.setVal(10);
        r.setGameId(0);
        assertEquals(1, rm.addRating(r));

        Rating r2 = new Rating();
        r2.setVal(20);
        r2.setGameId(1);
        assertEquals(1, rm.addRating(r2));

        Rating r3 = new Rating();
        r3.setVal(30);
        r3.setGameId(1);
        assertEquals(1, rm.addRating(r3));

        List<Rating> list = rm.getRatingsByGameId(1l);
        assertEquals(2, list.size());

        rm.clearRatings();

        list = rm.getRatingsByGameId(1l);
        assertEquals(0, list.size());
    }
}
