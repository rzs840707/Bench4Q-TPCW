package org.bench4Q.hibernate;

/**
 * AbstractOrderLine entity provides the base persistence definition of the
 * OrderLine entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractOrderLine implements java.io.Serializable {

	// Fields

	private OrderLineId id;
	private Integer olIId;
	private Integer olQty;
	private Double olDiscount;
	private String olComments;

	// Constructors

	/** default constructor */
	public AbstractOrderLine() {
	}

	/** minimal constructor */
	public AbstractOrderLine(OrderLineId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractOrderLine(OrderLineId id, Integer olIId, Integer olQty,
			Double olDiscount, String olComments) {
		this.id = id;
		this.olIId = olIId;
		this.olQty = olQty;
		this.olDiscount = olDiscount;
		this.olComments = olComments;
	}

	// Property accessors

	public OrderLineId getId() {
		return this.id;
	}

	public void setId(OrderLineId id) {
		this.id = id;
	}

	public Integer getOlIId() {
		return this.olIId;
	}

	public void setOlIId(Integer olIId) {
		this.olIId = olIId;
	}

	public Integer getOlQty() {
		return this.olQty;
	}

	public void setOlQty(Integer olQty) {
		this.olQty = olQty;
	}

	public Double getOlDiscount() {
		return this.olDiscount;
	}

	public void setOlDiscount(Double olDiscount) {
		this.olDiscount = olDiscount;
	}

	public String getOlComments() {
		return this.olComments;
	}

	public void setOlComments(String olComments) {
		this.olComments = olComments;
	}

}