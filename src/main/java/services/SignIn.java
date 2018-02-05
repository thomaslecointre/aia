package services;

import filters.AIAKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import persistence.Authentification_db;
import persistence.LogUser;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

@Path("/signin")
public class SignIn {

    private final long THIRTY_MINUTES_IN_SECONDS = 1800;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response verifyLogin(@QueryParam("username") String username, @QueryParam("password") String password) throws SQLException {

        if (username == null) {
            return Response.status(400).build();
        }

        if (password == null) {
            return Response.status(400).build();
        }

        LogUser logUser = Authentification_db.getLogUser(username, password);

        if (logUser != null) {
            String jws = Jwts.builder()
                    .setIssuer("aia")
                    .setSubject(username)
                    .setHeaderParam("typ","JWT")
                    .claim("role", logUser.getRole())
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plusSeconds(THIRTY_MINUTES_IN_SECONDS)))
                    .signWith(
                            SignatureAlgorithm.HS512,
                            AIAKey.getKey()
                    )
                    .compact();
            return Response.status(200).entity(jws).build();
        } else {
            return Response.status(400).build();
        }
    }

}
