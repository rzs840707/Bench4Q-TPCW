package org.bench4Q.hibernate;

/**
 * ShoppingCartLineId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShoppingCartLineId extends AbstractShoppingCartLineId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ShoppingCartLineId() {
	}

	/** full constructor */
	public ShoppingCartLineId(Integer sclScId, Integer sclIId) {
		super(sclScId, sclIId);
	}

}
