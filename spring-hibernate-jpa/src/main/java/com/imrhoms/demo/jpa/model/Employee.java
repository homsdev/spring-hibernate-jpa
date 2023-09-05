package com.imrhoms.demo.jpa.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * SINGLE_TABLE: All subclasses are mapped to one table (causes DB integrity problem)
 * TABLE_PER_CLASS: All subclasses are mapped to one table per class
 * JOINED: Best data integrity(creates a table per class, 3 in this case)
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "EmployeeType")
public abstract class Employee {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public Employee() {
	}

	public Employee(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}
