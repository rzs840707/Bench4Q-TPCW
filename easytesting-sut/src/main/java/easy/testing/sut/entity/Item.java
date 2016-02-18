package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * ITEM Table Layout
 * Field Name Field Definition Comments
 * I_ID Numeric, 9 digits Unique ID of Item
 * I_TITLE Variable text, size 60 Title of Item
 * I_A_ID Numeric, 9 digits Author ID of Item
 * I_PUB_DATE Date Date of release of the product
 * I_PUBLISHER Variable text, size 60 Publisher of item
 * I_SUBJECT Variable text, size 60 Subject of Book
 * I_DESC Variable text, size 500 Description of Item
 * I_SRP Numeric, (15,2) digits Suggested Retail Price
 * I_COST Numeric, (15,2) digits Cost of Item
 * I_AVAIL Date When item is available
 * I_ISBN Fixed text, size 13 Product ISBN
 * I_PAGE Numeric, 4 digits Number of pages of book
 * I_BACKING Variable text, size 15 Type of book, paper or hard back
 * I_DIMENSIONS Variable text, size 25 Size of book in inches
 * Primary Key: (I_ID)
 * (I_A_ID) Foreign Key, references (A_ID)
 */
@Entity
@Table(name = "ITEM")
public class Item {
	private int id;
	private String title;
	private Author author;
	private Date publishDate;
	private String publisher;
	private String subject;
	private String description;
	private double suggestedRetailPrice;
	private double cost;
	private Date availableDate;
	private String isbn;
	private int page;
	private String backing;
	private String dimensions;

	@Id
	@Column(name = "I_ID")
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Column(name = "I_TITLE")
	public String getTitle() {
		return title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne
	@JoinColumn(name = "I_A_ID")
	public Author getAuthor() {
		return author;
	}

	protected void setAuthor(Author author) {
		this.author = author;
	}

	@Column(name = "I_PUB_DATE")
	public Date getPublishDate() {
		return publishDate;
	}

	protected void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "I_PUBLISHER")
	public String getPublisher() {
		return publisher;
	}

	protected void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "I_SUBJECT")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "I_DESC")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "I_SRP")
	public double getSuggestedRetailPrice() {
		return suggestedRetailPrice;
	}

	public void setSuggestedRetailPrice(double suggestedRetailPrice) {
		this.suggestedRetailPrice = suggestedRetailPrice;
	}

	@Column(name = "I_COST")
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Column(name = "I_AVAIL")
	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	@Column(name = "I_ISBN")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "I_PAGE")
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Column(name = "I_BACKING")
	public String getBacking() {
		return backing;
	}

	public void setBacking(String backing) {
		this.backing = backing;
	}

	@Column(name = "I_DIMENSIONS")
	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	protected Item() {

	}
}
