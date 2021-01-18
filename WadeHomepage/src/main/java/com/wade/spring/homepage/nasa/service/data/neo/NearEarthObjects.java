package com.wade.spring.homepage.nasa.service.data.neo;

import java.util.Map;

public class NearEarthObjects {
	private Links links;
	private int element_count;
	private Map<String, Object> near_earth_objects;

	public NearEarthObjects() {
		super();
	}

	public NearEarthObjects(Links links, int element_count, Map<String, Object> near_earth_objects) {
		super();
		this.links = links;
		this.element_count = element_count;
		this.near_earth_objects = near_earth_objects;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public int getElement_count() {
		return element_count;
	}

	public void setElement_count(int element_count) {
		this.element_count = element_count;
	}

	public Map<String, Object> getNear_earth_objects() {
		return near_earth_objects;
	}

	public void setNear_earth_objects(Map<String, Object> near_earth_objects) {
		this.near_earth_objects = near_earth_objects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + element_count;
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((near_earth_objects == null) ? 0 : near_earth_objects.hashCode());
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
		NearEarthObjects other = (NearEarthObjects) obj;
		if (element_count != other.element_count)
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (near_earth_objects == null) {
			if (other.near_earth_objects != null)
				return false;
		} else if (!near_earth_objects.equals(other.near_earth_objects))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NearEarthObjects [links=" + links + ", element_count=" + element_count + ", near_earth_objects="
				+ near_earth_objects + "]";
	}
}
