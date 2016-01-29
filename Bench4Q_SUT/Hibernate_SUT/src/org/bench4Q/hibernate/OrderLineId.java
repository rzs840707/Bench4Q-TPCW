package org.bench4Q.hibernate;

/**
 * OrderLineId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class OrderLineId extends AbstractOrderLineId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrderLineId() {
	}

	/** full constructor */
	public OrderLineId(Integer olId, Integer olOId) {
		super(olId, olOId);
	}

}
