package com.imrhoms.demo;

import java.math.BigDecimal;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.imrhoms.demo.jpa.model.Employee;
import com.imrhoms.demo.jpa.model.FullTimeEmployee;
import com.imrhoms.demo.jpa.model.PartTimeEmployee;
import com.imrhoms.demo.jpa.model.Student;
import com.imrhoms.demo.repository.CourseJpaRepository;
import com.imrhoms.demo.repository.EmployeeRepository;
import com.imrhoms.demo.repository.PassportRepository;
import com.imrhoms.demo.repository.ReviewJPARepository;
import com.imrhoms.demo.repository.StudentJpaRepository;

import jakarta.transaction.Transactional;

/**
 * Spring application for JPA implementation
 * 
 * @author homs
 */
@SpringBootApplication
public class SpringHibernateJpaApplication implements CommandLineRunner {

	@Autowired
	private CourseJpaRepository courseJpaRepository;

	@Autowired
	private PassportRepository passportRepository;

	@Autowired
	private StudentJpaRepository studentRepository;

	@Autowired
	private ReviewJPARepository reviewRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateJpaApplication.class, args);
	}

	@Transactional
	private void lazyFetchingStudent() {
		Student student = studentRepository.findById(20001L);
		logger.info("Student -> {}", student);
		logger.info("Passport -> {}", student.getPassport());
	}

	private void demoManyToMany() {
		studentRepository.enrollInCourse(10001L, 20001L);
		studentRepository.enrollInCourse(10001L, 20002L);
		studentRepository.enrollInCourse(10003L, 20003L);
		logger.info("==================End of Inserted data==============");
		Set<Student> allStudentsFromCourse = courseJpaRepository.getAllStudentsFromCourse(10001L);
		logger.info("{}", allStudentsFromCourse);
	}

	/**
	 * Executes code after spring boot application has started
	 */
	@Override
	public void run(String... args) throws Exception {
		Employee jack = new FullTimeEmployee("Jack", new BigDecimal(10000));
		Employee jill = new PartTimeEmployee("Jill", new BigDecimal(50));
		employeeRepository.insertNewEmployee(jill);
		employeeRepository.insertNewEmployee(jack);
		logger.info("Employees -> {}",employeeRepository.getAllEmployees());
	}

}
