package easy.testing.sut.tpcw2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * AUTHOR Table Layout
 * Field Name Field Definition Comments
 * A_ID Numeric, 9 digits Unique Author ID
 * A_FNAME Variable text, size 20 First Name of Author
 * A_LNAME Variable text, size 20 Last Name of Author
 * Primary Key: (A_ID)
 */
@Entity
@Table(name = "AUTHOR")
public class Author {

	private int id;
	private String firstName;
	private String lastName;

	@Id
	@Column(name = "A_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "A_FNAME")
	public String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "A_LNAME")
	public String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	protected Author() {

	}
}
