package easy.testing.sut.entity;

public class BuyResult {
	private ShoppingCartList shoppingCartList;
	private Order order;

	public ShoppingCartList getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(ShoppingCartList shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BuyResult(ShoppingCartList shoppingCartList, Order order) {
		this.setShoppingCartList(shoppingCartList);
		this.setOrder(order);
	}

}
