package filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import persistence.Authentification_db;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    private String message_error = "";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        boolean isSecured = false;
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null) {
            if (authorizationHeader != null) {
                // Validate the token
                Jws<Claims> claims = null;
                try {
                    claims = validateToken(authorizationHeader);
                    AIAPrincipal principal = buildPrincipal(claims);
                    if (principal != null) {
                        AIAContext context = new AIAContext(principal, requestContext.getSecurityContext().isSecure());
                        requestContext.setSecurityContext(context);
                        isSecured = true;
                    }
                } catch (SQLException e) {
                    requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
                }
            }
        }

        if (!isSecured) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(message_error).build());
        }
    }

    private AIAPrincipal buildPrincipal(Jws<Claims> claims) {
        return new AIAPrincipal(claims.getBody().getSubject(), (String) claims.getBody().get("role"));
    }

    private Jws<Claims> validateToken(String token) throws SQLException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(AIAKey.getKey()).parseClaimsJws(token);
        String role = (String) claims.getBody().get("role");
        String username = claims.getBody().getSubject();

        boolean userHasRole = false;

        userHasRole = Authentification_db.checkRole(username, role);

        if (!userHasRole) {
            message_error = "Bad token: Role does not coincide";
            return null;
        }

        Date now = Date.from(Instant.now());
        Date issuedAt = claims.getBody().getIssuedAt();
        Date expires = claims.getBody().getExpiration();
        if (!now.after(issuedAt) || !now.before(expires)) {
            message_error = "Bad token: token is de-synchronised";
            return null;
        }

        return claims;
    }
}
