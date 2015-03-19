package com.sapient.springapp.domain;

/**
 * POJO to hold custom data fetched from a custom query
 * @author Jatin Girhotra
 *
 */
public class Custom {
	
	private String policyName;
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String description;

}
