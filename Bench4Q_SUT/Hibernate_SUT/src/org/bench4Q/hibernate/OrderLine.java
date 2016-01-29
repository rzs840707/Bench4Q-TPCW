package org.bench4Q.hibernate;

/**
 * OrderLine entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class OrderLine extends AbstractOrderLine implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrderLine() {
	}

	/** minimal constructor */
	public OrderLine(OrderLineId id) {
		super(id);
	}

	/** full constructor */
	public OrderLine(OrderLineId id, Integer olIId, Integer olQty,
			Double olDiscount, String olComments) {
		super(id, olIId, olQty, olDiscount, olComments);
	}

}
