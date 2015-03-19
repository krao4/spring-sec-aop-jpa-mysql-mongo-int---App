/**
 * Created on Feb 13, 2015
 */
package com.sapient.springapp.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.sapient.springapp.domain.Custom;
import com.sapient.springapp.domain.Policy;

/**
 * Interface for policy service
 * @author Karthik Rao
 *
 */
@Path("/policy")
public interface PolicyService {

	@GET
    @Path("/")
    @Produces("application/json") 
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public List<Policy> findAll();
	
	@GET
    @Path("/custom")
    @Produces("application/json") 
	public List<Policy> findCustom();
	public Policy findById(Long id);
	
	public Policy save(Policy policy);
	
	public Page<Policy> findAllByPage(Pageable pageable);	
	
	@DELETE
    @Path("/{id}")
    @Produces("application/json") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(@PathParam("id") String id);
	
	@GET
    @Path("/cleanCache")
    @Produces("application/json") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void cleanCache();
	
	@GET
    @Path("/customdata/{input}")
    @Produces("application/json") 
   @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
   public List<Custom> findCustomData(@PathParam("input") String input);
	
}
