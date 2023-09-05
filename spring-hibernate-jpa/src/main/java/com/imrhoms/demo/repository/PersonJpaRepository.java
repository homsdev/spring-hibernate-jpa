package com.imrhoms.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jpa.model.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PersonJpaRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Person> findAll() {
		TypedQuery<Person> query = entityManager.createNamedQuery("getAll",Person.class);
		return query.getResultList();
	}
	
	public Person findById(Integer id) {
		return entityManager.find(Person.class, id);
	}
	
	public Person update(Person person) {
		return entityManager.merge(person);
	}
	
	public Person save(Person person) {
		return entityManager.merge(person);
	}
	
	public void delete(Integer id) {
		Person person = findById(id);
		entityManager.remove(person);
	}
	

}
