package org.bench4Q.hibernate;

import java.util.Date;

/**
 * AbstractItem entity provides the base persistence definition of the Item
 * entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractItem implements java.io.Serializable {

	// Fields

	private Integer IId;
	private String ITitle;
	private Integer IAId;
	private Date IPubDate;
	private String IPublisher;
	private String ISubject;
	private String IDesc;
	private Integer IRelated1;
	private Integer IRelated2;
	private Integer IRelated3;
	private Integer IRelated4;
	private Integer IRelated5;
	private String IThumbnail;
	private String IImage;
	private Double ISrp;
	private Double ICost;
	private Date IAvail;
	private Integer IStock;
	private String IIsbn;
	private Integer IPage;
	private String IBacking;
	private String IDimensions;

	// Constructors

	/** default constructor */
	public AbstractItem() {
	}

	/** minimal constructor */
	public AbstractItem(Integer IId) {
		this.IId = IId;
	}

	/** full constructor */
	public AbstractItem(Integer IId, String ITitle, Integer IAId,
			Date IPubDate, String IPublisher, String ISubject, String IDesc,
			Integer IRelated1, Integer IRelated2, Integer IRelated3,
			Integer IRelated4, Integer IRelated5, String IThumbnail,
			String IImage, Double ISrp, Double ICost, Date IAvail,
			Integer IStock, String IIsbn, Integer IPage, String IBacking,
			String IDimensions) {
		this.IId = IId;
		this.ITitle = ITitle;
		this.IAId = IAId;
		this.IPubDate = IPubDate;
		this.IPublisher = IPublisher;
		this.ISubject = ISubject;
		this.IDesc = IDesc;
		this.IRelated1 = IRelated1;
		this.IRelated2 = IRelated2;
		this.IRelated3 = IRelated3;
		this.IRelated4 = IRelated4;
		this.IRelated5 = IRelated5;
		this.IThumbnail = IThumbnail;
		this.IImage = IImage;
		this.ISrp = ISrp;
		this.ICost = ICost;
		this.IAvail = IAvail;
		this.IStock = IStock;
		this.IIsbn = IIsbn;
		this.IPage = IPage;
		this.IBacking = IBacking;
		this.IDimensions = IDimensions;
	}

	// Property accessors

	public Integer getIId() {
		return this.IId;
	}

	public void setIId(Integer IId) {
		this.IId = IId;
	}

	public String getITitle() {
		return this.ITitle;
	}

	public void setITitle(String ITitle) {
		this.ITitle = ITitle;
	}

	public Integer getIAId() {
		return this.IAId;
	}

	public void setIAId(Integer IAId) {
		this.IAId = IAId;
	}

	public Date getIPubDate() {
		return this.IPubDate;
	}

	public void setIPubDate(Date IPubDate) {
		this.IPubDate = IPubDate;
	}

	public String getIPublisher() {
		return this.IPublisher;
	}

	public void setIPublisher(String IPublisher) {
		this.IPublisher = IPublisher;
	}

	public String getISubject() {
		return this.ISubject;
	}

	public void setISubject(String ISubject) {
		this.ISubject = ISubject;
	}

	public String getIDesc() {
		return this.IDesc;
	}

	public void setIDesc(String IDesc) {
		this.IDesc = IDesc;
	}

	public Integer getIRelated1() {
		return this.IRelated1;
	}

	public void setIRelated1(Integer IRelated1) {
		this.IRelated1 = IRelated1;
	}

	public Integer getIRelated2() {
		return this.IRelated2;
	}

	public void setIRelated2(Integer IRelated2) {
		this.IRelated2 = IRelated2;
	}

	public Integer getIRelated3() {
		return this.IRelated3;
	}

	public void setIRelated3(Integer IRelated3) {
		this.IRelated3 = IRelated3;
	}

	public Integer getIRelated4() {
		return this.IRelated4;
	}

	public void setIRelated4(Integer IRelated4) {
		this.IRelated4 = IRelated4;
	}

	public Integer getIRelated5() {
		return this.IRelated5;
	}

	public void setIRelated5(Integer IRelated5) {
		this.IRelated5 = IRelated5;
	}

	public String getIThumbnail() {
		return this.IThumbnail;
	}

	public void setIThumbnail(String IThumbnail) {
		this.IThumbnail = IThumbnail;
	}

	public String getIImage() {
		return this.IImage;
	}

	public void setIImage(String IImage) {
		this.IImage = IImage;
	}

	public Double getISrp() {
		return this.ISrp;
	}

	public void setISrp(Double ISrp) {
		this.ISrp = ISrp;
	}

	public Double getICost() {
		return this.ICost;
	}

	public void setICost(Double ICost) {
		this.ICost = ICost;
	}

	public Date getIAvail() {
		return this.IAvail;
	}

	public void setIAvail(Date IAvail) {
		this.IAvail = IAvail;
	}

	public Integer getIStock() {
		return this.IStock;
	}

	public void setIStock(Integer IStock) {
		this.IStock = IStock;
	}

	public String getIIsbn() {
		return this.IIsbn;
	}

	public void setIIsbn(String IIsbn) {
		this.IIsbn = IIsbn;
	}

	public Integer getIPage() {
		return this.IPage;
	}

	public void setIPage(Integer IPage) {
		this.IPage = IPage;
	}

	public String getIBacking() {
		return this.IBacking;
	}

	public void setIBacking(String IBacking) {
		this.IBacking = IBacking;
	}

	public String getIDimensions() {
		return this.IDimensions;
	}

	public void setIDimensions(String IDimensions) {
		this.IDimensions = IDimensions;
	}

}