package com.sapient.springapp.service;

import java.io.IOException;

import javax.ws.rs.Path;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;
import org.springframework.stereotype.Service;

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
