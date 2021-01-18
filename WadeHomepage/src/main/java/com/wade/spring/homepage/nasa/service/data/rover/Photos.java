package com.wade.spring.homepage.nasa.service.data.rover;

import java.util.List;

public class Photos {
	private List<MarsPics> photos;

	public Photos() {
		super();
	}

	public Photos(List<MarsPics> photos) {
		super();
		this.photos = photos;
	}

	public List<MarsPics> getPhotos() {
		return photos;
	}

	public void setPhotos(List<MarsPics> photos) {
		this.photos = photos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((photos == null) ? 0 : photos.hashCode());
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
		Photos other = (Photos) obj;
		if (photos == null) {
			if (other.photos != null)
				return false;
		} else if (!photos.equals(other.photos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Photos [photos=" + photos + "]";
	}
}
