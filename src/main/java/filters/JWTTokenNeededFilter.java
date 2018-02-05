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
import java.time.Instant;
import java.util.Date;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        boolean isSecured = false;
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null) {
            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();
            if (token != null) {
                // Validate the token
                Jws<Claims> claims = validateToken(token);
                AIAPrincipal principal = buildPrincipal(claims);
                if (principal != null) {
                    AIAContext context = new AIAContext(principal, requestContext.getSecurityContext().isSecure());
                    requestContext.setSecurityContext(context);
                    isSecured = true;
                }
            }
        }

        if (!isSecured) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private AIAPrincipal buildPrincipal(Jws<Claims> claims) {
        return new AIAPrincipal(claims.getBody().getSubject(), (String) claims.getHeader().get("role"));
    }

    private Jws<Claims> validateToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(AIAKey.key).parseClaimsJws(token);
        String role = (String) claims.getHeader().get("scope");
        String username = claims.getBody().getSubject();

        boolean userHasRole = Authentification_db.checkRole(username, role);

        if (!userHasRole) {
            return null;
        }

        Date now = Date.from(Instant.now());
        Date issuedAt = claims.getBody().getIssuedAt();
        Date expires = claims.getBody().getExpiration();
        if (!now.after(issuedAt) || !now.before(expires)) {
            return null;
        }

        return claims;
    }
}
