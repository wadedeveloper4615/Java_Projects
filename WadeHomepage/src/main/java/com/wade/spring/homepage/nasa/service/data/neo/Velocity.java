package com.wade.spring.homepage.nasa.service.data.neo;

public class Velocity {
	private double kilometers_per_second;
	private double kilometers_per_hour;
	private double miles_per_hour;

	public Velocity() {
		super();
	}

	public Velocity(double kilometers_per_second, double kilometers_per_hour, double miles_per_hour) {
		super();
		this.kilometers_per_second = kilometers_per_second;
		this.kilometers_per_hour = kilometers_per_hour;
		this.miles_per_hour = miles_per_hour;
	}

	public double getKilometers_per_second() {
		return kilometers_per_second;
	}

	public void setKilometers_per_second(double kilometers_per_second) {
		this.kilometers_per_second = kilometers_per_second;
	}

	public double getKilometers_per_hour() {
		return kilometers_per_hour;
	}

	public void setKilometers_per_hour(double kilometers_per_hour) {
		this.kilometers_per_hour = kilometers_per_hour;
	}

	public double getMiles_per_hour() {
		return miles_per_hour;
	}

	public void setMiles_per_hour(double miles_per_hour) {
		this.miles_per_hour = miles_per_hour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(kilometers_per_hour);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(kilometers_per_second);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(miles_per_hour);
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
		Velocity other = (Velocity) obj;
		if (Double.doubleToLongBits(kilometers_per_hour) != Double.doubleToLongBits(other.kilometers_per_hour))
			return false;
		if (Double.doubleToLongBits(kilometers_per_second) != Double.doubleToLongBits(other.kilometers_per_second))
			return false;
		if (Double.doubleToLongBits(miles_per_hour) != Double.doubleToLongBits(other.miles_per_hour))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Velocity [kilometers_per_second=" + kilometers_per_second + ", kilometers_per_hour="
				+ kilometers_per_hour + ", miles_per_hour=" + miles_per_hour + "]";
	}
}
