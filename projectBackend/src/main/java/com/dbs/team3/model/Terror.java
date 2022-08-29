package com.dbs.team3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Terror {
	@Id
	int id;
	String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Terror [id=" + id + ", name=" + name + "]";
	}
	public Terror(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Terror() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
