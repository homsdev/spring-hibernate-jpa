package com.imrhoms.demo.jpa.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review implements Serializable {

	private static final long serialVersionUID = -4665767933360553676L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "description")
	private String description;

	@ManyToOne//Fetch type by default is EAGER
	@JoinColumn(name = "course_id")
	private Course course;

	public Review() {
	}

	public Review(Integer rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
