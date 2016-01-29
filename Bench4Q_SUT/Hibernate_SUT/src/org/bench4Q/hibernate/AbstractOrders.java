package org.bench4Q.hibernate;

import java.util.Date;

/**
 * AbstractOrders entity provides the base persistence definition of the Orders
 * entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractOrders implements java.io.Serializable {

	// Fields

	private Integer OId;
	private Integer OCId;
	private Date ODate;
	private Double OSubTotal;
	private Double OTax;
	private Double OTotal;
	private String OShipType;
	private Date OShipDate;
	private Integer OBillAddrId;
	private Integer OShipAddrId;
	private String OStatus;

	// Constructors

	/** default constructor */
	public AbstractOrders() {
	}

	/** minimal constructor */
	public AbstractOrders(Integer OId) {
		this.OId = OId;
	}

	/** full constructor */
	public AbstractOrders(Integer OId, Integer OCId, Date ODate,
			Double OSubTotal, Double OTax, Double OTotal, String OShipType,
			Date OShipDate, Integer OBillAddrId, Integer OShipAddrId,
			String OStatus) {
		this.OId = OId;
		this.OCId = OCId;
		this.ODate = ODate;
		this.OSubTotal = OSubTotal;
		this.OTax = OTax;
		this.OTotal = OTotal;
		this.OShipType = OShipType;
		this.OShipDate = OShipDate;
		this.OBillAddrId = OBillAddrId;
		this.OShipAddrId = OShipAddrId;
		this.OStatus = OStatus;
	}

	// Property accessors

	public Integer getOId() {
		return this.OId;
	}

	public void setOId(Integer OId) {
		this.OId = OId;
	}

	public Integer getOCId() {
		return this.OCId;
	}

	public void setOCId(Integer OCId) {
		this.OCId = OCId;
	}

	public Date getODate() {
		return this.ODate;
	}

	public void setODate(Date ODate) {
		this.ODate = ODate;
	}

	public Double getOSubTotal() {
		return this.OSubTotal;
	}

	public void setOSubTotal(Double OSubTotal) {
		this.OSubTotal = OSubTotal;
	}

	public Double getOTax() {
		return this.OTax;
	}

	public void setOTax(Double OTax) {
		this.OTax = OTax;
	}

	public Double getOTotal() {
		return this.OTotal;
	}

	public void setOTotal(Double OTotal) {
		this.OTotal = OTotal;
	}

	public String getOShipType() {
		return this.OShipType;
	}

	public void setOShipType(String OShipType) {
		this.OShipType = OShipType;
	}

	public Date getOShipDate() {
		return this.OShipDate;
	}

	public void setOShipDate(Date OShipDate) {
		this.OShipDate = OShipDate;
	}

	public Integer getOBillAddrId() {
		return this.OBillAddrId;
	}

	public void setOBillAddrId(Integer OBillAddrId) {
		this.OBillAddrId = OBillAddrId;
	}

	public Integer getOShipAddrId() {
		return this.OShipAddrId;
	}

	public void setOShipAddrId(Integer OShipAddrId) {
		this.OShipAddrId = OShipAddrId;
	}

	public String getOStatus() {
		return this.OStatus;
	}

	public void setOStatus(String OStatus) {
		this.OStatus = OStatus;
	}

}