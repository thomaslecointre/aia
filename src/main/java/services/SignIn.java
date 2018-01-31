package services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/signin")
public class SignIn {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response verifyLogin(@QueryParam("username") String username, @QueryParam("password") String password) {
        // TODO
        return null;
    }
}
