package com.sapient.springapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *  User service implements the logout service  
 *  @author Karthik Rao
 */

@Service("userService")
@Component
@Path("/userservice")
public class UserService implements ApplicationContextAware {
	
	final Logger logger = LoggerFactory.getLogger(UserService.class);	

	private static ApplicationContext ctx;
	
	@GET
    @Path("/logout")
    @Produces("application/json") 	
	public String logoutUser() {
		SecurityContextHolder.getContext().setAuthentication(null);
		//request.getSession().invalidate();
		return "true";
	}	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		ctx = applicationContext;
	}
	
}
