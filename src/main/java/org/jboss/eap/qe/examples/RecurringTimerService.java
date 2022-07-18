package org.jboss.eap.qe.examples;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Encapsulate EJB timer related operations
 */
@Stateless
public class RecurringTimerService {

	private static final Logger LOGGER = Logger.getLogger(RecurringTimerService.class.getName());
	public static final String TIMER_NAME = RecurringTimerService.class.getName();

	@Resource
	protected TimerService timerService;
	@EJB
	private TimerExpirationService timerExpirationService;

	@Timeout
	public void doExecute(Timer t) {
		LOGGER.info("Executing timer at: " + new java.util.Date().toString());
		// save the timer expiration
		try {
			final String localHostId = InetAddress.getLocalHost().toString();
			timerExpirationService.saveTimerExpiration(localHostId, TIMER_NAME, t.getInfo().toString(), Instant.now());
		} catch (UnknownHostException e) {
			throw new IllegalStateException("An error occurred while trying to get the local host name");
		}
	}

	public int createBatchOfIntervalTimer(Integer batchSize, Long initialDelay, Long expirationInterval, String applicationInfo) {
		int created;
		for (created = 0; created < batchSize; created++) {
			final String actualApplicationInfo = String.format("%s(%d)", applicationInfo, created);
			LOGGER.info(
					String.format(
							"A recurring timer will be created with the following parameters: initialDelay=%d, expirationInterval=%d, applicationInfo=%s",
							initialDelay, expirationInterval, actualApplicationInfo));
			this.timerService.createIntervalTimer(
					initialDelay,
					expirationInterval,
					new TimerConfig(actualApplicationInfo, Boolean.TRUE));
			LOGGER.info(String.format("Recurring timer successfully created in a batch of (%d).", batchSize));
		}
		return created;
	}

	public int cancelBatchOfIntervalTimer(String applicationInfo) {
		int deleted = 0;
		Collection<Timer> timers = timerService.getTimers();
		for (Timer timer : timers) {
			if ((timer.getInfo() != null) && !timer.getInfo().toString().isBlank() && timer.getInfo().toString().contains(applicationInfo)) {
				LOGGER.info(
						String.format(
								"A recurring timer matching the provided info (%s:%s) will be cancelled",
								applicationInfo, timer.getInfo().toString()));
				timer.cancel();
				deleted++;
				LOGGER.info(String.format("Recurring timer %s successfully cancelled.", applicationInfo));
				break;
			}
		}
		return deleted;
	}
}
