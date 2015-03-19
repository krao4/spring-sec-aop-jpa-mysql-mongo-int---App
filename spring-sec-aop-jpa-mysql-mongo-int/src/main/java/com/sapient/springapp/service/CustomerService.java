/**
 * Created on Feb 13, 2015
 */
package com.sapient.springapp.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.sapient.springapp.domain.Customer;

/**
 * 
 * Interface for customer service
 * @author Karthik Rao
 *
 */
@Path("/customer")
public interface CustomerService {

	@GET
    @Path("/")
    @Produces("application/json") 
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public List<Customer> findAll();
	
	@DELETE
    @Path("/{id}")
    @Produces("application/json") 
	@PreAuthorize("hasRole('ROLE_USER')")	
	public void delete(Customer customer);
}
