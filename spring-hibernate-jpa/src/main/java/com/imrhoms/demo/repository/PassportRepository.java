package com.imrhoms.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imrhoms.demo.jpa.model.Passport;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PassportRepository {

	EntityManager em;

	@Autowired
	public PassportRepository(EntityManager em) {
		super();
		this.em = em;
	}

	public void savePassport(Passport passport) {
		em.persist(passport);
	}

	public Passport findById(Long id) {
		return em.find(Passport.class, id);
	}

}
