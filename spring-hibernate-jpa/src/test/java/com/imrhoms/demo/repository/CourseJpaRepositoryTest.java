package com.imrhoms.demo.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.imrhoms.demo.SpringHibernateJpaApplication;
import com.imrhoms.demo.jpa.model.Course;

/**
 * Integration test for CourseJPaRepository
 */
@RunWith(SpringRunner.class) // Launch the entire springboot context
@SpringBootTest(classes = SpringHibernateJpaApplication.class)
public class CourseJpaRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CourseJpaRepository repository;
	
	@Test
	@DirtiesContext//if current test modifies application context it recreates for later tests
	public void testDeleteCourseById() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	public void testFindCourseById() {
		Course course = repository.findById(10002L);
		assertEquals("Java in 100 steps", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void testSaveCourse() throws Exception {
		Course course = new Course("Git in 100 steps");
		repository.save(course);
		Course createdCourse = repository.findById(1L);
		assertEquals(course.getName(), createdCourse.getName());
	}

}
