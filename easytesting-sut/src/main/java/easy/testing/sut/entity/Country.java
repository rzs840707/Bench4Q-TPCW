package easy.testing.sut.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * COUNTRY Table Layout
 * Field Name Field Definition Comments
 * CO_ID Numeric, 4 digits Unique Country ID
 * CO_NAME Variable text, size 50 Name of Country
 * Primary Key: (CO_ID)
 */
@Entity
@Table(name = "COUNTRY")
public class Country {
	private int id;
	private String name;

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

	protected Country() {

	}
}
