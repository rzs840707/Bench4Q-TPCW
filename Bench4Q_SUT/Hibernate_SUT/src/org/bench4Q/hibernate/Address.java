package org.bench4Q.hibernate;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Address extends AbstractAddress implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** minimal constructor */
	public Address(Integer addrId) {
		super(addrId);
	}

	/** full constructor */
	public Address(Integer addrId, String addrStreet1, String addrStreet2,
			String addrCity, String addrState, String addrZip, Integer addrCoId) {
		super(addrId, addrStreet1, addrStreet2, addrCity, addrState, addrZip,
				addrCoId);
	}

}
