package com.example.restservicedemo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Game;
import com.example.restservicedemo.service.GameManager;

@Path("game")
public class GameRestService {
	private GameManager gm = new GameManager();

	@GET
	@Path("/{gameId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game getGame(@PathParam("gameId") Long id){
		return gm.getGame(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Game game) {
 		gm.addGame(game);
		return Response.status(201).entity("Game").build();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public Response addNewGame(Game game) {
		System.out.println(game);
		gm.addGame(game);
		return Response.status(201).entity("success").build();
	}

	@DELETE
	public Response clearGames(){
		gm.clearGames();
		return Response.status(200).build();
	}
}
