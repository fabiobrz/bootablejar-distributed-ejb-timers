package org.jboss.eap.qe.examples.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Timer expiration base implementation.
 */
public class TimerExpiration implements Serializable {

	private static final long serialVersionUID = -6807753176066577450L;

	private Long id = null;
	private String executor;
	private String name;
	private String info;
	private Instant timestamp;

	public TimerExpiration() {
		this(null, null, null, null);
	}

	public TimerExpiration(String executor, String name, String info, Instant timestamp) {
		super();
		this.executor = executor;
		this.name = name;
		this.info = info;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TimerExpiration that = (TimerExpiration) o;
		return executor.equals(that.executor) && name.equals(that.name) && info.equals(that.info)
				&& timestamp.equals(that.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(executor, name, info, timestamp);
	}

	@Override
	public String toString() {
		return "TimerExpiration{" +
				"id=" + id +
				", executor='" + executor + '\'' +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				", timestamp=" + timestamp +
				'}';
	}
}
