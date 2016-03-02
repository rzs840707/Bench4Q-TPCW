package easy.testing.sut.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_CART_LINE")
public class ShoppingCartLine implements Serializable {

	private static final long serialVersionUID = 4747231619065650491L;

	private ShoppingCart shoppingCart;
	private Item item;
	private int quantity;

	@Id
	@ManyToOne
	@JoinColumn(name = "SCL_SC_ID")
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	protected void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "SCL_I_ID")
	public Item getItem() {
		return item;
	}

	protected void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "SCL_QTY")
	public int getQuantity() {
		return quantity;
	}

	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	protected ShoppingCartLine() {

	}

	public static ShoppingCartLine create(ShoppingCart shoppingCart, Item item, int quantity) {
		ShoppingCartLine shoppingCartLine = new ShoppingCartLine();
		shoppingCartLine.setShoppingCart(shoppingCart);
		shoppingCartLine.setItem(item);
		shoppingCartLine.setQuantity(quantity);
		return shoppingCartLine;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + (this.getShoppingCart() == null ? 0 : this.getShoppingCart().hashCode());
		result = 37 * result + (this.getItem() == null ? 0 : this.getItem().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ((this == obj)) {
			return true;
		}
		if ((obj == null)) {
			return false;
		}
		if (!(obj instanceof ShoppingCartLine)) {
			return false;
		}

		ShoppingCartLine castOther = (ShoppingCartLine) obj;
		boolean shoppingCartEquals = ((this.getShoppingCart() == castOther.getShoppingCart())
				|| (this.getShoppingCart() != null && castOther.getShoppingCart() != null
						&& this.getShoppingCart().equals(castOther.getShoppingCart())));
		boolean itemEquals = ((this.getItem() == castOther.getItem()) || (this.getItem() != null
				&& castOther.getItem() != null && this.getItem().equals(castOther.getItem())));
		return shoppingCartEquals && itemEquals;
	}

}
