package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/whoami")
public class WhoAmI {
    @GET
    public String userInfo() {
        // id, first name, last name?
        return null;
    }
}
