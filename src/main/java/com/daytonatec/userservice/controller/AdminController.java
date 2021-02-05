package com.daytonatec.userservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daytonatec.userservice.entity.User;
import com.daytonatec.userservice.service.IUserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends AbstractController{
	@Autowired
	IUserService userService;

	@GetMapping(value = "/getAllUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody User user) throws Exception{
		return userService.save(user);
	}

	@GetMapping(value = "/getUserByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUserByEmail(@PathVariable(required = true) String email) {
		return userService.getUserByEmail(email);
	}
}
