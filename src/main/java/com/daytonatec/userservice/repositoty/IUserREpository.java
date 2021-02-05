package com.daytonatec.userservice.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.daytonatec.userservice.entity.User;

public interface IUserREpository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long>{
	@Query("select u from User u where u.email = :email")
	User findByEmail(String email);
}
