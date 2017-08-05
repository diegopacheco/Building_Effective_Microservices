package com.packtpub.microservice.service;

public class Meetup {
	
	private String Name;
	private String typez;
	
	public Meetup() {}
	
	public Meetup(String name, String typez) {
		super();
		Name = name;
		this.typez = typez;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getTypez() {
		return typez;
	}
	public void setTypez(String typez) {
		this.typez = typez;
	}

	@Override
	public String toString() {
		return "Meetup [Name=" + Name + ", typez=" + typez + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((typez == null) ? 0 : typez.hashCode());
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
		Meetup other = (Meetup) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (typez == null) {
			if (other.typez != null)
				return false;
		} else if (!typez.equals(other.typez))
			return false;
		return true;
	}
	
}
