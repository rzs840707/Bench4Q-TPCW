package easy.testing.sut.tpcw2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * ADDRESS Table Layout
 * Field Name Field Definition Comments
 * ADDR_ID Numeric, 9 digits Unique address ID
 * ADDR_STREET1 Variable text, size 40 Street address, line 1
 * ADDR_STREET2 Variable text, size 40 Street address, line 2
 * ADDR_CITY Variable text, size 30 Name of city
 * ADDR_STATE Variable text, size 20 Name of state
 * ADDR_ZIP Variable text, size 10 Zip code or Postal code
 * ADDR_CO_ID Numeric, 4 digits Unique ID of Country
 * Primary Key: (ADDR_ID)
 * (ADDR_CO_ID) Foreign Key, references (CO_ID)
 */
@Entity
@Table(name = "ADDRESS")
public class Address {
	private int id;
	private String streetLineOne;
	private String streetLineTwo;
	private String city;
	private String state;
	private String zip;
	private Country country;

	@Id
	@Column(name = "ADDR_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "ADDR_STREET1")
	public String getStreetLineOne() {
		return streetLineOne;
	}

	protected void setStreetLineOne(String streetLineOne) {
		this.streetLineOne = streetLineOne;
	}

	@Column(name = "ADDR_STREET2")
	public String getStreetLineTwo() {
		return streetLineTwo;
	}

	protected void setStreetLineTwo(String streetLineTwo) {
		this.streetLineTwo = streetLineTwo;
	}

	@Column(name = "ADDR_CITY")
	public String getCity() {
		return city;
	}

	protected void setCity(String city) {
		this.city = city;
	}

	@Column(name = "ADDR_STATE")
	public String getState() {
		return state;
	}

	protected void setState(String state) {
		this.state = state;
	}

	@Column(name = "ADDR_ZIP")
	public String getZip() {
		return zip;
	}

	protected void setZip(String zip) {
		this.zip = zip;
	}

	@ManyToOne
	@JoinColumn(name = "ADDR_CO_ID")
	public Country getCountry() {
		return country;
	}

	protected void setCountry(Country country) {
		this.country = country;
	}

	protected Address() {

	}
}
