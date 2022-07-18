package org.jboss.eap.qe.examples;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * Expose a Timer service REST APIs
 *
 * Uses {@link RecurringTimerService} to perform EJB timer related operations
 */
@Path("/timer")
public class TimerManagementEndpoint {

    private static final Integer RECURRING_TIMER_BATCH_SIZE = 10;
    private static final Long RECURRING_TIMER_INITIAL_DELAY = 10 * 1_000L;
    private static final Long RECURRING_TIMER_EXPIRATION_TIMEOUT = 10 * 1_000L;
    private static final String RECURRING_TIMER_APPLICATION_INFO = TimerManagementEndpoint.class.getName();

    @EJB
    private RecurringTimerService recurringTimerService;

    @GET
    @Path("/batch-of-interval")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBatchOfIntervalTimer(
            @QueryParam("size") Integer size,
            @QueryParam("initialDelay") Long initialDelay,
            @QueryParam("expirationInterval") Long expirationInterval,
            @QueryParam("applicationInfo") String applicationInfo) {
        int created = recurringTimerService.createBatchOfIntervalTimer(
                size == null ? RECURRING_TIMER_BATCH_SIZE : size,
                initialDelay == null ? RECURRING_TIMER_INITIAL_DELAY : initialDelay,
                expirationInterval == null ? RECURRING_TIMER_EXPIRATION_TIMEOUT : expirationInterval,
                applicationInfo == null ? RECURRING_TIMER_APPLICATION_INFO : applicationInfo);
        return Response.ok()
                .entity(String.format("{\"size\": %d}", created))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @DELETE
    @Path("/batch-of-interval/{applicationInfo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBatchOfIntervalTimer(@PathParam("applicationInfo") String applicationInfo) {
        final int deleted = recurringTimerService.cancelBatchOfIntervalTimer(applicationInfo);
        if (deleted > 0) {
            return Response
                    .ok()
                    .entity(String.format("{\"size\": %d}", deleted))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}