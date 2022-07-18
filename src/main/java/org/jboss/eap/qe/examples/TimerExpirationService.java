package org.jboss.eap.qe.examples;

import org.jboss.eap.qe.examples.model.TimerExpiration;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.Instant;
import java.util.logging.Logger;

/**
 * A timer expiration service that prints a timer expiration data.
 */
@Singleton
@Startup
public class TimerExpirationService {

	private static final Logger logger = Logger.getLogger(TimerExpirationService.class.getName());

	public void saveTimerExpiration(final String executor, final String name, final String info, final Instant expirationTime) {
		// create data
		TimerExpiration timerExpiration = new TimerExpiration(
				executor,
				name,
				info,
				expirationTime);
		// save the new timer expiration data
		logger.info("--->>> Here's the timer expiration data: " + timerExpiration.toString());
	}
}
