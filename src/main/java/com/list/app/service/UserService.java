package com.list.app.service;

import java.util.List;

import com.list.app.model.User;

/**
 * Author : Mukul.Sharma
 */
public interface UserService {

	public List<User> getAll();

	public User getById(int id);

	public void addUser(User user);

}
