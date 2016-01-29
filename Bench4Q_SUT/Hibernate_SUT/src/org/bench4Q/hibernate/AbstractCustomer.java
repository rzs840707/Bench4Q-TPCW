package org.bench4Q.hibernate;

import java.sql.Timestamp;
import java.util.Date;

/**
 * AbstractCustomer entity provides the base persistence definition of the
 * Customer entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractCustomer implements java.io.Serializable {

	// Fields

	private Integer CId;
	private String CUname;
	private String CPasswd;
	private String CFname;
	private String CLname;
	private Integer CAddrId;
	private String CPhone;
	private String CEmail;
	private Date CSince;
	private Date CLastLogin;
	private Timestamp CLogin;
	private Timestamp CExpiration;
	private Float CDiscount;
	private Double CBalance;
	private Double CYtdPmt;
	private Date CBirthdate;
	private String CData;

	// Constructors

	/** default constructor */
	public AbstractCustomer() {
	}

	/** minimal constructor */
	public AbstractCustomer(Integer CId) {
		this.CId = CId;
	}

	/** full constructor */
	public AbstractCustomer(Integer CId, String CUname, String CPasswd,
			String CFname, String CLname, Integer CAddrId, String CPhone,
			String CEmail, Date CSince, Date CLastLogin, Timestamp CLogin,
			Timestamp CExpiration, Float CDiscount, Double CBalance,
			Double CYtdPmt, Date CBirthdate, String CData) {
		this.CId = CId;
		this.CUname = CUname;
		this.CPasswd = CPasswd;
		this.CFname = CFname;
		this.CLname = CLname;
		this.CAddrId = CAddrId;
		this.CPhone = CPhone;
		this.CEmail = CEmail;
		this.CSince = CSince;
		this.CLastLogin = CLastLogin;
		this.CLogin = CLogin;
		this.CExpiration = CExpiration;
		this.CDiscount = CDiscount;
		this.CBalance = CBalance;
		this.CYtdPmt = CYtdPmt;
		this.CBirthdate = CBirthdate;
		this.CData = CData;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public String getCUname() {
		return this.CUname;
	}

	public void setCUname(String CUname) {
		this.CUname = CUname;
	}

	public String getCPasswd() {
		return this.CPasswd;
	}

	public void setCPasswd(String CPasswd) {
		this.CPasswd = CPasswd;
	}

	public String getCFname() {
		return this.CFname;
	}

	public void setCFname(String CFname) {
		this.CFname = CFname;
	}

	public String getCLname() {
		return this.CLname;
	}

	public void setCLname(String CLname) {
		this.CLname = CLname;
	}

	public Integer getCAddrId() {
		return this.CAddrId;
	}

	public void setCAddrId(Integer CAddrId) {
		this.CAddrId = CAddrId;
	}

	public String getCPhone() {
		return this.CPhone;
	}

	public void setCPhone(String CPhone) {
		this.CPhone = CPhone;
	}

	public String getCEmail() {
		return this.CEmail;
	}

	public void setCEmail(String CEmail) {
		this.CEmail = CEmail;
	}

	public Date getCSince() {
		return this.CSince;
	}

	public void setCSince(Date CSince) {
		this.CSince = CSince;
	}

	public Date getCLastLogin() {
		return this.CLastLogin;
	}

	public void setCLastLogin(Date CLastLogin) {
		this.CLastLogin = CLastLogin;
	}

	public Timestamp getCLogin() {
		return this.CLogin;
	}

	public void setCLogin(Timestamp CLogin) {
		this.CLogin = CLogin;
	}

	public Timestamp getCExpiration() {
		return this.CExpiration;
	}

	public void setCExpiration(Timestamp CExpiration) {
		this.CExpiration = CExpiration;
	}

	public Float getCDiscount() {
		return this.CDiscount;
	}

	public void setCDiscount(Float CDiscount) {
		this.CDiscount = CDiscount;
	}

	public Double getCBalance() {
		return this.CBalance;
	}

	public void setCBalance(Double CBalance) {
		this.CBalance = CBalance;
	}

	public Double getCYtdPmt() {
		return this.CYtdPmt;
	}

	public void setCYtdPmt(Double CYtdPmt) {
		this.CYtdPmt = CYtdPmt;
	}

	public Date getCBirthdate() {
		return this.CBirthdate;
	}

	public void setCBirthdate(Date CBirthdate) {
		this.CBirthdate = CBirthdate;
	}

	public String getCData() {
		return this.CData;
	}

	public void setCData(String CData) {
		this.CData = CData;
	}

}