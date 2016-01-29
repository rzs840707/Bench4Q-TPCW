package org.bench4Q.hibernate;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Customer extends AbstractCustomer implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(Integer CId) {
		super(CId);
	}

	/** full constructor */
	public Customer(Integer CId, String CUname, String CPasswd, String CFname,
			String CLname, Integer CAddrId, String CPhone, String CEmail,
			Date CSince, Date CLastLogin, Timestamp CLogin,
			Timestamp CExpiration, Float CDiscount, Double CBalance,
			Double CYtdPmt, Date CBirthdate, String CData) {
		super(CId, CUname, CPasswd, CFname, CLname, CAddrId, CPhone, CEmail,
				CSince, CLastLogin, CLogin, CExpiration, CDiscount, CBalance,
				CYtdPmt, CBirthdate, CData);
	}

}
