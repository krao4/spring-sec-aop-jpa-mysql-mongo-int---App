package com.sapient.springapp.service;

import java.io.IOException;

import javax.ws.rs.Path;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Service calls a external RSS feed through the SI gateway and loads the title + link of the blog in a temporary file in the filesystem
 * The gateway connecting to the external RSS is configured on a polling mechanism and fetches new data every 500 seconds.
 * Hit enter key to terminate fetching blog data
 * @author Karthik Rao
 *
 */
@Path("/blogs")
@Service
public class BlogService {

	public static void main(String[] args) throws IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("/blog/integration.xml", BlogService.class);
        System.out.println("Hit Enter to terminate");
        System.in.read();

	}

	public void loadBlogs() throws IOException{
/*		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/integration/blog.xml", BlogService.class);
        System.out.println("Hit Enter to terminate");
        System.in.read();
*/	}

	
}
