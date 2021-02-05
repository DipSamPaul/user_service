package com.daytonatec.userservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {
	private long id;
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Date dob;
	private String status;

	private UserModel(Builder builder) {
		this.userName = builder.userName;
		this.password = builder.password;
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.dob = builder.dob;
		this.status = builder.status;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String userName;
		private String password;
		private String email;
		private String firstName;
		private String lastName;
		private Date dob;
		private String status;

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder dob(Date dob) {
			this.dob = dob;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public UserModel build() {
			return new UserModel(this);
		}
	}
}
