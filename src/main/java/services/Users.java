package services;

import com.google.gson.Gson;
import persistence.Activities_BDD;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class Users {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() {
        Activities_BDD bdd= new Activities_BDD("");

        return Response.status(200).entity(new Gson().toJson(bdd.getAllUsers())).build();
    }

    @POST
    public Response createUser(@QueryParam("username") String username, @QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
        // TODO
        return null;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @GET
    @Path("/{id}/sessions")
    public Response getSessions(@PathParam("id") String id) {
        // TODO
        return null;
    }

    @GET
    @Path("/{id}/activities/{ida}/sessions")
    public Response getSessionsFromActivity(@PathParam("id") String id, @PathParam("ida") String ida) {
        // TODO
        return null;
    }
}
