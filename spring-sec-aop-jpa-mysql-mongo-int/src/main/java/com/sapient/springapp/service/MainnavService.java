package com.sapient.springapp.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.springapp.domain.Mainnav;
import com.sapient.springapp.repository.MainnavRepository;

@Service("mainnavService")
@Path("/mainnav")
public class MainnavService {

	final Logger logger = LoggerFactory.getLogger(MainnavService.class);	
	
	@Autowired
	private MainnavRepository mainnavRepository;
	
	@Transactional(readOnly=true)
	@GET
    @Path("/")
    @Produces("application/json") 	
	public Mainnav show() {
		
		logger.info("main nav details");
		logger.info("-------------------------------");

		return mainnavRepository.findAll().get(0);
	}

	
	public Mainnav save(Mainnav navjson) {
		
		logger.info("save main nav");
		logger.info("-------------------------------");
		return mainnavRepository.save(navjson);
	}	
}
