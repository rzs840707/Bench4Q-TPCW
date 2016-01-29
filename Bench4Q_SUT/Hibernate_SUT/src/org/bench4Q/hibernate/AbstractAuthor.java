package org.bench4Q.hibernate;

import java.util.Date;

/**
 * AbstractAuthor entity provides the base persistence definition of the Author
 * entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractAuthor implements java.io.Serializable {

	// Fields

	private Integer AId;
	private String AFname;
	private String ALname;
	private String AMname;
	private Date ADob;
	private String ABio;

	// Constructors

	/** default constructor */
	public AbstractAuthor() {
	}

	/** minimal constructor */
	public AbstractAuthor(Integer AId) {
		this.AId = AId;
	}

	/** full constructor */
	public AbstractAuthor(Integer AId, String AFname, String ALname,
			String AMname, Date ADob, String ABio) {
		this.AId = AId;
		this.AFname = AFname;
		this.ALname = ALname;
		this.AMname = AMname;
		this.ADob = ADob;
		this.ABio = ABio;
	}

	// Property accessors

	public Integer getAId() {
		return this.AId;
	}

	public void setAId(Integer AId) {
		this.AId = AId;
	}

	public String getAFname() {
		return this.AFname;
	}

	public void setAFname(String AFname) {
		this.AFname = AFname;
	}

	public String getALname() {
		return this.ALname;
	}

	public void setALname(String ALname) {
		this.ALname = ALname;
	}

	public String getAMname() {
		return this.AMname;
	}

	public void setAMname(String AMname) {
		this.AMname = AMname;
	}

	public Date getADob() {
		return this.ADob;
	}

	public void setADob(Date ADob) {
		this.ADob = ADob;
	}

	public String getABio() {
		return this.ABio;
	}

	public void setABio(String ABio) {
		this.ABio = ABio;
	}

}