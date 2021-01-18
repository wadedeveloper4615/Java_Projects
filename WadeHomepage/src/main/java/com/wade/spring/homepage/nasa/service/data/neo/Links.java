package com.wade.spring.homepage.nasa.service.data.neo;

public class Links {
	private String next;
	private String prev;
	private String self;

	public Links() {
		super();
	}

	public Links(String next, String prev, String self) {
		super();
		this.next = next;
		this.prev = prev;
		this.self = self;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((prev == null) ? 0 : prev.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
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
		Links other = (Links) obj;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (prev == null) {
			if (other.prev != null)
				return false;
		} else if (!prev.equals(other.prev))
			return false;
		if (self == null) {
			if (other.self != null)
				return false;
		} else if (!self.equals(other.self))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Links [next=" + next + ", prev=" + prev + ", self=" + self + "]";
	}

}
