package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * AUTHOR Table Layout
 * Field Name	Field Definition	Comments
 * A_ID	Numeric, 10 digits	Unique Author ID
 * A_FNAME	Variable text, size 20	First Name of Author
 * A_LNAME	Variable text, size 20	Last Name of Author
 * A_MNAME	Variable text, size 20	Middle Name of Author
 * A_DOB	Date	Date of Birth of Author
 * A_BIO	Variable text, size 500	About the Author
 * Primary Key: (A_ID)
 */
@Entity
@Table(name = "AUTHOR")
public class Author {
	private int id;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private String aboutTheAuthor;

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

	@Column(name = "A_MNAME")
	public String getMiddleName() {
		return middleName;
	}

	protected void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "A_DOB")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	protected void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "A_BIO")
	public String getAboutTheAuthor() {
		return aboutTheAuthor;
	}

	protected void setAboutTheAuthor(String aboutTheAuthor) {
		this.aboutTheAuthor = aboutTheAuthor;
	}

	protected Author() {

	}
}
