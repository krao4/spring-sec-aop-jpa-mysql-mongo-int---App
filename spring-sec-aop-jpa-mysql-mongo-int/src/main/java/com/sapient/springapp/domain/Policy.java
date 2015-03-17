/**
 * Created on Feb 13, 2015
 */
package com.sapient.springapp.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * @author Karthik Rao
 *
 */
@Entity
@Table(name = "policy")
public class Policy implements Serializable {

	private static final long serialVersionUID = 4788145565046910301L;
	private Long id;
	private String policyName;
	private String startDate;
	private String description;
	private int version;	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	@NotEmpty(message="{validation.policyname.NotEmpty.message}")
	@Size(min=3, max=60, message="{validation.policyname.Size.message}")
	@Column(name = "POLICY_NAME")
	public String getPolicyName() {
		return policyName;
	}
	
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
	@Column(name = "START_DATE")
	@DateTimeFormat(iso=ISO.DATE)
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}	

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {		
		return "Policy - Id: " + id 
				+ ", Policy name: " + policyName 
				+ ", Start Date: " + startDate
				+ ", Description: " + description;
	}	
	
}
