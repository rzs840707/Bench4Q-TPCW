package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/*
 * STOCK Table Layout
 * Field Name Field Definition Comments
 * S_I_ID Numeric, 9 digits Unique ID of Item
 * S_QTY Numeric, 9 digits Quantity in stock
 * S_LAST_MODIFIED Date and time Time of last received shipment
 * Primary Key: (S_I_ID)
 * (S_I_ID) Foreign Key, references (I_ID)
 */
@Entity
@Table(name = "STOCK")
public class Stock {
	private int id;
	private int quantity;
	private Date lastModified;
	private Item item;

	@Id
	@Column(name = "S_I_ID")
	@GenericGenerator(name = "foreignKey", strategy = "foreign", parameters = @Parameter(name = "property", value = "item") )
	@GeneratedValue(generator = "foreignKey", strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "S_QTY")
	public int getQuantity() {
		return quantity;
	}

	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "S_LAST_MODIFIED")
	public Date getLastModified() {
		return lastModified;
	}

	protected void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@OneToOne(targetEntity = Item.class, cascade = { CascadeType.ALL }, mappedBy = "stock")
	@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
	public Item getItem() {
		return item;
	}

	protected void setItem(Item item) {
		this.item = item;
	}

	protected Stock() {

	}
}
