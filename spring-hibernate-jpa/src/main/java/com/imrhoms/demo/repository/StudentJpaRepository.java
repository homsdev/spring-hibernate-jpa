package com.imrhoms.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jpa.model.Course;
import com.imrhoms.demo.jpa.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StudentJpaRepository {

	private final EntityManager em;
	private final CourseJpaRepository courseJpaRepository;

	@Autowired
	public StudentJpaRepository(EntityManager em, CourseJpaRepository courseJpaRepository) {
		this.em = em;
		this.courseJpaRepository = courseJpaRepository;
	}

	/**
	 * Saves student into DB
	 * 
	 * @param student info
	 */
	public void saveStudent(Student student) {
		em.persist(student);
	}

	/**
	 * Retrieves student with the given id from DB
	 * 
	 * @param id
	 * @return Student
	 */
	public Student findById(Long id) {
		return em.find(Student.class, id);// Session dies after this method ends, consider this in lazy fetch
	}

	/**
	 * Enrolls student into given course id
	 * 
	 * @param courseId
	 * @param studentId
	 */
	public void enrollInCourse(Long courseId, Long studentId) {
		Course course = courseJpaRepository.findById(courseId);
		Student student = findById(studentId);
		if (course != null && student != null) {
			student.addCourse(course);
		}
	}

	public List<Course> getEnrolledCourses(Long studentId) {
		Student student = em.find(Student.class, studentId);
		List<Course> enrolledCourses = student.getEnrolledCourses();
		return enrolledCourses;
	}

}
