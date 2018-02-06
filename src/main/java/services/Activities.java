package services;

import com.google.gson.Gson;
import filters.JWTTokenNeeded;
import persistence.Activities_db;
import persistence.Activity;
import persistence.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/activities")
public class Activities {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listActivities() {
        List<Session> sessions = Activities_db.getAllSessions();
        if (sessions.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No sessions found.").build();
        } else {
            return Response.status(Response.Status.FOUND).entity(new Gson().toJson(sessions)).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActivityFromId(@PathParam("id") String id) {
        try {
            Activity activity = Activities_db.getActivity(Integer.parseInt(id));
            if (activity != null) {
                return Response.status(Response.Status.FOUND).entity(new Gson().toJson(activity)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No activity found at for this id.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/properties")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPropertiesFromIdentifiedActivity(@PathParam("id") String id) {
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
