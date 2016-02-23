package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/*
 * CC_XACTS Table Layout (Credit Card Transaction Table)
 * Field Name	Field Definition	Comments
 * CX_O_ID	Numeric, 10 digits	Unique Order ID (O_ID)
 * CX_TYPE	Variable text, size 10	Credit card type
 * CX_NUM	Numeric, 16 digits	Credit card number
 * CX_NAME	Variable text, size 31	Name on credit card
 * CX_EXPIRY	Date	Expiration date of credit card
 * CX_AUTH_ID	Fixed text, size 15	Authorization for transaction amount
 * CX_XACT_AMT	Numeric, (15,2) digits	Amount for this transaction
 * CX_XACT_DATE	Date and time	Date and time of authorization
 * CX_CO_ID	Numeric, 4 digits	Country where transaction originated
 * Primary Key: (CX_O_ID)
 * (CX_O_ID) Foreign Key, references (O_ID); (CX_CO_ID) Foreign Key, references (CO_ID)
 */
public class CreditCardTransaction {
	private int id;
	private Order order;
	private String type;
	private String number;
	private String name;
	private Date expirationDate;
	private String authorizationId;
	private double amount;
	private Date authorizationDate;
	private Country country;

	@Id
	@Column(name = "CX_O_ID")
	@GenericGenerator(name = "foreignKey", strategy = "foreign", parameters = @Parameter(name = "property", value = "order") )
	@GeneratedValue(generator = "foreignKey", strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@OneToOne(targetEntity = Order.class, mappedBy = "creditCardTransaction")
	@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
	public Order getOrder() {
		return order;
	}

	protected void setOrder(Order order) {
		this.order = order;
	}

	@Column(name = "CX_TYPE")
	public String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	@Column(name = "CX_NUM")
	public String getNumber() {
		return number;
	}

	protected void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "CX_NAME")
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Column(name = "CX_EXPIRY")
	public Date getExpirationDate() {
		return expirationDate;
	}

	protected void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "CX_AUTH_ID")
	public String getAuthorizationId() {
		return authorizationId;
	}

	protected void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	@Column(name = "CX_XACT_AMT")
	public double getAmount() {
		return amount;
	}

	protected void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "CX_XACT_DATE")
	public Date getAuthorizationDate() {
		return authorizationDate;
	}

	protected void setAuthorizationDate(Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	@ManyToOne
	@JoinColumn(name = "CX_CO_ID")
	public Country getCountry() {
		return country;
	}

	protected void setCountry(Country country) {
		this.country = country;
	}

	protected CreditCardTransaction() {

	}
}
