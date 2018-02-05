package filters;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class AIAContext implements SecurityContext{

    private AIAPrincipal principal;
    private boolean isSecure;

    public AIAContext(AIAPrincipal principal, boolean isSecure) {
        this.principal = principal;
        this.isSecure = isSecure;
    }


    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }
}
