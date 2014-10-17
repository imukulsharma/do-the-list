package com.list.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.list.app.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getAllUsers() {
		return entityManager.createQuery("Select u from com.list.app.model.User u", User.class).getResultList();
	}

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public User getById(int id) {
		return entityManager.createQuery("from com.list.app.Model.User where userid = id", User.class)
				.getSingleResult();
	}

	@Override
	public User getByUsername(String username) {
		return entityManager.createQuery("from com.list.app.model.User where username = :username", User.class)
				.setParameter("username", username).getSingleResult();
	}
}
