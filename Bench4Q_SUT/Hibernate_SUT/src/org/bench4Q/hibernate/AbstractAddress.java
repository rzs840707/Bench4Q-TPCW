package org.bench4Q.hibernate;

/**
 * AbstractAddress entity provides the base persistence definition of the
 * Address entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractAddress implements java.io.Serializable {

	// Fields

	private Integer addrId;
	private String addrStreet1;
	private String addrStreet2;
	private String addrCity;
	private String addrState;
	private String addrZip;
	private Integer addrCoId;

	// Constructors

	/** default constructor */
	public AbstractAddress() {
	}

	/** minimal constructor */
	public AbstractAddress(Integer addrId) {
		this.addrId = addrId;
	}

	/** full constructor */
	public AbstractAddress(Integer addrId, String addrStreet1,
			String addrStreet2, String addrCity, String addrState,
			String addrZip, Integer addrCoId) {
		this.addrId = addrId;
		this.addrStreet1 = addrStreet1;
		this.addrStreet2 = addrStreet2;
		this.addrCity = addrCity;
		this.addrState = addrState;
		this.addrZip = addrZip;
		this.addrCoId = addrCoId;
	}

	// Property accessors

	public Integer getAddrId() {
		return this.addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public String getAddrStreet1() {
		return this.addrStreet1;
	}

	public void setAddrStreet1(String addrStreet1) {
		this.addrStreet1 = addrStreet1;
	}

	public String getAddrStreet2() {
		return this.addrStreet2;
	}

	public void setAddrStreet2(String addrStreet2) {
		this.addrStreet2 = addrStreet2;
	}

	public String getAddrCity() {
		return this.addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrState() {
		return this.addrState;
	}

	public void setAddrState(String addrState) {
		this.addrState = addrState;
	}

	public String getAddrZip() {
		return this.addrZip;
	}

	public void setAddrZip(String addrZip) {
		this.addrZip = addrZip;
	}

	public Integer getAddrCoId() {
		return this.addrCoId;
	}

	public void setAddrCoId(Integer addrCoId) {
		this.addrCoId = addrCoId;
	}

}