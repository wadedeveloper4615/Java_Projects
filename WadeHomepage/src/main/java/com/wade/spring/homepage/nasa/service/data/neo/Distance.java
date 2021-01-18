package com.wade.spring.homepage.nasa.service.data.neo;

public class Distance {
	private double astronomical;
	private double lunar;
	private double kilometers;
	private double miles;

	public Distance() {
		super();
	}

	public Distance(double astronomical, double lunar, double kilometers, double miles) {
		super();
		this.astronomical = astronomical;
		this.lunar = lunar;
		this.kilometers = kilometers;
		this.miles = miles;
	}

	public double getAstronomical() {
		return astronomical;
	}

	public void setAstronomical(double astronomical) {
		this.astronomical = astronomical;
	}

	public double getLunar() {
		return lunar;
	}

	public void setLunar(double lunar) {
		this.lunar = lunar;
	}

	public double getKilometers() {
		return kilometers;
	}

	public void setKilometers(double kilometers) {
		this.kilometers = kilometers;
	}

	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) {
		this.miles = miles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(astronomical);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(kilometers);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lunar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(miles);
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
		Distance other = (Distance) obj;
		if (Double.doubleToLongBits(astronomical) != Double.doubleToLongBits(other.astronomical))
			return false;
		if (Double.doubleToLongBits(kilometers) != Double.doubleToLongBits(other.kilometers))
			return false;
		if (Double.doubleToLongBits(lunar) != Double.doubleToLongBits(other.lunar))
			return false;
		if (Double.doubleToLongBits(miles) != Double.doubleToLongBits(other.miles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Distance [astronomical=" + astronomical + ", lunar=" + lunar + ", kilometers=" + kilometers + ", miles="
				+ miles + "]";
	}
}
