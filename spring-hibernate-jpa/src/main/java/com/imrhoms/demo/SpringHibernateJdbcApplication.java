package com.imrhoms.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.imrhoms.demo.jdbc.dao.PersonJdbcDAO;
import com.imrhoms.demo.jdbc.model.Person;

/**
 * Spring application for JDBC
 */
//@SpringBootApplication
public class SpringHibernateJdbcApplication implements CommandLineRunner {

	@Autowired
	private PersonJdbcDAO dao;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateJdbcApplication.class, args);
	}
	
	/**
	 * Executes code after spring boot application has started
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("All person -> {}", dao.getAll());
		logger.info("FindById -> {}", dao.getById(10002));
		logger.info("DeleteById -> Affected rows: {}", dao.deleteById(10002));
		logger.info("Create -> Affected rows {}", dao.save(new Person(10004, "Tara", "USA", new Date())));
		logger.info("Update -> Affected rows {}",
				dao.updateById(10004, new Person(10004, "Tara", "Germany", new Date())));
		logger.info("FindById CustomRowMapper -> {}",dao.findById(10004));
	}

}
