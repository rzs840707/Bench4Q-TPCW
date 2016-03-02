package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart {
	private int id;
	private Date time;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SC_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "SC_TIME")
	public Date getTime() {
		return time;
	}

	protected void setTime(Date time) {
		this.time = time;
	}

	protected ShoppingCart() {

	}

	public static ShoppingCart create() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setTime(new Date(System.currentTimeMillis()));
		return shoppingCart;
	}

	public void resetTime() {
		this.setTime(new Date(System.currentTimeMillis()));
	}
}
