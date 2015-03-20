package com.sapient.springapp.service;

import java.io.IOException;

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
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sapient.springapp.domain.Greeting;
import com.sapient.springapp.domain.Temp;
import com.sapient.springapp.domain.UppercaseContent;

/**
 * 	This service demonstrates some Spring Integration concepts - connections with external SOAP service, REST service and an ACTIVE MQ service.
 *  A Message (received from UI) is sent to a Channel, where it is retrieved by a Chain which consists of a Header Enricher and a WS Outbound Gateway(SOAP).
 *  The Header Enricher enriches the Message with the SOAP action header.
 *  The WS Outbound Gateway converts the Message to a SOAP request and sends it to a remote service (W3School), 
 *  which converts a temperature from Fahrenheit (e.g. 90F) to Celsius (32.2C). 
 *  The result is picked up by an adapter (service activator) and sent to spring managed bean.
 *  This data is then sent to a local "greeting" REST service, through an http-outbound-gateway and the REST service responds with the greeting and temperature information.
 *  This data is then sent to an active MQ channel and is converted to all Upper case string.
 *  The resulting data is then sent to the caller(UI) as response.
 *  
 *  @author Karthik Rao
 */

@Service("tempConvService")
@Component
@Path("/tempconv")
public class TempConvService implements ApplicationContextAware {
	
	final Logger logger = LoggerFactory.getLogger(TempConvService.class);	

	@Autowired
	private Temp payload;
	
	@Autowired
	private Greeting greetingPayload;
	
	@Autowired
	private UppercaseContent uppercaseContent;
	
	private static ApplicationContext ctx;
	
	@GET
    @Path("/{temp}")
    @Produces("application/json") 
	public String convTemp(@PathParam("temp") String temp) throws IOException{

		//		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/integration/temperatureConversion.xml", TempConvService.class);
		DestinationResolver<MessageChannel> channelResolver = new BeanFactoryChannelResolver(ctx);
		
		if(("").equalsIgnoreCase(temp)) {
			temp = "90";
		}
		logger.info("Input temperature in farenheit: "+temp);
		

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
		logger.info("Today's temperature in celsius is: "+payload.getTempVal());
		
		
		Greeting greeting = new Greeting();
		greeting.setName("Karthik");
		greeting.setTempInCelsius(payload.getTempVal());

		// Send the Message to the handler's input channel
		channel = channelResolver.resolveDestination("sendGreetingChannel");
		Message<Greeting> greetingMessage = MessageBuilder.withPayload(greeting).build();		
		channel.send(greetingMessage );

		String msg =  greetingPayload.getContent();
		logger.info("*****Greeting content: "+msg);
		
		
		// Create the Message object
		message = MessageBuilder.withPayload(msg).build();

		// Send the Message to the handler's input channel
		channel = channelResolver.resolveDestination("producerChannel");
		channel.send(message,1);
		logger.info("*****Message sent: "+msg);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String uContent = uppercaseContent.getContent();
		logger.info("*****Message received: "+uContent);
		
		return uContent;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		ctx = applicationContext;
	}
	
}
