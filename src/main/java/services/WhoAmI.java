package services;

import com.google.gson.Gson;
import filters.JWTTokenNeeded;
import persistence.Activities_db;
import persistence.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;

@Path("/whoami")
public class WhoAmI {
    @Context
    private SecurityContext securityContext;

    @GET
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response userInfo() {
        String username = securityContext.getUserPrincipal().getName();
        try {
            User user = Activities_db.getUser(username);
            return Response.status(200).entity(new Gson().toJson(user)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
