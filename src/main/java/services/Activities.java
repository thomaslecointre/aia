package services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/activities")
public class Activities {

    @GET
    public Response listActivities() {
        // TODO
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
