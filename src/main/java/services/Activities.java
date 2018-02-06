package services;

import filters.JWTTokenNeeded;
import persistence.Activities_db;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/activities")
public class Activities {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listActivities() {
        //Activities_db.getAllSession();
        return null;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActivityFromId(@PathParam("id") String id) {

        return null;
    }

    @GET
    @Path("/{id}/properties")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPropertiesFromIdentifiedActivity(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response createActivity(@QueryParam("id") String id) {
        // TODO
        return null;
    }
}
