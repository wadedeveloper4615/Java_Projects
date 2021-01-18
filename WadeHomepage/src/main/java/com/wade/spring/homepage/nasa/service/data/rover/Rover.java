package com.wade.spring.homepage.nasa.service.data.rover;

public class Rover {
	private long id;
	private String name;
	private String landing_date;
	private String launch_date;
	private String status;

	public Rover() {
		super();
	}

	public Rover(long id, String name, String landing_date, String launch_date, String status) {
		super();
		this.id = id;
		this.name = name;
		this.landing_date = landing_date;
		this.launch_date = launch_date;
		this.status = status;
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

	public String getLanding_date() {
		return landing_date;
	}

	public void setLanding_date(String landing_date) {
		this.landing_date = landing_date;
	}

	public String getLaunch_date() {
		return launch_date;
	}

	public void setLaunch_date(String launch_date) {
		this.launch_date = launch_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((landing_date == null) ? 0 : landing_date.hashCode());
		result = prime * result + ((launch_date == null) ? 0 : launch_date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Rover other = (Rover) obj;
		if (id != other.id)
			return false;
		if (landing_date == null) {
			if (other.landing_date != null)
				return false;
		} else if (!landing_date.equals(other.landing_date))
			return false;
		if (launch_date == null) {
			if (other.launch_date != null)
				return false;
		} else if (!launch_date.equals(other.launch_date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rover [id=" + id + ", name=" + name + ", landing_date=" + landing_date + ", launch_date=" + launch_date
				+ ", status=" + status + "]";
	}
}
