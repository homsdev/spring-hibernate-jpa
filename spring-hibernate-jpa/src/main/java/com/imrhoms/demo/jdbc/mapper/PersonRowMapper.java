package com.imrhoms.demo.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.imrhoms.demo.jdbc.model.Person;

/**
 * Class custom mapper for Person Data, maps rows from a result set to a JAVA POJO
 * @author homs
 */
public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();

		person.setId(rs.getInt("id"));
		person.setName(rs.getString("name"));
		person.setLocation(rs.getString("location"));
		person.setBirthDate(rs.getTimestamp("birth_date"));

		return person;
	}

}
