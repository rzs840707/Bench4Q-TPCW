package org.bench4Q.hibernate;

import java.util.Date;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Item extends AbstractItem implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Item() {
	}

	/** minimal constructor */
	public Item(Integer IId) {
		super(IId);
	}

	/** full constructor */
	public Item(Integer IId, String ITitle, Integer IAId, Date IPubDate,
			String IPublisher, String ISubject, String IDesc,
			Integer IRelated1, Integer IRelated2, Integer IRelated3,
			Integer IRelated4, Integer IRelated5, String IThumbnail,
			String IImage, Double ISrp, Double ICost, Date IAvail,
			Integer IStock, String IIsbn, Integer IPage, String IBacking,
			String IDimensions) {
		super(IId, ITitle, IAId, IPubDate, IPublisher, ISubject, IDesc,
				IRelated1, IRelated2, IRelated3, IRelated4, IRelated5,
				IThumbnail, IImage, ISrp, ICost, IAvail, IStock, IIsbn, IPage,
				IBacking, IDimensions);
	}

}
