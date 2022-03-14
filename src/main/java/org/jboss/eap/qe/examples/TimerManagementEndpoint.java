package org.jboss.eap.qe.examples;

import javax.ejb.EJB;
import javax.ejb.Timer;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

/**
 * Expose a Timer service REST APIs
 *
 * Uses {@link EjbTimerBean} to perform EJB timer related operations
 */
@Path("/timer")
public class TimerManagementEndpoint {

    private static Logger log = Logger.getLogger(TimerManagementEndpoint.class.getName());

    @EJB
    private EjbTimerBean ejbTimerBean;

    @GET
    @Path("create")
    @Produces("application/json")
    public Response doCreate() {
        log.debug("TimerManagementEndpoint.doCreate called");

        Timer t = ejbTimerBean.createTimer();

        return Response.ok(t.getInfo()).build();
    }
}