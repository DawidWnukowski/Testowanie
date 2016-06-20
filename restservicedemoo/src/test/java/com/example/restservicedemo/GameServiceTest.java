package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.example.restservicedemo.domain.Game;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import javax.ws.rs.core.MediaType;

public class GameServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
	}

	@Before
	public void clearDB(){
		delete("/game/").then().assertThat().statusCode(200);
	}
	
	@Test
	public void getGame(){

		Game aGame = new Game(0, "Diablo 2", 50);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(aGame).
		when().
				post("/game/").
		then().
				assertThat().statusCode(201);

		given().
				contentType(MediaType.APPLICATION_JSON).
		when().
				get("/game/0").
		then().
				body("id", equalTo(0)).
				body("title", equalTo("Diablo 2")).
				body("price", equalTo(50f)).
				statusCode(200);

		//drugi sposob :)
		Game g = get("/game/0").as(Game.class);
		assertThat(g.getTitle(), is(equalTo("Diablo 2")));
	}
	
	@Test
	public void addGame(){

		Game aGame = new Game(2, "Diablo 3", 200);
		given().
		       contentType(MediaType.APPLICATION_JSON).
		       body(aGame).
		when().
		post("/game/add").then().assertThat().statusCode(201);
	}

	@Test
	public void removeGames() {

		Game aGame = new Game(1, "Diablo 3", 200);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(aGame).
				when().
				post("/game/add").then().assertThat().statusCode(201);

		aGame = new Game(2, "Diablo 333333", 200);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(aGame).
				when().
				post("/game/add").then().assertThat().statusCode(201);
	}

}
