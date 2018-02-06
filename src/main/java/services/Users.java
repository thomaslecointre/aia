package services;

import com.google.gson.Gson;
import filters.JWTTokenNeeded;
import persistence.Activities_db;
import persistence.Session;
import persistence.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class Users {

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() {
        try {
            List<User> users = Activities_db.getAllUsers();
            if (!users.isEmpty()) {
                return Response.status(Response.Status.FOUND).entity(new Gson().toJson(users)).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No users found.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response createUser(@QueryParam("username") String username, @QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
        try {
            if (username == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{username} parameter non existant.").build();
            }
            if (firstname == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{firstname} parameter non existant.").build();
            }
            if (lastname == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{lastname} parameter non existant.").build();
            }

            boolean sqlError = Activities_db.createUser(username, firstname, lastname);
            if (!sqlError) {
                return Response.status(Response.Status.CREATED).entity("User created successfully.").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("User creation failed.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        try {
            User user = Activities_db.getUser(Integer.parseInt(id));
            if (user != null) {
                return Response.status(Response.Status.FOUND).entity(new Gson().toJson(user)).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No user found.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/sessions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessions(@PathParam("id") String id) {
        try {
            List<Session> sessions = Activities_db.getAllSessionsof(Integer.parseInt(id));
            if (!sessions.isEmpty()) {
                return Response.status(Response.Status.FOUND).entity(new Gson().toJson(sessions)).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No sessions found.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/activities/{ida}/sessions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSessionsFromActivity(@PathParam("id") String id, @PathParam("ida") String ida) {
        try {
            List<Session> sessions = Activities_db.getActivitiesByIdof(Integer.parseInt(id), Integer.parseInt(ida));
            if (!sessions.isEmpty()) {
                return Response.status(Response.Status.FOUND).entity(new Gson().toJson(sessions)).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).entity("No sessions found.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @JWTTokenNeeded
    public Response deleteUser(@QueryParam("username") String username) {
        try {
            boolean sqlError = Activities_db.removeUser(username);
            if (!sqlError) {
                return Response.status(Response.Status.OK).entity("User removed successfully.").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("User removal failed.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
