package services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/sessions")
public class Sessions {

    @GET
    @Path("/{id}")
    public Response getSessionFromId(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @POST
    public Response createSession(@QueryParam("id") String id) { // Parameters?
        // TODO
        return null;
    }

    @PUT
    @Path("/{id}")
    public Response modifySession(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @PUT
    @Path("/{id}/properties/{idp}")
    public Response modifyPropertyFromId(@PathParam("id") String id, @PathParam("idp") String idp) {
        // TODO
        return null;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProperty(@PathParam("id") String id) {
        // TODO
        return null;
    }
}
