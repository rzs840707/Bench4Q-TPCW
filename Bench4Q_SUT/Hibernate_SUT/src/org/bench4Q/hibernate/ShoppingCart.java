package org.bench4Q.hibernate;

import java.sql.Timestamp;

/**
 * ShoppingCart entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShoppingCart extends AbstractShoppingCart implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ShoppingCart() {
	}

	/** minimal constructor */
	public ShoppingCart(Integer scId) {
		super(scId);
	}

	/** full constructor */
	public ShoppingCart(Integer scId, Timestamp scTime) {
		super(scId, scTime);
	}

}
