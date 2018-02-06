package services;

import com.google.gson.Gson;
import filters.JWTTokenNeeded;
import persistence.Activities_db;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/sessions")
public class Sessions {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionFromId(@PathParam("id") String id) {
        try {
            return Response.status(Response.Status.OK).entity(new Gson().toJson(Activities_db.getAllSessionsof(Integer.parseInt(id)))).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response createSession(@QueryParam("id") String id) { // Parameters?
        return null;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response modifySession(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @PUT
    @Path("/{id}/properties/{idp}")
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response modifyPropertyFromId(@PathParam("id") String id, @PathParam("idp") String idp) {
        // TODO
        return null;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProperty(@PathParam("id") String id) {
        // TODO
        return null;
    }
}
