package org.bench4Q.hibernate;

import java.util.Date;

/**
 * AbstractCcXacts entity provides the base persistence definition of the
 * CcXacts entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractCcXacts implements java.io.Serializable {

	// Fields

	private Integer cxOId;
	private String cxType;
	private String cxNum;
	private String cxName;
	private Date cxExpire;
	private String cxAuthId;
	private Double cxXactAmt;
	private Date cxXactDate;
	private Integer cxCoId;

	// Constructors

	/** default constructor */
	public AbstractCcXacts() {
	}

	/** minimal constructor */
	public AbstractCcXacts(Integer cxOId) {
		this.cxOId = cxOId;
	}

	/** full constructor */
	public AbstractCcXacts(Integer cxOId, String cxType, String cxNum,
			String cxName, Date cxExpire, String cxAuthId, Double cxXactAmt,
			Date cxXactDate, Integer cxCoId) {
		this.cxOId = cxOId;
		this.cxType = cxType;
		this.cxNum = cxNum;
		this.cxName = cxName;
		this.cxExpire = cxExpire;
		this.cxAuthId = cxAuthId;
		this.cxXactAmt = cxXactAmt;
		this.cxXactDate = cxXactDate;
		this.cxCoId = cxCoId;
	}

	// Property accessors

	public Integer getCxOId() {
		return this.cxOId;
	}

	public void setCxOId(Integer cxOId) {
		this.cxOId = cxOId;
	}

	public String getCxType() {
		return this.cxType;
	}

	public void setCxType(String cxType) {
		this.cxType = cxType;
	}

	public String getCxNum() {
		return this.cxNum;
	}

	public void setCxNum(String cxNum) {
		this.cxNum = cxNum;
	}

	public String getCxName() {
		return this.cxName;
	}

	public void setCxName(String cxName) {
		this.cxName = cxName;
	}

	public Date getCxExpire() {
		return this.cxExpire;
	}

	public void setCxExpire(Date cxExpire) {
		this.cxExpire = cxExpire;
	}

	public String getCxAuthId() {
		return this.cxAuthId;
	}

	public void setCxAuthId(String cxAuthId) {
		this.cxAuthId = cxAuthId;
	}

	public Double getCxXactAmt() {
		return this.cxXactAmt;
	}

	public void setCxXactAmt(Double cxXactAmt) {
		this.cxXactAmt = cxXactAmt;
	}

	public Date getCxXactDate() {
		return this.cxXactDate;
	}

	public void setCxXactDate(Date cxXactDate) {
		this.cxXactDate = cxXactDate;
	}

	public Integer getCxCoId() {
		return this.cxCoId;
	}

	public void setCxCoId(Integer cxCoId) {
		this.cxCoId = cxCoId;
	}

}