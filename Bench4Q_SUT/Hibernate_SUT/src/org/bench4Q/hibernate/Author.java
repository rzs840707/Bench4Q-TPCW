package org.bench4Q.hibernate;

import java.util.Date;

/**
 * Author entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Author extends AbstractAuthor implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Author() {
	}

	/** minimal constructor */
	public Author(Integer AId) {
		super(AId);
	}

	/** full constructor */
	public Author(Integer AId, String AFname, String ALname, String AMname,
			Date ADob, String ABio) {
		super(AId, AFname, ALname, AMname, ADob, ABio);
	}

}
