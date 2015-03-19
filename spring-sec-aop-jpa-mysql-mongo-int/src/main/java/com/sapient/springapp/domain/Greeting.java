package com.sapient.springapp.domain;

import org.springframework.stereotype.Component;

/**
 * Domain object to hold content from greeting service
 * @author Karthik Rao
 *
 */
@Component
public class Greeting {

    private long id;
    private String name;
    private String tempInCelsius;
    private String content;
    
    public Greeting() {
    }

	public Greeting(long id, String name, String tempInCelsius, String content) {
		super();
		this.id = id;
		this.name = name;
		this.tempInCelsius = tempInCelsius;
		this.content= content;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTempInCelsius() {
		return tempInCelsius;
	}

	public void setTempInCelsius(String tempInCelsius) {
		this.tempInCelsius = tempInCelsius;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void processPayload(Greeting value) {
		setContent(value.getContent());
	}

	@Override
	public String toString() {
		return "Greeting [name=" + name + ", tempInCelsius=" + tempInCelsius
				+ "]";
	}	    
	
	
}
