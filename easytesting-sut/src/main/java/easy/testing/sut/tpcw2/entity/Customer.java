package easy.testing.sut.tpcw2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * CUSTOMER Table Layout
 * Field Name Field Definition Comments
 * C_ID Numeric, 9 digits Unique ID per Customer
 * C_BUSINESS_NAME Variable text, size 20 Unique Business name
 * C_BUSINESS_INFO Variable text, size 100 Miscellaneous information
 * C_PASSWD Variable text, size 20 User Password for Business
 * C_CONTACT_FNAME Variable text, size 15 First name of Business Contact
 * C_CONTACT_LNAME Variable text, size 15 Last name of Business Contact
 * C_ADDR_ID Numeric, 9 digits Billing Address ID for this customer
 * C_CONTACT_PHONE Variable text, size 16 Phone number of Business Contact
 * C_CONTACT_EMAIL Variable text, size 50 Email of Business Contact
 * C_PAYMENT_METHOD Variable text, size 2 Payment method text code
 * C_CREDIT_INFO Variable text, size 300 Credit Information
 * C_PO Numeric, 9 digits Purchase order number
 * C_DISCOUNT (verify if set in New Customer) Numeric, (3,2) digits Percentage discount for Customer
 * Primary Key: (C_ID)
 * (C_ADDDR_ID) Foreign Key, references (ADDR_ID)
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	private int id;
	private String businessName;
	private String businessInfo;
	private String password;
	private String contactFirstName;
	private String contactLastName;
	private int addressId;
	private String contactPhone;
	private String contactEmail;
	private String paymentMethod;
	private String creditInfo;
	private int purchaseOrderNumber;
	private double discount;

	@Id
	@Column(name = "C_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "C_BUSINESS_NAME")
	public String getBusinessName() {
		return businessName;
	}

	protected void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Column(name = "C_BUSINESS_INFO")
	public String getBusinessInfo() {
		return businessInfo;
	}

	protected void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}

	@Column(name = "C_PASSWD")
	public String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "C_CONTACT_FNAME")
	public String getContactFirstName() {
		return contactFirstName;
	}

	protected void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	@Column(name = "C_CONTACT_LNAME")
	public String getContactLastName() {
		return contactLastName;
	}

	protected void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	@Column(name = "C_ADDR_ID")
	public int getAddressId() {
		return addressId;
	}

	protected void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	@Column(name = "C_CONTACT_PHONE")
	public String getContactPhone() {
		return contactPhone;
	}

	protected void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Column(name = "C_CONTACT_EMAIL")
	public String getContactEmail() {
		return contactEmail;
	}

	protected void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Column(name = "C_PAYMENT_METHOD")
	public String getPaymentMethod() {
		return paymentMethod;
	}

	protected void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "C_CREDIT_INFO")
	public String getCreditInfo() {
		return creditInfo;
	}

	protected void setCreditInfo(String creditInfo) {
		this.creditInfo = creditInfo;
	}

	@Column(name = "C_PO")
	public int getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	protected void setPurchaseOrderNumber(int purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	@Column(name = "C_DISCOUNT")
	public double getDiscount() {
		return discount;
	}

	protected void setDiscount(double discount) {
		this.discount = discount;
	}

	protected Customer() {

	}
}
