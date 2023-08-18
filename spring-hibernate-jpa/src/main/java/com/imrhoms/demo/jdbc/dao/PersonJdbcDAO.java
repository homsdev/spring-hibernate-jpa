package com.imrhoms.demo.jdbc.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jdbc.mapper.PersonRowMapper;
import com.imrhoms.demo.jdbc.model.Person;

@Repository
public class PersonJdbcDAO {

	JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonJdbcDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * BeanPropertyRowMapper is the default mapped in JDBC
	 * 
	 * @return List of persons
	 */
	@SuppressWarnings("unchecked")
	public List<Person> getAll() {
		return jdbcTemplate.query("select * from person p", new BeanPropertyRowMapper(Person.class));
	}

	/**
	 * NOTE: queryForObject(sql,Object[],mapper) is deprecated use
	 * queryForObject(sql,mapper,Object args...)
	 * 
	 * @param id
	 * @return
	 */
	public Person getById(Integer id) {
		String query = "SELECT * FROM person p WHERE p.id = ?";
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Person.class), id);
	}

	public int deleteById(Integer id) {
		String query = "DELETE FROM person p WHERE p.id = ?";
		return jdbcTemplate.update(query, id);
	}

	public int save(Person person) {
		String query = "INSERT INTO person(id,name,location,birth_date) VALUES (?,?,?,?)";

		return jdbcTemplate.update(query, person.getId(), person.getName(), person.getLocation(),
				new Timestamp(person.getBirthDate().getTime()));
	}
	
	public int updateById(Integer id,Person person) {
		String query = "UPDATE person p SET p.name = ? , p.location = ? WHERE p.id = ?";
		return jdbcTemplate.update(query, person.getName(), person.getLocation(),id);
	}

	
	/**
	 * Using custom row mapper
	 * @param id
	 * @return
	 */
	public Person findById(Integer id) {
		String query = "SELECT * FROM person p WHERE p.id = ?";
		return jdbcTemplate.queryForObject(query, new PersonRowMapper(), id);
	}
	
}
