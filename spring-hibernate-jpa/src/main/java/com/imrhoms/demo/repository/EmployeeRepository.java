package com.imrhoms.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jpa.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

	private final EntityManager em;

	@Autowired
	public EmployeeRepository(EntityManager em) {
		super();
		this.em = em;
	}

	public void insertNewEmployee(Employee employee) {
		em.persist(employee);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = em.createQuery("select e from Employee e", Employee.class).getResultList();
		return employeeList;
	}

}
