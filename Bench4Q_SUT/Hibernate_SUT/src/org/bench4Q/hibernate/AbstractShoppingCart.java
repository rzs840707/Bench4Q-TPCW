package org.bench4Q.hibernate;

import java.sql.Timestamp;

/**
 * AbstractShoppingCart entity provides the base persistence definition of the
 * ShoppingCart entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractShoppingCart implements java.io.Serializable {

	// Fields

	private Integer scId;
	private Timestamp scTime;

	// Constructors

	/** default constructor */
	public AbstractShoppingCart() {
	}

	/** minimal constructor */
	public AbstractShoppingCart(Integer scId) {
		this.scId = scId;
	}

	/** full constructor */
	public AbstractShoppingCart(Integer scId, Timestamp scTime) {
		this.scId = scId;
		this.scTime = scTime;
	}

	// Property accessors

	public Integer getScId() {
		return this.scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public Timestamp getScTime() {
		return this.scTime;
	}

	public void setScTime(Timestamp scTime) {
		this.scTime = scTime;
	}

}