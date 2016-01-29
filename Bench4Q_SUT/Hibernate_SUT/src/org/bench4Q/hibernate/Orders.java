package org.bench4Q.hibernate;

import java.util.Date;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Orders extends AbstractOrders implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(Integer OId) {
		super(OId);
	}

	/** full constructor */
	public Orders(Integer OId, Integer OCId, Date ODate, Double OSubTotal,
			Double OTax, Double OTotal, String OShipType, Date OShipDate,
			Integer OBillAddrId, Integer OShipAddrId, String OStatus) {
		super(OId, OCId, ODate, OSubTotal, OTax, OTotal, OShipType, OShipDate,
				OBillAddrId, OShipAddrId, OStatus);
	}

}
