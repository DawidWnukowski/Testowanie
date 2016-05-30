package com.example.restservicedemo.rest;

import com.example.restservicedemo.domain.Rating;
import com.example.restservicedemo.service.RatingManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("rating")
public class RatingRestService {
    private RatingManager rm = new RatingManager();

    @GET
    @Path("/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> getRatingsByGameId(@PathParam("gameId") Long id) {
        return rm.getRatingsByGameId(id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Rating r) {
        rm.addRating(r);
        return Response.status(201).entity("Rating").build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response addNewRating(Rating r) {
        System.out.println(r);
        rm.addRating(r);
        return Response.status(201).entity("success").build();
    }

    @DELETE
    public Response clearRatings(){
        rm.clearRatings();
        return Response.status(200).build();
    }
}
