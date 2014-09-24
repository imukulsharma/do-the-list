package com.list.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.list.app.dao.UserDAO;
import com.list.app.model.User;
import com.list.app.model.Role;

/**
 * Author : Mukul.Sharma
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public List<User> getAll() {
		System.out.println("Fetching Users List");
		System.out.println("Size : " + userDao.getAllUsers().size());
		return userDao.getAllUsers();
	}

	@Override
	public User getById(int id) {
		return userDao.getById(id);

	}

	@Override
	public void addUser(User user) {
		user.setEnabled(true);
		Role userRole = new Role("ROLE_USER");
		user.getUserRole().add(userRole);
		userDao.addUser(user);
	}

	@Override
	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}

}
