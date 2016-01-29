package org.bench4Q.hibernate;

/**
 * AbstractCountry entity provides the base persistence definition of the
 * Country entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractCountry implements java.io.Serializable {

	// Fields

	private Integer coId;
	private String coName;
	private Double coExchange;
	private String coCurrency;

	// Constructors

	/** default constructor */
	public AbstractCountry() {
	}

	/** minimal constructor */
	public AbstractCountry(Integer coId) {
		this.coId = coId;
	}

	/** full constructor */
	public AbstractCountry(Integer coId, String coName, Double coExchange,
			String coCurrency) {
		this.coId = coId;
		this.coName = coName;
		this.coExchange = coExchange;
		this.coCurrency = coCurrency;
	}

	// Property accessors

	public Integer getCoId() {
		return this.coId;
	}

	public void setCoId(Integer coId) {
		this.coId = coId;
	}

	public String getCoName() {
		return this.coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public Double getCoExchange() {
		return this.coExchange;
	}

	public void setCoExchange(Double coExchange) {
		this.coExchange = coExchange;
	}

	public String getCoCurrency() {
		return this.coCurrency;
	}

	public void setCoCurrency(String coCurrency) {
		this.coCurrency = coCurrency;
	}

}