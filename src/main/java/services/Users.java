package services;

import com.google.gson.Gson;
import persistence.Activities_db;
import persistence.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class Users {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() {

        try {
            return Response.status(200).entity(new Gson().toJson(Activities_db.getAllUsers())).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @POST
    public Response createUser(@QueryParam("username") String username, @QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
        try {
            Activities_db.createUser(username, firstname, lastname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        try {
            return Response.status(200).entity(new Gson().toJson(Activities_db.getUser(id))).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}/sessions")
    public Response getSessions(@PathParam("id") String id) {
        List<Session> l_session = null;
        try {
            l_session = Activities_db.getAllSessionsof(Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (l_session != null) {
            return Response.status(200).entity(new Gson().toJson(l_session)).build();
        }
        return Response.status(404).entity("404 error").build();
    }

    @GET
    @Path("/{id}/activities/{ida}/sessions")
    public Response getSessionsFromActivity(@PathParam("id") String id, @PathParam("ida") String ida) {

        try {
            return Response.status(200).entity(new Gson().toJson(Activities_db.getActivitiesByIdof(Integer.parseInt(id), Integer.parseInt(ida)))).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

}
