package com.imrhoms.demo.jpa.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "getById", query = "SELECT * FROM course WHERE id = ?", resultClass = Course.class),
		@NamedNativeQuery(name = "getByIdNamedParameter", query = "SELECT * FROM course WHERE id = :id", resultClass = Course.class),
		@NamedNativeQuery(name = "getAllCourses", query = "SELECT * FROM course",resultClass = Course.class) })
public class Course implements Serializable {

	private static final long serialVersionUID = -3469583445473380018L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * name: column name in DB (optional if names are equal) nullable: this field
	 * cannot be null (not null) insertable: whether the column is included in
	 * insert statements updatable: whether the column is included in update
	 * statements
	 */
	@Column(name = "name", nullable = false)
	private String name;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "course") // By default fetch type is LAZY
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "enrolledCourses")
	private Set<Student> enrolledStudents;//TODO change for set


	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String name) {
		super();
		this.name = name;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public void deleteReview(Review review) {
		reviews.remove(review);
	}
}
