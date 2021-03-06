package com.example.restservicedemo.service;

import com.example.restservicedemo.domain.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingManager extends Manager{
    //private Connection connection;

    //private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
    private static final String CREATE_TABLE_rating =
            "CREATE TABLE rating(id bigint GENERATED BY DEFAULT AS IDENTITY, val INT, gameId bigint FOREIGN KEY references Game(id))";

    private PreparedStatement addRatingStmt;
    private PreparedStatement getRatingsByGameIdStmt;
    private PreparedStatement deleteAllRatingsStmt;

    private Statement statement;

    public RatingManager() {
        try {
            //connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null,
                    null);
            boolean tableExists = false;
            while (rs.next()) {
                if ("Rating".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }

            if (!tableExists)
                statement.executeUpdate(CREATE_TABLE_rating);

            addRatingStmt = connection
                    .prepareStatement("INSERT INTO rating (id, val, gameId) VALUES (?, ?, ?)");
            deleteAllRatingsStmt = connection
                    .prepareStatement("DELETE FROM rating");
            getRatingsByGameIdStmt = connection
                    .prepareStatement("SELECT id, val, gameId FROM rating where gameId = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        return connection;
    }

    public void clearRatings() {
        try {
            deleteAllRatingsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addRating(Rating rating) {
        int count = 0;
        try {
            addRatingStmt.setLong(1, rating.getId());
            addRatingStmt.setInt(2, rating.getVal());
            addRatingStmt.setLong(3, rating.getGameId());
            count = addRatingStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Rating> getRatingsByGameId(Long gameId) {
        List<Rating> ratings = new ArrayList<Rating>();

        try {
            getRatingsByGameIdStmt.setLong(1, gameId);
            ResultSet rs = getRatingsByGameIdStmt.executeQuery();

            while (rs.next()) {
                Rating r = new Rating();
                r.setId(rs.getInt("id"));
                r.setVal(rs.getInt("val"));
                r.setGameId(rs.getLong("gameId"));
                if(r.getGameId() == gameId)
                    ratings.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }
}
