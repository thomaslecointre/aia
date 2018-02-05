package services;

import persistence.Activities_db;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/activities")
public class Activities {

    @GET
    public Response listActivities() {
        //Activities_db.getAllSession();
        return null;
    }

    @GET
    @Path("/{id}")
    public Response getActivityFromId(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @GET
    @Path("/{id}/properties")
    public Response listPropertiesFromIdentifiedActivity(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @POST
    public Response createActivity(@QueryParam("id") String id) {
        // TODO
        return null;
    }
}
