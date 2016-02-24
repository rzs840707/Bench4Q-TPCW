package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * CUSTOMER Table Layout
 * Field Name	Field Definition	Comments
 * C_ID	Numeric, 10 digits	Unique ID per Customer
 * C_UNAME	Variable text, size 20	Unique User Name for Customer
 * C_PASSWD	Variable text, size 20	User Password for Customer
 * C_FNAME	Variable text, size 15	First name of Customer
 * C_LNAME	Variable text, size 15	Last name of Customer
 * C_ADDR_ID	Numeric, 10 digits	Address ID of Customer 
 * C_PHONE	Variable text, size 16	Phone number of Customer
 * C_EMAIL	Variable text, size 50	For sending purchase confirmations
 * C_SINCE	Date	Date of Customer registration
 * C_LAST_VISIT	Date	Date of last visit
 * C_LOGIN	Date and time	Start of Current Customer Session
 * C_EXPIRATION	Date and time	Current Customer Session Expiry
 * C_DISCOUNT	Numeric, (3,2) digits	Percentage discount for Customer
 * C_BALANCE	Sign numeric, (15,2) digits	Balance of Customer
 * C_YTD_PMT	Numeric, (15,2) digits	YTD Payment of Customer
 * C_BIRTHDATE	Date 	Birth date of Customer
 * C_DATA	Variable text, size 500	Miscellaneous information
 * Primary Key: (C_ ID)
 * (C_ADDR_ID) Foreign Key, references (ADDR_ID)
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	private int id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Address address;
	private String phone;
	private String email;
	private Date registrationDate;
	private Date lastVisitDate;
	private Date sessionStart;
	private Date sessionExpiration;
	private double discount;
	private double balance;
	private double yearToDatePayment;
	private Date birthDate;
	private String data;

	@Id
	@Column(name = "C_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "C_UNAME")
	public String getUserName() {
		return userName;
	}

	protected void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "C_PASSWD")
	public String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "C_FNAME")
	public String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "C_LNAME")
	public String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ManyToOne
	@JoinColumn(name = "C_ADDR_ID")
	public Address getAddress() {
		return address;
	}

	protected void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "C_PHONE")
	public String getPhone() {
		return phone;
	}

	protected void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "C_EMAIL")
	public String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "C_SINCE")
	public Date getRegistrationDate() {
		return registrationDate;
	}

	protected void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	// @Column(name = "C_LAST_VISIT")
	@Column(name = "C_LAST_LOGIN")
	public Date getLastVisitDate() {
		return lastVisitDate;
	}

	protected void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	@Column(name = "C_LOGIN")
	public Date getSessionStart() {
		return sessionStart;
	}

	protected void setSessionStart(Date sessionStart) {
		this.sessionStart = sessionStart;
	}

	@Column(name = "C_EXPIRATION")
	public Date getSessionExpiration() {
		return sessionExpiration;
	}

	protected void setSessionExpiration(Date sessionExpiration) {
		this.sessionExpiration = sessionExpiration;
	}

	@Column(name = "C_DISCOUNT")
	public double getDiscount() {
		return discount;
	}

	protected void setDiscount(double discount) {
		this.discount = discount;
	}

	@Column(name = "C_BALANCE")
	public double getBalance() {
		return balance;
	}

	protected void setBalance(double balance) {
		this.balance = balance;
	}

	@Column(name = "C_YTD_PMT")
	public double getYearToDatePayment() {
		return yearToDatePayment;
	}

	protected void setYearToDatePayment(double yearToDatePayment) {
		this.yearToDatePayment = yearToDatePayment;
	}

	@Column(name = "C_BIRTHDATE")
	public Date getBirthDate() {
		return birthDate;
	}

	protected void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "C_DATA")
	public String getData() {
		return data;
	}

	protected void setData(String data) {
		this.data = data;
	}

	protected Customer() {

	}

	public void refreshSession() {
		Date now = new Date(System.currentTimeMillis());
		this.setSessionStart(now);
		this.setSessionExpiration(new Date(now.getTime() + 2 * 60 * 60 * 1000));
	}

	public static Customer create(String userName, String password, String firstName, String lastName, Address address,
			String phone, String email, Date registrationDate, Date lastVisitDate, Date sessionStart,
			Date sessionExpiration, double discount, double balance, double yearToDatePayment, Date birthDate,
			String data) {
		Customer customer = new Customer();
		customer.setUserName(userName);
		customer.setPassword(password);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setAddress(address);
		customer.setPhone(phone);
		customer.setEmail(email);
		customer.setRegistrationDate(registrationDate);
		customer.setLastVisitDate(lastVisitDate);
		customer.setSessionStart(sessionStart);
		customer.setSessionExpiration(sessionExpiration);
		customer.setDiscount(discount);
		customer.setBalance(balance);
		customer.setYearToDatePayment(yearToDatePayment);
		customer.setBirthDate(birthDate);
		customer.setData(data);
		return customer;
	}

	public void updateInformation(String userName, String password, Address address) {
		this.setUserName(userName);
		this.setPassword(password);
		this.setAddress(address);
	}
}
