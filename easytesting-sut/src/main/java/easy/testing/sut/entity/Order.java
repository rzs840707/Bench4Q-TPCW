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
 * Field Name	Field Definition	Comments
 * O_ID	Numeric, 10 digits	Unique ID per order
 * O_C_ID	Numeric, 10 digits	Customer ID of Order
 * O_DATE	Date and time	Order Date and time
 * O_SUB_TOTAL	Numeric, (15,2) digits	Subtotal of all order-line items
 * O_TAX	Numeric, (15,2) digits	Tax over the subtotal
 * O_TOTAL	Numeric, (15,2) digits	Total for this order
 * O_SHIP_TYPE	Variable text, size 10	Method of delivery
 * O_SHIP_DATE	Date and time	Order Ship Date
 * O_BILL_ADDR_ID	Numeric, 10 digits	Address ID to bill
 * O_SHIP_ADDR_ID	Numeric, 10 digits	Address ID to ship order
 * O_STATUS	Variable text, size 15	Order status
 * Primary Key: (O_ID)
 * (O_C_ID) Foreign Key, references (C_ID); (O_BILL_ADDR, O_SHIP_ADDR) Foreign Key, references (ADDR_ID)
 */
@Entity
@Table(name = "ORDERS")
public class Order {
	private int id;
	private Customer customer;
	private Date orderDate;
	private double subTotal;
	private double tax;
	private double total;
	private String shipType;
	private Date shipDate;
	private Address billAddress;
	private Address shipAddress;
	private String status;

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
	public double getSubTotal() {
		return subTotal;
	}

	protected void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
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

	@Column(name = "O_SHIP_TYPE")
	public String getShipType() {
		return shipType;
	}

	protected void setShipType(String shipType) {
		this.shipType = shipType;
	}

	@Column(name = "O_SHIP_DATE")
	public Date getShipDate() {
		return shipDate;
	}

	protected void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	@ManyToOne
	@JoinColumn(name = "O_BILL_ADDR_ID")
	public Address getBillAddress() {
		return billAddress;
	}

	protected void setBillAddress(Address billAddress) {
		this.billAddress = billAddress;
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

	protected Order() {

	}
}
