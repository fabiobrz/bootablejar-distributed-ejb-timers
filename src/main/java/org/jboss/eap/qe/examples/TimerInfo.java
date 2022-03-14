package org.jboss.eap.qe.examples;

import javax.ejb.Timer;
import java.io.Serializable;

/**
 * Serializable content that is used as the EJB timer payload (i.e.: {@link Timer#getInfo()})
 */
public class TimerInfo implements Serializable {

    private static final long serialVersionUID = -6807753176066577450L;
    private final static Long TIMER_INFO__DEFAULT_DELAY = 20000L;
    private final static String TIMER_INFO__DEFAULT_NAME = "EjbTimerBean";

    private String name;
    private Long millisecondDelay;

    public TimerInfo() {
        this(TIMER_INFO__DEFAULT_NAME, TIMER_INFO__DEFAULT_DELAY);
    }

    public TimerInfo(String name, Long millisecondDelay) {
        this.name = name;
        this.millisecondDelay = millisecondDelay;
    }

    public String getName() {
        return name;
    }

    public Long getMillisecondDelay() {
        return millisecondDelay;
    }

    @Override
    public String toString() {
        return "TimerInfo{" +
                "name='" + name + '\'' +
                ", millisecondDelay=" + millisecondDelay +
                '}';
    }
}
