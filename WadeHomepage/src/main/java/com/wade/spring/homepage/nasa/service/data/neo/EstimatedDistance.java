package com.wade.spring.homepage.nasa.service.data.neo;

public class EstimatedDistance {
	private double estimated_diameter_min;
	private double estimated_diameter_max;

	public EstimatedDistance() {
		super();
	}

	public EstimatedDistance(double estimated_diameter_min, double estimated_diameter_max) {
		super();
		this.estimated_diameter_min = estimated_diameter_min;
		this.estimated_diameter_max = estimated_diameter_max;
	}

	public double getEstimated_diameter_min() {
		return estimated_diameter_min;
	}

	public void setEstimated_diameter_min(double estimated_diameter_min) {
		this.estimated_diameter_min = estimated_diameter_min;
	}

	public double getEstimated_diameter_max() {
		return estimated_diameter_max;
	}

	public void setEstimated_diameter_max(double estimated_diameter_max) {
		this.estimated_diameter_max = estimated_diameter_max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(estimated_diameter_max);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(estimated_diameter_min);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		EstimatedDistance other = (EstimatedDistance) obj;
		if (Double.doubleToLongBits(estimated_diameter_max) != Double.doubleToLongBits(other.estimated_diameter_max))
			return false;
		if (Double.doubleToLongBits(estimated_diameter_min) != Double.doubleToLongBits(other.estimated_diameter_min))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstimatedDiameter [estimated_diameter_min=" + estimated_diameter_min + ", estimated_diameter_max="
				+ estimated_diameter_max + "]";
	}

}
