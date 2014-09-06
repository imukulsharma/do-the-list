package com.list.app.dao;

import java.util.List;

import com.list.app.model.User;

public interface UserDAO {

	public void addUser(User user);

	public List<User> getAllUsers();

	public User getById(int id);

}
