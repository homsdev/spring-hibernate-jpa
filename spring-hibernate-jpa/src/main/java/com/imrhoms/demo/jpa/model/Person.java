package com.imrhoms.demo.jpa.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "getAll", query = "select p from Person p") })
public class Person implements Serializable{

	private static final long serialVersionUID = -3026956333584565271L;

	@Id
	@GeneratedValue
	private int id;

	private String name;
	private String location;

	@Column(name = "birth_date")
	private Date birthDate;

	// Always no argument constructor
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name, String location, Date birthDate) {
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirtDate() {
		return birthDate;
	}

	public void setBirtDate(Date birtDate) {
		this.birthDate = birtDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}

}
