package org.bench4Q.hibernate;

import java.util.Date;

/**
 * CcXacts entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class CcXacts extends AbstractCcXacts implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CcXacts() {
	}

	/** minimal constructor */
	public CcXacts(Integer cxOId) {
		super(cxOId);
	}

	/** full constructor */
	public CcXacts(Integer cxOId, String cxType, String cxNum, String cxName,
			Date cxExpire, String cxAuthId, Double cxXactAmt, Date cxXactDate,
			Integer cxCoId) {
		super(cxOId, cxType, cxNum, cxName, cxExpire, cxAuthId, cxXactAmt,
				cxXactDate, cxCoId);
	}

}
