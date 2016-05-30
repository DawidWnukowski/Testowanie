package com.example.restservicedemo;

import static org.junit.Assert.*;

import com.example.restservicedemo.domain.Game;
import com.example.restservicedemo.service.GameManager;
import org.junit.Test;

import java.util.List;

public class GameManagerTest {
    GameManager gm = new GameManager();

    @Test
    public void addGame() {
        Game g = new Game();
        g.setTitle("Uncharted 4");
        g.setPrice(200);

        assertEquals(1, gm.addGame(g));
    }

    //expected, actual
    @Test
    public void getGame() {
        Game g = new Game();
        g.setId(10);
        g.setTitle("Divinity");
        g.setPrice(100);
        assertEquals(1, gm.addGame(g));

        Game game = gm.getGame((long) 10);

        assertEquals(g.getId(), game.getId());
        assertEquals(g.getTitle(), game.getTitle());
        assertEquals(g.getPrice(), game.getPrice(), 0.00001);
    }

    @Test
    public void getAllGames() {
        gm.clearGames();

        List<Game> list = gm.getAllGames();
        assertEquals(0, list.size());

        Game g = new Game();
        g.setTitle("Divinity");
        g.setPrice(100);
        assertEquals(1, gm.addGame(g));

        Game g2 = new Game();
        g2.setTitle("Divinity2");
        g2.setPrice(1002);
        assertEquals(1, gm.addGame(g2));

        list = gm.getAllGames();
        assertEquals(2, list.size());

        assertEquals(list.get(0).getTitle(), g.getTitle());
        assertEquals(list.get(1).getPrice(), g2.getPrice(), 0.000001);
    }

    @Test
    public void removeGame() {
        gm.clearGames();

        Game g = new Game();
        g.setId(10);
        g.setTitle("Diablo");
        g.setPrice(100);
        assertEquals(1, gm.addGame(g));

        assertEquals(1, gm.removeGame(g));

        assertEquals(null, gm.getGame(10l).getTitle());
    }

    @Test
    public void clearGames() {
        gm.clearGames();
        List<Game> list = gm.getAllGames();
        assertEquals(0, list.size());

        Game g = new Game();
        g.setTitle("Divinity");
        g.setPrice(100);
        assertEquals(1, gm.addGame(g));

        Game g2 = new Game();
        g2.setTitle("Divinity2");
        g2.setPrice(1002);
        assertEquals(1, gm.addGame(g2));

        list = gm.getAllGames();
        assertEquals(2, list.size());

        gm.clearGames();
        list = gm.getAllGames();
        assertEquals(0, list.size());
    }
}
