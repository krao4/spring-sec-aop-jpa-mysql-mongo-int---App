package com.sapient.springapp.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;


/**
 * Domain object holding main navigation info.
 * @author Karthik Rao
 *
 */
public class Mainnav implements Serializable {

	private static final long serialVersionUID = 8086517660026628690L;

	@Id
    private String id;
	
	private String mainnav;
	
    public Mainnav() {}	

    public Mainnav(String mainnav) {
        this.mainnav = mainnav;
    }
    
	public String getMainnav() {
		return mainnav;
	}

	public void setMainnav(String mainnav) {
		this.mainnav = mainnav;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "MainNav [mainnav=" + mainnav + "]";
	}

}

