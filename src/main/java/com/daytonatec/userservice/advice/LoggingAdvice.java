package com.daytonatec.userservice.advice;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.daytonatec.userservice.config.UserContext;
import com.daytonatec.userservice.entity.UserLog;
import com.daytonatec.userservice.repositoty.IUserLogRepository;
import com.daytonatec.userservice.repositoty.IUserREpository;
import com.daytonatec.userservice.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {
	@Autowired
	IUserLogRepository userLogRepository;
	@Autowired
	IUserREpository userREpository;
	@Autowired
	ObjectMapper objectMapper;

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

	@Pointcut("execution(* com.daytonatec.userservice.controller.UserController.*(..)) || execution(* com.daytonatec.userservice.controller.LogInController.*(..))")
	public void myPointcut() {

	}

	@Around("myPointcut()")
	public Object activityLogger(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		log.info("method -> " + methodName);
		Object[] args = pjp.getArgs();
		log.info("args -> " + args);
		Object obj = pjp.proceed();
		log.info("result -> " + obj);
		this.saveLog(methodName, args, obj);
		UserContext.clear();
		return obj;
	}

	private void saveLog(String methodName, Object[] args, Object returnData) throws JsonProcessingException {
		String user = (String) UserContext.getCurrentUser();
		userLogRepository.save(UserLog.builder().createdDate(new Date()).activity(methodName)
				.userid((user != null ? userREpository.findByEmail(user).getId() : 0))
				.inputs(objectMapper.writeValueAsString(args)).returnData(objectMapper.writeValueAsString(returnData))
				.build());
	}
}
