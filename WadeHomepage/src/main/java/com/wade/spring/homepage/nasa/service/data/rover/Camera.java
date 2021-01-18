package com.wade.spring.homepage.nasa.service.data.rover;

public class Camera {
	private long id;
	private String name;
	private String rover_id;
	private String full_name;

	public Camera() {
		super();
	}

	public Camera(long id, String name, String rover_id, String full_name) {
		super();
		this.id = id;
		this.name = name;
		this.rover_id = rover_id;
		this.full_name = full_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRover_id() {
		return rover_id;
	}

	public void setRover_id(String rover_id) {
		this.rover_id = rover_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((full_name == null) ? 0 : full_name.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rover_id == null) ? 0 : rover_id.hashCode());
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
		Camera other = (Camera) obj;
		if (full_name == null) {
			if (other.full_name != null)
				return false;
		} else if (!full_name.equals(other.full_name))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rover_id == null) {
			if (other.rover_id != null)
				return false;
		} else if (!rover_id.equals(other.rover_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Camera [id=" + id + ", name=" + name + ", rover_id=" + rover_id + ", full_name=" + full_name + "]";
	}

}
