package com.example.restservicedemo.service;

import com.example.restservicedemo.domain.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameManager extends Manager{
    //private Connection connection;

    //private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
    private static final String CREATE_TABLE_game =
            "CREATE TABLE game(id bigint GENERATED BY DEFAULT AS IDENTITY, title varchar(32), price DOUBLE)";

    private PreparedStatement addGameStmt;
    private PreparedStatement removeGameStmt;
    private PreparedStatement deleteAllGamesStmt;
    private PreparedStatement getAllGamesStmt;
    private PreparedStatement getGameByIdStmt;

    private Statement statement;

    public GameManager() {
        try {
            //connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null,
                    null);
            boolean tableExists = false;
            while (rs.next()) {
                if ("Game".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }

            if (!tableExists)
                statement.executeUpdate(CREATE_TABLE_game);

            addGameStmt = connection
                    .prepareStatement("INSERT INTO game (id, title, price) VALUES (?, ?, ?)");
            removeGameStmt = connection
                    .prepareStatement("DELETE FROM game where id = ?");
            deleteAllGamesStmt = connection
                    .prepareStatement("DELETE FROM game");
            getAllGamesStmt = connection
                    .prepareStatement("SELECT id, title, price FROM game");
            getGameByIdStmt = connection
                    .prepareStatement("SELECT id, title, price FROM game where id = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        return connection;
    }

    public void clearGames() {
        try {
            deleteAllGamesStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addGame(Game game) {
        int count = 0;
        try {
            addGameStmt.setLong(1, game.getId());
            addGameStmt.setString(2, game.getTitle());
            addGameStmt.setDouble(3, game.getPrice());
            count = addGameStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int removeGame(Game game) {
        int count = 0;
        try {
            removeGameStmt.setLong(1, game.getId());
            count = removeGameStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<Game>();

        try {
            ResultSet rs = getAllGamesStmt.executeQuery();

            while (rs.next()) {
                Game g = new Game();
                g.setId(rs.getInt("id"));
                g.setTitle(rs.getString("title"));
                g.setPrice(rs.getDouble("price"));
                games.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public Game getGame(Long id) {
        Game g = new Game();
        try {
            getGameByIdStmt.setLong(1, id);
            ResultSet rs = getGameByIdStmt.executeQuery();

            while (rs.next()) {
                g.setId(rs.getInt("id"));
                g.setTitle(rs.getString("title"));
                g.setPrice(rs.getDouble("price"));
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
}
