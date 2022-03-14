package org.jboss.eap.qe.examples;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.util.logging.Logger;

/**
 * Encapsulate EJB timer related operations
 */
@Stateless
public class EjbTimerBean {

	private static final Logger logger = Logger.getLogger(EjbTimerBean.class.getName());

	@Resource
	protected TimerService timerService;

	public Timer createTimer() {
		TimerInfo ti = new TimerInfo();
		logger.info("Creating timer: " + ti.toString());

		return this.timerService.createTimer(ti.getMillisecondDelay(), ti);
	}

	@Timeout
	public void timeout(Timer t) {
		logger.info("Timeout of timer " + t.getInfo());
	}
}
