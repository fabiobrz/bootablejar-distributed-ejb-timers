package org.jboss.eap.qe.examples;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * Expose a HelloWorld service REST API
 */
@Path("/hello")
public class HelloWorldEndpoint {

    @GET
    @Produces("text/plain")
    public Response doGet() {
        return Response.ok("Hello from the WildFly distributed EJB Timers Bootable jar example!").build();
    }
}