package com.imrhoms.demo.repository;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jpa.model.Course;
import com.imrhoms.demo.jpa.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Uses persistence context to keep track of every change to an entity
 */
@Repository
@Transactional // Every change to data should be done by transaction
public class CourseJpaRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Entity manager track the entity until the end of transaction
	 */
	@Autowired
	EntityManager em;

	/**
	 * Find a course with the given Id in DB
	 * 
	 * @param id
	 * @return
	 */
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	/**
	 * Deletes course from DB
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	/**
	 * Saves new course in DB
	 * 
	 * @param course
	 */
	public void save(Course course) {
		em.persist(course);
	}

	/**
	 * Updates an existing course in DB
	 * 
	 * @param course
	 * @return
	 */
	public Course update(Course course) {
		return em.merge(course);
	}

	/**
	 * By default entity manager is configured in FlushModeType auto It flushes the
	 * persistent context in 2 situations - before the transaction gets committed &
	 * before executing a query that uses any database table
	 */
	public void entityManagerFlush() {
		Course course = new Course("Junit in 100 steps");
		em.persist(course);// Persist only makes entity get managed by the persistence context
		em.flush();// runs previous queries into DB
		course.setName("Junit");
	}

	/**
	 * By default entity manager is configured in FlushModeType auto It flushes the
	 * persistent context in 2 situations - before the transaction gets committed -
	 * before executing a query that uses any database table Flush - runs the query
	 * in DB
	 */
	public void entityManagerDetach() {
		Course course = new Course("Junit in 100 steps");
		em.persist(course);// Persist only makes entity get managed by the persistence context
		em.flush();// runs previous queries into DB
		em.detach(course);
		course.setName("Junit");
	}

	/**
	 * Refresh: retrieves status of entity from DB
	 */
	public void entityManagerRefresh() {
		Course course = new Course("Entity Manager in 100 steps");
		em.persist(course);
		em.flush();
		course.setName("Entity Manager deprecated");
		em.refresh(course);
	}

	/**
	 * Gets all elements from course table using native SQL queries
	 * 
	 * @return List of all courses in DB
	 */
	public List<Course> getAllByNativeQuery() {
		TypedQuery<Course> allCourses = em.createNamedQuery("getAllCourses", Course.class);
		return allCourses.getResultList();
	}

	/**
	 * Retrieves course with same id from DB with native queries and index parameter
	 * 
	 * @param id
	 * @return Course entity
	 */
	public Course getByIdByNativeQuery(Long id) {
		TypedQuery<Course> getByIdQuery = em.createNamedQuery("getById", Course.class);
		getByIdQuery.setParameter(1, id);
		return getByIdQuery.getSingleResult();
	}

	/**
	 * Retrieves course with same id from DB with native queries and named parameter
	 * 
	 * @param id
	 * @return Course entity
	 */
	public Course getByIdNativeQueryNamedParameter(Long id) {
		TypedQuery<Course> getByIdQuery = em.createNamedQuery("getByIdNamedParameter", Course.class);
		getByIdQuery.setParameter("id", id);
		return getByIdQuery.getSingleResult();
	}

	/**
	 * Retrieves a List of all student enrolled in the course
	 * 
	 * @param courseId
	 * @return List<Student>
	 */
	public Set<Student> getAllStudentsFromCourse(Long courseId) {
		Set<Student> enrolledStudents = em
				.createQuery("Select c From Course c JOIN FETCH c.enrolledStudents WHERE c.id = :id ", Course.class)
				.setParameter("id", courseId).getSingleResult().getEnrolledStudents();
		return enrolledStudents;
	}

}
