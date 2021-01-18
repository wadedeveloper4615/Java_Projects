package com.wade.spring.homepage.nasa.service.data.rover;

public class MarsPics {
	private long id;
	private long sol;
	private Camera camera;
	private String img_src;
	private String earth_date;
	private Rover rover;

	public MarsPics() {
		super();
	}

	public MarsPics(long id, long sol, Camera camera, String img_src, String earth_date, Rover rover) {
		super();
		this.id = id;
		this.sol = sol;
		this.camera = camera;
		this.img_src = img_src;
		this.earth_date = earth_date;
		this.rover = rover;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSol() {
		return sol;
	}

	public void setSol(long sol) {
		this.sol = sol;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public String getEarth_date() {
		return earth_date;
	}

	public void setEarth_date(String earth_date) {
		this.earth_date = earth_date;
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camera == null) ? 0 : camera.hashCode());
		result = prime * result + ((earth_date == null) ? 0 : earth_date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((img_src == null) ? 0 : img_src.hashCode());
		result = prime * result + ((rover == null) ? 0 : rover.hashCode());
		result = prime * result + (int) (sol ^ (sol >>> 32));
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
		MarsPics other = (MarsPics) obj;
		if (camera == null) {
			if (other.camera != null)
				return false;
		} else if (!camera.equals(other.camera))
			return false;
		if (earth_date == null) {
			if (other.earth_date != null)
				return false;
		} else if (!earth_date.equals(other.earth_date))
			return false;
		if (id != other.id)
			return false;
		if (img_src == null) {
			if (other.img_src != null)
				return false;
		} else if (!img_src.equals(other.img_src))
			return false;
		if (rover == null) {
			if (other.rover != null)
				return false;
		} else if (!rover.equals(other.rover))
			return false;
		if (sol != other.sol)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarsPics [id=" + id + ", sol=" + sol + ", camera=" + camera + ", img_src=" + img_src + ", earth_date="
				+ earth_date + ", rover=" + rover + "]";
	}

}
