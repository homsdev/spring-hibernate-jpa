package com.imrhoms.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jpa.model.Course;
import com.imrhoms.demo.jpa.model.Review;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ReviewJPARepository {

	private final EntityManager em;
	private final CourseJpaRepository courseRepository;
	
	
	@Autowired
	public ReviewJPARepository(EntityManager em, CourseJpaRepository courseRepository) {
		super();
		this.em = em;
		this.courseRepository = courseRepository;
	}



	public void createReview(Long courseId, Review review) {
		Course course = courseRepository.findById(courseId);
		if (course != null) {
			review.setCourse(course);
			em.persist(review);
		}
	}

}
