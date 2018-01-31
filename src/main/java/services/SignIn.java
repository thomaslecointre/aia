package services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;

@Path("/signin")
public class SignIn {

    private KeyGenerator keyGenerator;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response verifyLogin(@QueryParam("username") String username, @QueryParam("password") String password) {

        return null;
    }

}
