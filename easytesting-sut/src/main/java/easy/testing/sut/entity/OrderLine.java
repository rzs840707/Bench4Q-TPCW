package easy.testing.sut.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * ORDER_LINE Table Layout
 * Field Name	Field Definition	Comments
 * OL_ID	Numeric, 3 digits	Unique Order Line Item ID
 * OL_O_ID	Numeric, 10 digits	Order ID of Order Line
 * OL_I_ID	Numeric, 10 digits	Unique Item ID (I_ID)
 * OL_QTY	Numeric, 3 digits	Quantity of Item
 * OL_DISCOUNT	Numeric, (3,2) digits	Percentage discount off of I_SRP
 * OL_COMMENTS	Variable text, size 100	Special Instructions
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
	private double discount;
	private String comments;

	@Id
	@Column(name = "OL_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "OL_O_ID")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne
	@JoinColumn(name = "OL_I_ID")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "OL_QTY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "OL_DISCOUNT")
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Column(name = "OL_COMMENTS")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	protected OrderLine() {

	}
}
