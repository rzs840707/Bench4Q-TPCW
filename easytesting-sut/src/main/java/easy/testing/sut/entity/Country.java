package easy.testing.sut.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * COUNTRY Table Layout
 * Field Name	Field Definition	Comments
 * CO_ID	Numeric, 4 digits	Unique Country ID
 * CO_NAME	Variable text, size 50	Name of Country
 * CO_EXCHANGE	Numeric, (6, 6) digits	Exchange rate to US Dollars
 * CO_CURRENCY	Variable text, size 18	Name of Currency
 * Primary Key: (CO_ID)
 */
@Entity
@Table(name = "COUNTRY")
public class Country {
	private int id;
	private String name;
	private double exchange;
	private String currency;

	@Id
	@Column(name = "CO_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "CO_NAME")
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Column(name = "CO_EXCHANGE")
	public double getExchange() {
		return exchange;
	}

	protected void setExchange(double exchange) {
		this.exchange = exchange;
	}

	@Column(name = "CO_CURRENCY")
	public String getCurrency() {
		return currency;
	}

	protected void setCurrency(String currency) {
		this.currency = currency;
	}

	protected Country() {

	}
}
