package org.bench4Q.hibernate;

/**
 * AbstractShoppingCartLine entity provides the base persistence definition of
 * the ShoppingCartLine entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractShoppingCartLine implements java.io.Serializable {

	// Fields

	private ShoppingCartLineId id;
	private Integer sclQty;

	// Constructors

	/** default constructor */
	public AbstractShoppingCartLine() {
	}

	/** minimal constructor */
	public AbstractShoppingCartLine(ShoppingCartLineId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractShoppingCartLine(ShoppingCartLineId id, Integer sclQty) {
		this.id = id;
		this.sclQty = sclQty;
	}

	// Property accessors

	public ShoppingCartLineId getId() {
		return this.id;
	}

	public void setId(ShoppingCartLineId id) {
		this.id = id;
	}

	public Integer getSclQty() {
		return this.sclQty;
	}

	public void setSclQty(Integer sclQty) {
		this.sclQty = sclQty;
	}

}