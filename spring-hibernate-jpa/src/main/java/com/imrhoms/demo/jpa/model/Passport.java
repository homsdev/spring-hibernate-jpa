package com.imrhoms.demo.jpa.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Passport implements Serializable {

	private static final long serialVersionUID = 1557668557407335965L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "number")
	private String number;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "passport")
	private Student student;

	public Passport() {
	}

	public Passport(String number) {
		super();
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}

}
