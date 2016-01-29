package org.bench4Q.hibernate;

/**
 * Country entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Country extends AbstractCountry implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** minimal constructor */
	public Country(Integer coId) {
		super(coId);
	}

	/** full constructor */
	public Country(Integer coId, String coName, Double coExchange,
			String coCurrency) {
		super(coId, coName, coExchange, coCurrency);
	}

}
