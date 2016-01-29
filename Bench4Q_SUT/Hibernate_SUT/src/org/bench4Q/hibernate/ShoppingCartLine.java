package org.bench4Q.hibernate;

/**
 * ShoppingCartLine entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShoppingCartLine extends AbstractShoppingCartLine implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ShoppingCartLine() {
	}

	/** minimal constructor */
	public ShoppingCartLine(ShoppingCartLineId id) {
		super(id);
	}

	/** full constructor */
	public ShoppingCartLine(ShoppingCartLineId id, Integer sclQty) {
		super(id, sclQty);
	}

}
