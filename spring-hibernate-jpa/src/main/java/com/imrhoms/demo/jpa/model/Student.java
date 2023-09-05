package com.imrhoms.demo.jpa.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Student implements Serializable {

	private static final long serialVersionUID = 2285295742487191920L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "passport_id")
	private Passport passport;

	@ManyToMany
	@JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id")) 
	private List<Course> enrolledCourses;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, Passport passport) {
		super();
		this.name = name;
		this.passport = passport;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public List<Course> getEnrolledCourses() {
		return enrolledCourses;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

	public void addCourse(Course course) {
		this.enrolledCourses.add(course);
	}

}
