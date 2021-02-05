package com.daytonatec.userservice.service;

import java.util.List;

import com.daytonatec.userservice.entity.User;

public interface IUserService {
	List<User> getAllUser();
	User save(User model);
	User update(User u);
	User getUserByEmail(String email);
	String updatePassword(String email, String oldPassword, String newPassword);
}
