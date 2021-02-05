package com.daytonatec.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daytonatec.userservice.entity.User;
import com.daytonatec.userservice.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController{

	@Autowired
	IUserService userService;

	@PutMapping(value = "/updatePassword/{email}/{oldPassword}/{newPassword}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String updatePassword(@PathVariable(required = true) String email,
			@PathVariable(required = true) String oldPassword, @PathVariable(required = true) String newPassword) {
		return userService.updatePassword(email, oldPassword, newPassword);
	}

	@PutMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user) {
		return userService.update(user);
	}
	
}
