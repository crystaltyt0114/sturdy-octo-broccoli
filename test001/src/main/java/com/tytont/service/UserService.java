package com.tytont.service;

import com.tytont.model.Role;
import com.tytont.model.User;

public interface UserService {

	public User getUser(int id);

	public Object page(Integer pageNum, Integer pageSize);

	public User add();

	public Role getRoleByUser(Integer userId);

	public int update();

	public User selectUserByUsername(String username, String password);
}
