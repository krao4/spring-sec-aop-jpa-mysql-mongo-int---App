package com.sapient.springapp.domain;

import org.springframework.stereotype.Component;

/**
 * Domain object for temperature information
 * @author Karthik Rao
 *
 */
@Component
public class Temp {

	private static String tempVal;

	public String getTempVal() {
		return tempVal;
	}

	public static void setTempVal(String tempValue) {
		tempVal = tempValue;
	}
	
	public static void processPayload(String tempVal) {
		String temp = tempVal.substring(tempVal.indexOf("<FahrenheitToCelsiusResult>")+"<FahrenheitToCelsiusResult>".length(), tempVal.indexOf("</FahrenheitToCelsiusResult>"));
		setTempVal(temp);
	}	
	
}
