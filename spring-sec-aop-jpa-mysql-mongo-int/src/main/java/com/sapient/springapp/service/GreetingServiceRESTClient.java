package com.sapient.springapp.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sapient.springapp.domain.Greeting;

/**
 * CXF Service for consuming external REST service - implementation is in cxfcontext configuration
 * @author Karthik Rao
 *
 */

@Path("/greeting")
@Produces("application/json")
@Service("greetingServiceRESTClient")
@Component
public interface GreetingServiceRESTClient {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/")
	public Greeting getGreeting();
}
