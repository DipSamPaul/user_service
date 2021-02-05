package com.daytonatec.userservice.config;

/*
 *this class stores the user Identifier in threadloal
 */
public class UserContext {

	private static ThreadLocal<Object> currentUser = new ThreadLocal();

	public static Object getCurrentUser() {
		return currentUser.get();

	}

	public static void setCurrentUser(Object tenant) {
		currentUser.set(tenant);
	}

	public static void clear() {
		currentUser.set(null);
	}

}