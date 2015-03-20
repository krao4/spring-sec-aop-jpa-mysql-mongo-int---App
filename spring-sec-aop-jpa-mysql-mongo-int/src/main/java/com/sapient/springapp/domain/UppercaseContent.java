package com.sapient.springapp.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@MessageEndpoint
@Component
public class UppercaseContent {

	Logger logger = LoggerFactory.getLogger(UppercaseContent.class);
	
	private static String content;
	
	public static String getContent() {
		return content;
	}

	public static void setContent(String contentVal) {
		content = contentVal;
	}
	
	@ServiceActivator
	public static void receive(String input) {
		setContent(input.toUpperCase());
	}
	
}
