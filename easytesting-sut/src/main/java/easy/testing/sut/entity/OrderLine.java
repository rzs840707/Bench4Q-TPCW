package easy.testing.sut.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * ORDER_LINE Table Layout
 * Field Name Field Definition Comments
 * OL_ID Numeric, 3 digits Unique Order Line Item ID
 * OL_O_ID Numeric, 18 digits Order ID of Order Line
 * OL_I_ID Numeric, 9 digits Unique Item ID (I_ID)
 * OL_QTY Numeric, 9 digits Quantity of Item
 * OL_STATUS Variable text, size 16 Order Status for this Item
 * OL_COST Numeric, (15,2) digits Cost for this line
 * Primary Key: (OL_ID, OL_O_ID)
 * (OL_I_ID) Foreign Key, references (I_ID); (OL_O_ID) Foreign Key, references (O_ID)
 */
@Entity
@Table(name = "ORDER_LINE")
public class OrderLine {
	private int id;
	private Order order;
	private Item item;
	private int quantity;
	private String status;
	private double cost;

	@Id
	@Column(name = "OL_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "OL_O_ID")
	public Order getOrder() {
		return order;
	}

	protected void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne
	@JoinColumn(name = "OL_I_ID")
	public Item getItem() {
		return item;
	}

	protected void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "OL_QTY")
	public int getQuantity() {
		return quantity;
	}

	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "OL_STATUS")
	public String getStatus() {
		return status;
	}

	protected void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OL_COST")
	public double getCost() {
		return cost;
	}

	protected void setCost(double cost) {
		this.cost = cost;
	}

	protected OrderLine() {

	}
}
