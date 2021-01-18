package com.wade.spring.homepage.nasa.service.data.neo;

public class EstimatedDiameter {
	private EstimatedDistance kilometers;
	private EstimatedDistance meters;
	private EstimatedDistance miles;
	private EstimatedDistance feet;

	public EstimatedDiameter() {
		super();
	}

	public EstimatedDiameter(EstimatedDistance kilometers, EstimatedDistance meters, EstimatedDistance miles,
			EstimatedDistance feet) {
		super();
		this.kilometers = kilometers;
		this.meters = meters;
		this.miles = miles;
		this.feet = feet;
	}

	public EstimatedDistance getKilometers() {
		return kilometers;
	}

	public void setKilometers(EstimatedDistance kilometers) {
		this.kilometers = kilometers;
	}

	public EstimatedDistance getMeters() {
		return meters;
	}

	public void setMeters(EstimatedDistance meters) {
		this.meters = meters;
	}

	public EstimatedDistance getMiles() {
		return miles;
	}

	public void setMiles(EstimatedDistance miles) {
		this.miles = miles;
	}

	public EstimatedDistance getFeet() {
		return feet;
	}

	public void setFeet(EstimatedDistance feet) {
		this.feet = feet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feet == null) ? 0 : feet.hashCode());
		result = prime * result + ((kilometers == null) ? 0 : kilometers.hashCode());
		result = prime * result + ((meters == null) ? 0 : meters.hashCode());
		result = prime * result + ((miles == null) ? 0 : miles.hashCode());
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
		EstimatedDiameter other = (EstimatedDiameter) obj;
		if (feet == null) {
			if (other.feet != null)
				return false;
		} else if (!feet.equals(other.feet))
			return false;
		if (kilometers == null) {
			if (other.kilometers != null)
				return false;
		} else if (!kilometers.equals(other.kilometers))
			return false;
		if (meters == null) {
			if (other.meters != null)
				return false;
		} else if (!meters.equals(other.meters))
			return false;
		if (miles == null) {
			if (other.miles != null)
				return false;
		} else if (!miles.equals(other.miles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstimatedDiameter [kilometers=" + kilometers + ", meters=" + meters + ", miles=" + miles + ", feet="
				+ feet + "]";
	}
}
