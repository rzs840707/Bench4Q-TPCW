package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * ORDERS Table Layout
 * Field Name Field Definition Comments
 * O_ID Numeric, 18 digits Unique ID per order
 * O_C_ID Numeric, 9 digits Customer ID of Order
 * O_DATE Date and time Order Date and time
 * O_SUB_TOTAL Numeric, (15,2) digits Subtotal of all order-line items
 * O_TAX Numeric, (15,2) digits Tax over the subtotal
 * O_TOTAL Numeric, (15,2) digits Total for this order
 * O_SHIP_DATE Date and time Date the orders was shipped to customer
 * O_SHIP_TYPE Variable text, size 10 Method of delivery
 * O_SHIP_ADDR_ID Numeric, 9 digits Address ID to ship order
 * O_STATUS Variable text, size 16 Order status
 * O_AUTH_ID Variable text, size 16 Authorization code from PGE (for credit orders)
 * O_SHIP_COST Numeric, (15,2) digits Shipping costs for this order
 * O_DISCOUNT Numeric, (3,2) digits Percentage discount off (note: Need to update Create Order to use this field instead of OL_DISCOUNT)
 * Primary Key: (O_ID)
 * (O_C_ID) Foreign Key, references (C_ID);
 * (O_SHIP_ADDR) Foreign Key, references (ADDR_ID)
 */
@Entity
@Table(name = "ORDERS")
public class Order {
	private int id;
	private Customer customer;
	private Date orderDate;
	private double subtotal;
	private double tax;
	private double total;
	private Date shipDate;
	private String shipType;
	private Address shipAddress;
	private String status;
	private String authorizationCode;
	private double shipCost;
	private double discount;

	@Id
	@Column(name = "O_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "O_C_ID")
	public Customer getCustomer() {
		return customer;
	}

	protected void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "O_DATE")
	public Date getOrderDate() {
		return orderDate;
	}

	protected void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "O_SUB_TOTAL")
	public double getSubtotal() {
		return subtotal;
	}

	protected void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Column(name = "O_TAX")
	public double getTax() {
		return tax;
	}

	protected void setTax(double tax) {
		this.tax = tax;
	}

	@Column(name = "O_TOTAL")
	public double getTotal() {
		return total;
	}

	protected void setTotal(double total) {
		this.total = total;
	}

	@Column(name = "O_SHIP_DATE")
	public Date getShipDate() {
		return shipDate;
	}

	protected void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	@Column(name = "O_SHIP_TYPE")
	public String getShipType() {
		return shipType;
	}

	protected void setShipType(String shipType) {
		this.shipType = shipType;
	}

	@ManyToOne
	@JoinColumn(name = "O_SHIP_ADDR_ID")
	public Address getShipAddress() {
		return shipAddress;
	}

	protected void setShipAddress(Address shipAddress) {
		this.shipAddress = shipAddress;
	}

	@Column(name = "O_STATUS")
	public String getStatus() {
		return status;
	}

	protected void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "O_AUTH_ID")
	public String getAuthorizationCode() {
		return authorizationCode;
	}

	protected void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	@Column(name = "O_SHIP_COST")
	public double getShipCost() {
		return shipCost;
	}

	protected void setShipCost(double shipCost) {
		this.shipCost = shipCost;
	}

	@Column(name = "O_DISCOUNT")
	public double getDiscount() {
		return discount;
	}

	protected void setDiscount(double discount) {
		this.discount = discount;
	}

	protected Order() {

	}
}
