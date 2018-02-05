package filters;

import java.security.Principal;

public class AIAPrincipal implements Principal {

    private String name;
    private String role;

    public AIAPrincipal(String subject, String role) {
        this.name = subject;
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
