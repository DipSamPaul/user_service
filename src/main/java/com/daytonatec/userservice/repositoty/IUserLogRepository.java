package com.daytonatec.userservice.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daytonatec.userservice.entity.UserLog;

public interface IUserLogRepository extends JpaRepository<UserLog, Long>{

}
