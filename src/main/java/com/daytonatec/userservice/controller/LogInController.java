package com.daytonatec.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daytonatec.userservice.config.UserContext;
import com.daytonatec.userservice.dto.AuthRequest;
import com.daytonatec.userservice.util.JwtUtil;

@RestController
public class LogInController extends AbstractController {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String logIn(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
			
			UserContext.setCurrentUser(authRequest.getEmail());
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return new StringBuffer("daytonatec ").append(jwtUtil.generateToken(authRequest.getEmail())).toString();
	}
}
