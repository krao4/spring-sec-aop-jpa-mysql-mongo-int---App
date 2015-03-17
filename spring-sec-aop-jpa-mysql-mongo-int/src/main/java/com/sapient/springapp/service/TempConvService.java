package com.sapient.springapp.service;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sapient.springapp.domain.Temp;

@Service("tempConvService")
@Component
@Path("/tempconv")
public class TempConvService implements ApplicationContextAware {
	
	final Logger logger = LoggerFactory.getLogger(TempConvService.class);	

	@Autowired
	private Temp payload;
	
	private static ApplicationContext ctx;
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
			new ClassPathXmlApplicationContext("/META-INF/spring/integration/temperatureConversion.xml");
		DestinationResolver<MessageChannel> channelResolver = new BeanFactoryChannelResolver(context);

		// Compose the XML message according to the server's schema
		String requestXml =
				"<FahrenheitToCelsius xmlns=\"http://www.w3schools.com/webservices/\">" +
						"<Fahrenheit>90.0</Fahrenheit>" +
				"</FahrenheitToCelsius>";

		// Create the Message object
		Message<String> message = MessageBuilder.withPayload(requestXml).build();

		// Send the Message to the handler's input channel
		MessageChannel channel = channelResolver.resolveDestination("fahrenheitChannel");
		channel.send(message);
		
		System.out.println(message.getPayload().toString());
	}

	@GET
    @Path("/{temp}")
    @Produces("application/json") 
	public String convTemp(@PathParam("temp") String temp) throws IOException{
		
		if(("").equalsIgnoreCase(temp)) {
			temp = "90";
		}
		logger.info("Input temperature in farenheit: "+temp);
		
		//		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/integration/temperatureConversion.xml", TempConvService.class);
		DestinationResolver<MessageChannel> channelResolver = new BeanFactoryChannelResolver(ctx);

		// Compose the XML message according to the server's schema
		String requestXml =
				"<FahrenheitToCelsius xmlns=\"http://www.w3schools.com/webservices/\">" +
						"<Fahrenheit>"+temp+"</Fahrenheit>" +
				"</FahrenheitToCelsius>";

		// Create the Message object
		Message<String> message = MessageBuilder.withPayload(requestXml).build();

		// Send the Message to the handler's input channel
		MessageChannel channel = channelResolver.resolveDestination("fahrenheitChannel");
		channel.send(message);
		
		return payload.getTempVal();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		ctx = applicationContext;
	}
	
}
