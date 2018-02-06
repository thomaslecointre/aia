package services;

import com.google.gson.Gson;
import filters.JWTTokenNeeded;
import persistence.Activities_db;
import persistence.Activity;
import persistence.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

@Path("/sessions")
public class Sessions {

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionFromId(@PathParam("id") String id) {
        try {
            if (id == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{id} parameter non existant.").build();
            }
            return Response.status(Response.Status.OK).entity(new Gson().toJson(Activities_db.getAllSessionsof(Integer.parseInt(id)))).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response createSession(@QueryParam("activityName") String activityName,@QueryParam("date")String date) { // Parameters?
        if (activityName == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{activityName} parameter non existant.").build();
        }
        if (date == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{date} parameter non existant.").build();
        }
        String username = securityContext.getUserPrincipal().getName();
        try {
            User user = Activities_db.getUser(username);
            Activity activity = Activities_db.getActivity(activityName);
            boolean sqlError = Activities_db.createSession(user.getId(), activity.getId(), date);
            if (!sqlError) {
                return Response.status(Response.Status.OK).entity("Session " + activityName + " of " + username + " created successfully").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Session creation failed").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response modifySession(@QueryParam("id") String id) {
        // TODO
        return null;
    }

    @PUT
    @Path("/{id}/properties/{idp}")
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response modifyPropertyFromId(@QueryParam("id") String id, @QueryParam("idp") String idp) {
        // TODO
        return null;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProperty(@QueryParam("id") String id) {
        // TODO
        return null;
    }
}
