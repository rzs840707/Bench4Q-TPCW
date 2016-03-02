package easy.testing.sut.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * ITEM Table Layout
 * Field Name	Field Definition	Comments
 * I_ID	Numeric, 10 digits	Unique ID of Item
 * I_TITLE	Variable text, size 60	Title of Item
 * I_A_ID	Numeric, 10 digits	Author ID of Item
 * I_PUB_DATE	Date	Date of release of the product
 * I_PUBLISHER	Variable text, size 60	Publisher of item
 * I_SUBJECT	Variable text, size 60	Subject of Book
 * I_DESC	Variable text, size 500	Description of Item
 * I_RELATED1	Numeric, 10 digits	Unique Item ID (I_ID) of related item
 * I_RELATED2	Numeric, 10 digits	Unique Item ID (I_ID) of related item
 * I_RELATED3	Numeric, 10 digits	Unique Item ID (I_ID) of related item
 * I_RELATED4	Numeric, 10 digits	Unique Item ID (I_ID) of related item
 * I_RELATED5	Numeric, 10 digits	Unique Item ID (I_ID) of related item
 * I_THUMBNAIL	Image	Thumbnail image of Item or pointer to thumbnail image
 * I_IMAGE	Image	Item image or pointer to image
 * I_SRP	Numeric, (15,2) digits	Suggested Retail Price
 * I_COST	Numeric, (15,2) digits	Cost of Item
 * I_AVAIL	Date 	When item is available
 * I_STOCK	Numeric, 4 digits	Quantity in stock
 * I_ISBN	Fixed text, size 13	Product ISBN
 * I_PAGE	Numeric, 4 digits	Number of pages of book
 * I_BACKING	Variable text, size 15	Type of book, paper or hard back
 * I_DIMENSIONS	Variable text, size 25	Size of book in inches
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
	private int related1;
	private int related2;
	private int related3;
	private int related4;
	private int related5;
	private String thumbnail;
	private String image;
	private double suggestedRetailPrice;
	private double cost;
	private Date availableDate;
	private int stock;
	private String isbn;
	private int page;
	private String backing;
	private String dimensions;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	protected void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "I_DESC")
	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "I_RELATED1")
	public int getRelated1() {
		return related1;
	}

	protected void setRelated1(int related1) {
		this.related1 = related1;
	}

	@Column(name = "I_RELATED2")
	public int getRelated2() {
		return related2;
	}

	protected void setRelated2(int related2) {
		this.related2 = related2;
	}

	@Column(name = "I_RELATED3")
	public int getRelated3() {
		return related3;
	}

	protected void setRelated3(int related3) {
		this.related3 = related3;
	}

	@Column(name = "I_RELATED4")
	public int getRelated4() {
		return related4;
	}

	protected void setRelated4(int related4) {
		this.related4 = related4;
	}

	@Column(name = "I_RELATED5")
	public int getRelated5() {
		return related5;
	}

	protected void setRelated5(int related5) {
		this.related5 = related5;
	}

	@Column(name = "I_THUMBNAIL")
	public String getThumbnail() {
		return thumbnail;
	}

	protected void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Column(name = "I_IMAGE")
	public String getImage() {
		return image;
	}

	protected void setImage(String image) {
		this.image = image;
	}

	@Column(name = "I_SRP")
	public double getSuggestedRetailPrice() {
		return suggestedRetailPrice;
	}

	protected void setSuggestedRetailPrice(double suggestedRetailPrice) {
		this.suggestedRetailPrice = suggestedRetailPrice;
	}

	@Column(name = "I_COST")
	public double getCost() {
		return cost;
	}

	protected void setCost(double cost) {
		this.cost = cost;
	}

	@Column(name = "I_AVAIL")
	public Date getAvailableDate() {
		return availableDate;
	}

	protected void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	@Column(name = "I_STOCK")
	public int getStock() {
		return stock;
	}

	protected void setStock(int stock) {
		this.stock = stock;
	}

	@Column(name = "I_ISBN")
	public String getIsbn() {
		return isbn;
	}

	protected void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "I_PAGE")
	public int getPage() {
		return page;
	}

	protected void setPage(int page) {
		this.page = page;
	}

	@Column(name = "I_BACKING")
	public String getBacking() {
		return backing;
	}

	protected void setBacking(String backing) {
		this.backing = backing;
	}

	@Column(name = "I_DIMENSIONS")
	public String getDimensions() {
		return dimensions;
	}

	protected void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	protected Item() {

	}

	public void updateInformation(double newCost, String newImage, String newThumbnail) {
		this.setCost(newCost);
		this.setImage(newImage);
		this.setThumbnail(newThumbnail);
		this.setPublishDate(new Date(System.currentTimeMillis()));
	}

	public void updateStock(int newStock) {
		this.setStock(newStock);
	}

	public void updateRelatedItems(int related1, int related2, int related3, int related4, int related5) {
		this.setRelated1(related1);
		this.setRelated2(related2);
		this.setRelated3(related3);
		this.setRelated4(related4);
		this.setRelated5(related5);
	}

}
