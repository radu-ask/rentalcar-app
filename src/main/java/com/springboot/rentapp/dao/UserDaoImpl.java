package com.springboot.rentapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.rentapp.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public List<User> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public void deleteById(Long theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		User myUser = currentSession.get(User.class, theId);
		
		if(myUser != null) {
			currentSession.delete(myUser);
		}
		System.out.println("Id parameter is: " + theId);
		System.out.println("User object is null");
		
	}

}
