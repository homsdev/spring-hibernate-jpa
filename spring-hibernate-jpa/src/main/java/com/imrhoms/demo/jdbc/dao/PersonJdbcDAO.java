package com.imrhoms.demo.jdbc.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jdbc.mapper.PersonRowMapper;
import com.imrhoms.demo.jdbc.model.Person;

/**
 * Separates business logic to access data (DAO)
 * 
 * @author homs
 */
@Repository
public class PersonJdbcDAO {

	JdbcTemplate jdbcTemplate;

	/**
	 * Constructor for DAO class
	 * 
	 * @param jdbcTemplate
	 */
	@Autowired
	public PersonJdbcDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * BeanPropertyRowMapper is the default mapped in JDBC
	 * 
	 * @return All persons in DB using default bean mapper
	 */
	@SuppressWarnings("unchecked")
	public List<Person> getAll() {
		return jdbcTemplate.query("select * from person p", new BeanPropertyRowMapper(Person.class));
	}

	/**
	 * NOTE: queryForObject(sql,Object[],mapper) is deprecated use
	 * queryForObject(sql,mapper,Object args...)
	 * 
	 * @param id to search in DB
	 * @return person associate with given id
	 */
	public Person getById(Integer id) {
		String query = "SELECT * FROM person p WHERE p.id = ?";
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Person.class), id);
	}

	/**
	 * Uses jdbcTemplate method update to delete a person from DB
	 * 
	 * @param id from person to delete in DB
	 * @return Number of rows affected by delete operation
	 */
	public int deleteById(Integer id) {
		String query = "DELETE FROM person p WHERE p.id = ?";
		return jdbcTemplate.update(query, id);
	}

	/**
	 * Persist a new person inside DB using update method
	 * 
	 * @param person
	 * @return Number of affected rows
	 */
	public int save(Person person) {
		String query = "INSERT INTO person(id,name,location,birth_date) VALUES (?,?,?,?)";

		return jdbcTemplate.update(query, person.getId(), person.getName(), person.getLocation(),
				new Timestamp(person.getBirthDate().getTime()));
	}

	/**
	 * Updates the associated register with the id in DB
	 * 
	 * @param id     from person to update
	 * @param person data to update
	 * @return Number of affected rows
	 */
	public int updateById(Integer id, Person person) {
		String query = "UPDATE person p SET p.name = ? , p.location = ? WHERE p.id = ?";
		return jdbcTemplate.update(query, person.getName(), person.getLocation(), id);
	}

	/**
	 * Find a person with the given id, uses custom RowMapper
	 * 
	 * @param id from person to search
	 * @return Person
	 */
	public Person findById(Integer id) {
		String query = "SELECT * FROM person p WHERE p.id = ?";
		return jdbcTemplate.queryForObject(query, new PersonRowMapper(), id);
	}

}
