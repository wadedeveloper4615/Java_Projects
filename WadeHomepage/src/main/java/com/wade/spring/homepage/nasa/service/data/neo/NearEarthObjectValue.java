package com.wade.spring.homepage.nasa.service.data.neo;

import java.util.List;

public class NearEarthObjectValue {
	private Links links;
	private String id;
	private String neo_reference_id;
	private String name;
	private String nasa_jpl_url;
	private double absolute_magnitude_h;
	private EstimatedDistance estimated_diameter;
	private boolean is_potentially_hazardous_asteroid;
	private List<CloseApproachDataObject> close_approach_data;
	private boolean is_sentry_object;

	public NearEarthObjectValue() {
		super();
	}

	public NearEarthObjectValue(Links links, String id, String neo_reference_id, String name, String nasa_jpl_url,
			double absolute_magnitude_h, EstimatedDistance estimated_diameter,
			boolean is_potentially_hazardous_asteroid, List<CloseApproachDataObject> close_approach_data,
			boolean is_sentry_object) {
		super();
		this.links = links;
		this.id = id;
		this.neo_reference_id = neo_reference_id;
		this.name = name;
		this.nasa_jpl_url = nasa_jpl_url;
		this.absolute_magnitude_h = absolute_magnitude_h;
		this.estimated_diameter = estimated_diameter;
		this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
		this.close_approach_data = close_approach_data;
		this.is_sentry_object = is_sentry_object;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNeo_reference_id() {
		return neo_reference_id;
	}

	public void setNeo_reference_id(String neo_reference_id) {
		this.neo_reference_id = neo_reference_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNasa_jpl_url() {
		return nasa_jpl_url;
	}

	public void setNasa_jpl_url(String nasa_jpl_url) {
		this.nasa_jpl_url = nasa_jpl_url;
	}

	public double getAbsolute_magnitude_h() {
		return absolute_magnitude_h;
	}

	public void setAbsolute_magnitude_h(double absolute_magnitude_h) {
		this.absolute_magnitude_h = absolute_magnitude_h;
	}

	public EstimatedDistance getEstimated_diameter() {
		return estimated_diameter;
	}

	public void setEstimated_diameter(EstimatedDistance estimated_diameter) {
		this.estimated_diameter = estimated_diameter;
	}

	public boolean isIs_potentially_hazardous_asteroid() {
		return is_potentially_hazardous_asteroid;
	}

	public void setIs_potentially_hazardous_asteroid(boolean is_potentially_hazardous_asteroid) {
		this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
	}

	public List<CloseApproachDataObject> getClose_approach_data() {
		return close_approach_data;
	}

	public void setClose_approach_data(List<CloseApproachDataObject> close_approach_data) {
		this.close_approach_data = close_approach_data;
	}

	public boolean isIs_sentry_object() {
		return is_sentry_object;
	}

	public void setIs_sentry_object(boolean is_sentry_object) {
		this.is_sentry_object = is_sentry_object;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(absolute_magnitude_h);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((close_approach_data == null) ? 0 : close_approach_data.hashCode());
		result = prime * result + ((estimated_diameter == null) ? 0 : estimated_diameter.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (is_potentially_hazardous_asteroid ? 1231 : 1237);
		result = prime * result + (is_sentry_object ? 1231 : 1237);
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nasa_jpl_url == null) ? 0 : nasa_jpl_url.hashCode());
		result = prime * result + ((neo_reference_id == null) ? 0 : neo_reference_id.hashCode());
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
		NearEarthObjectValue other = (NearEarthObjectValue) obj;
		if (Double.doubleToLongBits(absolute_magnitude_h) != Double.doubleToLongBits(other.absolute_magnitude_h))
			return false;
		if (close_approach_data == null) {
			if (other.close_approach_data != null)
				return false;
		} else if (!close_approach_data.equals(other.close_approach_data))
			return false;
		if (estimated_diameter == null) {
			if (other.estimated_diameter != null)
				return false;
		} else if (!estimated_diameter.equals(other.estimated_diameter))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (is_potentially_hazardous_asteroid != other.is_potentially_hazardous_asteroid)
			return false;
		if (is_sentry_object != other.is_sentry_object)
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nasa_jpl_url == null) {
			if (other.nasa_jpl_url != null)
				return false;
		} else if (!nasa_jpl_url.equals(other.nasa_jpl_url))
			return false;
		if (neo_reference_id == null) {
			if (other.neo_reference_id != null)
				return false;
		} else if (!neo_reference_id.equals(other.neo_reference_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NearEarthObjectValue [links=" + links + ", id=" + id + ", neo_reference_id=" + neo_reference_id
				+ ", name=" + name + ", nasa_jpl_url=" + nasa_jpl_url + ", absolute_magnitude_h=" + absolute_magnitude_h
				+ ", estimated_diameter=" + estimated_diameter + ", is_potentially_hazardous_asteroid="
				+ is_potentially_hazardous_asteroid + ", close_approach_data=" + close_approach_data
				+ ", is_sentry_object=" + is_sentry_object + "]";
	}

}
