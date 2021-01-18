package com.wade.spring.homepage.nasa.service.data.neo;

public class CloseApproachDataObject {
	private String close_approach_date;
	private String close_approach_date_full;
	private long epoch_date_close_approach;
	private Velocity relative_velocity;
	private Distance miss_distance;
	private String orbiting_body;

	public CloseApproachDataObject() {
		super();
	}

	public CloseApproachDataObject(String close_approach_date, String close_approach_date_full,
			long epoch_date_close_approach, Velocity relative_velocity, Distance miss_distance, String orbiting_body) {
		super();
		this.close_approach_date = close_approach_date;
		this.close_approach_date_full = close_approach_date_full;
		this.epoch_date_close_approach = epoch_date_close_approach;
		this.relative_velocity = relative_velocity;
		this.miss_distance = miss_distance;
		this.orbiting_body = orbiting_body;
	}

	public String getClose_approach_date() {
		return close_approach_date;
	}

	public void setClose_approach_date(String close_approach_date) {
		this.close_approach_date = close_approach_date;
	}

	public String getClose_approach_date_full() {
		return close_approach_date_full;
	}

	public void setClose_approach_date_full(String close_approach_date_full) {
		this.close_approach_date_full = close_approach_date_full;
	}

	public long getEpoch_date_close_approach() {
		return epoch_date_close_approach;
	}

	public void setEpoch_date_close_approach(long epoch_date_close_approach) {
		this.epoch_date_close_approach = epoch_date_close_approach;
	}

	public Velocity getRelative_velocity() {
		return relative_velocity;
	}

	public void setRelative_velocity(Velocity relative_velocity) {
		this.relative_velocity = relative_velocity;
	}

	public Distance getMiss_distance() {
		return miss_distance;
	}

	public void setMiss_distance(Distance miss_distance) {
		this.miss_distance = miss_distance;
	}

	public String getOrbiting_body() {
		return orbiting_body;
	}

	public void setOrbiting_body(String orbiting_body) {
		this.orbiting_body = orbiting_body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((close_approach_date == null) ? 0 : close_approach_date.hashCode());
		result = prime * result + ((close_approach_date_full == null) ? 0 : close_approach_date_full.hashCode());
		result = prime * result + (int) (epoch_date_close_approach ^ (epoch_date_close_approach >>> 32));
		result = prime * result + ((miss_distance == null) ? 0 : miss_distance.hashCode());
		result = prime * result + ((orbiting_body == null) ? 0 : orbiting_body.hashCode());
		result = prime * result + ((relative_velocity == null) ? 0 : relative_velocity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CloseApproachDataObject other = (CloseApproachDataObject) obj;
		if (close_approach_date == null) {
			if (other.close_approach_date != null)
				return false;
		} else if (!close_approach_date.equals(other.close_approach_date))
			return false;
		if (close_approach_date_full == null) {
			if (other.close_approach_date_full != null)
				return false;
		} else if (!close_approach_date_full.equals(other.close_approach_date_full))
			return false;
		if (epoch_date_close_approach != other.epoch_date_close_approach)
			return false;
		if (miss_distance == null) {
			if (other.miss_distance != null)
				return false;
		} else if (!miss_distance.equals(other.miss_distance))
			return false;
		if (orbiting_body == null) {
			if (other.orbiting_body != null)
				return false;
		} else if (!orbiting_body.equals(other.orbiting_body))
			return false;
		if (relative_velocity == null) {
			if (other.relative_velocity != null)
				return false;
		} else if (!relative_velocity.equals(other.relative_velocity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CloseApproachDataObject [close_approach_date=" + close_approach_date + ", close_approach_date_full="
				+ close_approach_date_full + ", epoch_date_close_approach=" + epoch_date_close_approach
				+ ", relative_velocity=" + relative_velocity + ", miss_distance=" + miss_distance + ", orbiting_body="
				+ orbiting_body + "]";
	}
}
