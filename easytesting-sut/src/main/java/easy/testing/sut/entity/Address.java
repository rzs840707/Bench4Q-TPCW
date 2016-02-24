package easy.testing.sut.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * ADDRESS Table Layout
 * Field Name	Field Definition	Comments
 * ADDR_ID	Numeric, 10 digits	Unique address ID 
 * ADDR_STREET1	Variable text, size 40	Street address, line 1
 * ADDR_STREET2	Variable text, size 40	Street address, line 2
 * ADDR_CITY	Variable text, size 30	Name of city
 * ADDR_STATE	Variable text, size 20	Name of state
 * ADDR_ZIP	Variable text, size 10	Zip code or Postal code
 * ADDR_CO_ID	Numeric, 4 digits	Unique ID of Country 
 * Primary Key: (ADDR_ID)
 * (ADDR_CO_ID) Foreign Key, references (CO_ID)
 */
@Entity
@Table(name = "ADDRESS")
public class Address {
	private int id;
	private String streetLine1;
	private String streetLine2;
	private String city;
	private String state;
	private String zipCode;
	private Country country;

	@Id
	@Column(name = "ADDR_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ADDR_STREET1")
	public String getStreetLine1() {
		return streetLine1;
	}

	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}

	@Column(name = "ADDR_STREET2")
	public String getStreetLine2() {
		return streetLine2;
	}

	public void setStreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}

	@Column(name = "ADDR_CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "ADDR_STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ADDR_ZIP")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@ManyToOne
	@JoinColumn(name = "ADDR_CO_ID")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	protected Address() {

	}

	public static Address create(String streetLine1, String streetLine2, String city, String state, String zipCode,
			Country country) {
		Address address = new Address();
		address.setStreetLine1(streetLine1);
		address.setStreetLine2(streetLine2);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipCode);
		address.setCountry(country);
		return address;
	}
}
