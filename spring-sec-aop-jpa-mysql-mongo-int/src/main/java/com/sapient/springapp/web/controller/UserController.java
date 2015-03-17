package com.sapient.springapp.web.controller;

import java.util.ArrayList;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapient.springapp.domain.User;
import com.sapient.springapp.domain.Users;


@Controller
@RequestMapping("/users")
public class UserController 
{
	
	//Delete this class??
/*	@RequestMapping(method = RequestMethod.GET, value="/{id}", headers="Accept=application/json")
	public @ResponseBody User getUserById(@PathVariable String id) 
	{
		User user = new User();
		user.setFirstName("john");
		user.setLastName("adward");
		return user;
	}
	
	@RequestMapping(method = RequestMethod.GET,  headers="Accept=application/json")
	public @ResponseBody Users getAllUsers() 
	{
		User user1 = new User();
		user1.setFirstName("john");
		user1.setLastName("adward");
		
		User user2 = new User();
		user2.setFirstName("tom");
		user2.setLastName("hanks");
		
		Users users = new Users();
		users.setUsers(new ArrayList<User>());
		users.getUsers().add(user1);
		users.getUsers().add(user2);
		
		return users;
	}
	*/
	@RequestMapping(value="/logout", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody String logoutUser() {
		SecurityContextHolder.getContext().setAuthentication(null);
		//request.getSession().invalidate();
		return "true";
	}
	
}
