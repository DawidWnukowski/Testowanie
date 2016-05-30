package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.example.restservicedemo.domain.Rating;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RatingServiceTest {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";
    }

    @Test
    public void addRating() {
        delete("/rating").then().assertThat().statusCode(200);

        Rating r = new Rating(0, 5, 0);
        given().
            contentType(MediaType.APPLICATION_JSON).
            body(r).
        when().
            post("/rating/add").then().assertThat().statusCode(201);
    }

    //todo: mozliwe ze przesadzilem z ponizszym testem... ale zostawiam, bo moze jest dobrze :)
    @Test
    public void getRatingsByGameId() {
        delete("/rating").then().assertThat().statusCode(200);
        delete("/game").then().assertThat().statusCode(200);

        int val1 = 5, val2 = 7;

        Rating r1 = new Rating(0, val1, 0);
        given().
                contentType(MediaType.APPLICATION_JSON).
                body(r1).
                when().
                post("/rating/").then().assertThat().statusCode(201);

        Rating r2 = new Rating(1, val2, 0);
        given().
                contentType(MediaType.APPLICATION_JSON).
                body(r2).
                when().
                post("/rating/").then().assertThat().statusCode(201);

        Rating r3 = new Rating(2, 9, 1);
        given().
                contentType(MediaType.APPLICATION_JSON).
                body(r3).
                when().
                post("/rating/").then().assertThat().statusCode(201);

        JSONArray obj = null;
        try {
           obj = new JSONArray(get("/rating/0").prettyPrint());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.println("Objecto\n" + obj);

        ObjectMapper mapper = new ObjectMapper();
        List<Rating> list = new ArrayList<>();
        for (int i = 0; i < obj.length(); i++) {
            try {
                try {
                    Rating r = mapper.readValue(obj.get(i).toString(), Rating.class);
                    list.add(r);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    //System.out.println(list.get(i).getVal());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        assertThat(list.size(), is(equalTo(2)));
        assertThat(list.get(0).getVal(), is(equalTo(val1)));
        assertThat(list.get(1).getVal(), is(equalTo(val2)));
    }

    @Test
    public void removeRatings() {
        Rating r = new Rating(2, 9, 1);
        given().
                contentType(MediaType.APPLICATION_JSON).
                body(r).
                when().
                post("/rating/add").then().assertThat().statusCode(201);

        delete("/game/").then().assertThat().statusCode(200);
    }
}
