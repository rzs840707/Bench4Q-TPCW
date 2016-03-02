package easy.testing.sut.entity;

import java.util.List;

public class ShoppingCartList {
	private List<ShoppingCartLine> shoppingCartLines;
	private double discount;
	private double subTotal;
	private double tax;
	private double shipCost;
	private double totalCost;

	public List<ShoppingCartLine> getShoppingCartLines() {
		return shoppingCartLines;
	}

	private void setShoppingCartLines(List<ShoppingCartLine> shoppingCartLines) {
		this.shoppingCartLines = shoppingCartLines;
	}

	public double getDiscount() {
		return discount;
	}

	private void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getSubTotal() {
		return subTotal;
	}

	private void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTax() {
		return tax;
	}

	private void setTax(double tax) {
		this.tax = tax;
	}

	public double getShipCost() {
		return shipCost;
	}

	private void setShipCost(double shipCost) {
		this.shipCost = shipCost;
	}

	public double getTotalCost() {
		return totalCost;
	}

	private void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public ShoppingCartList(List<ShoppingCartLine> shoppingCartLines, double discount) {
		this.setShoppingCartLines(shoppingCartLines);
		this.setDiscount(discount);
		this.setSubTotal(0);

		int i;
		int total_items = 0;
		for (i = 0; i < shoppingCartLines.size(); i++) {
			ShoppingCartLine line = shoppingCartLines.get(i);
			double cost = line.getItem().getCost() * line.getQuantity();
			this.setSubTotal(this.getSubTotal() + cost);
			total_items += line.getQuantity();
		}

		// Need to multiply the sub_total by the discount.
		this.setSubTotal(this.getSubTotal() * ((100 - this.getDiscount()) * 0.01));
		this.setTax(this.getSubTotal() * .0825);
		this.setShipCost(3.00 + (1.00 * total_items));
		this.setTotalCost(this.getSubTotal() + this.getShipCost() + this.getTax());
	}
}
