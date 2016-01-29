/**
 * =========================================================================
 * 					Bench4Q version 1.0.0
 * =========================================================================
 * 
 * Bench4Q is available on the Internet at http://forge.ow2.org/projects/jaspte
 * You can find latest version there. 
 * 
 * Distributed according to the GNU Lesser General Public Licence. 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by   
 * the Free Software Foundation; either version 2.1 of the License, or any
 * later version.
 * 
 * SEE Copyright.txt FOR FULL COPYRIGHT INFORMATION.
 * 
 * This source code is distributed "as is" in the hope that it will be
 * useful.  It comes with no warranty, and no author or distributor
 * accepts any responsibility for the consequences of its use.
 *
 *
 * This version is a based on the implementation of TPC-W from University of Wisconsin. 
 * This version used some source code of The Grinder.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *  * Initial developer(s): Zhiquan Duan.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * 
 */
package org.bench4Q.servlet;

import java.util.Date;

import org.bench4Q.hibernate.Author;
import org.bench4Q.hibernate.Item;

public class Book {
	// Construct a book from a ResultSet
//	public Book(ResultSet rs) {
//		// The result set should have all of the fields we expect.
//		// This relies on using field name access. It might be a bad
//		// way to break this up since it does not allow us to use the
//		// more efficient select by index access method. This also
//		// might be a problem since there is no type checking on the
//		// result set to make sure it is even a reasonble result set
//		// to give to this function.
//
//		try {
//			i_id = rs.getInt("i_id");
//			i_title = rs.getString("i_title");
//			i_pub_Date = rs.getDate("i_pub_Date");
//			i_publisher = rs.getString("i_publisher");
//			i_subject = rs.getString("i_subject");
//			i_desc = rs.getString("i_desc");
//			i_related1 = rs.getInt("i_related1");
//			i_related2 = rs.getInt("i_related2");
//			i_related3 = rs.getInt("i_related3");
//			i_related4 = rs.getInt("i_related4");
//			i_related5 = rs.getInt("i_related5");
//			i_thumbnail = rs.getString("i_thumbnail");
//			i_image = rs.getString("i_image");
//			i_srp = rs.getDouble("i_srp");
//			i_cost = rs.getDouble("i_cost");
//			i_avail = rs.getDate("i_avail");
//			i_isbn = rs.getString("i_isbn");
//			i_page = rs.getInt("i_page");
//			i_backing = rs.getString("i_backing");
//			i_dimensions = rs.getString("i_dimensions");
//			a_id = rs.getInt("a_id");
//			a_fname = rs.getString("a_fname");
//			a_lname = rs.getString("a_lname");
//		} catch (java.lang.Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	
	

	// From Item
	public int i_id;
	
	public Book(int iId, String iTitle, Date iPubDate, String iPublisher,
			String iSubject, String iDesc, int iRelated1, int iRelated2,
			int iRelated3, int iRelated4, int iRelated5, String iThumbnail,
			String iImage, double iSrp, double iCost, Date iAvail,
			String iIsbn, int iPage, String iBacking, String iDimensions,
			int aId, String aFname, String aLname) {
		super();
		i_id = iId;
		i_title = iTitle;
		i_pub_Date = iPubDate;
		i_publisher = iPublisher;
		i_subject = iSubject;
		i_desc = iDesc;
		i_related1 = iRelated1;
		i_related2 = iRelated2;
		i_related3 = iRelated3;
		i_related4 = iRelated4;
		i_related5 = iRelated5;
		i_thumbnail = iThumbnail;
		i_image = iImage;
		i_srp = iSrp;
		i_cost = iCost;
		i_avail = iAvail;
		i_isbn = iIsbn;
		i_page = iPage;
		i_backing = iBacking;
		i_dimensions = iDimensions;
		a_id = aId;
		a_fname = aFname;
		a_lname = aLname;
	}
	
	public Book(Item item, Author author) {
		super();
		i_id = item.getIId();
		i_title = item.getITitle();
		i_pub_Date = item.getIPubDate();
		i_publisher = item.getIPublisher();
		i_subject = item.getISubject();
		i_desc = item.getIDesc();
		i_related1 = item.getIRelated1();
		i_related2 = item.getIRelated2();
		i_related3 = item.getIRelated3();
		i_related4 = item.getIRelated4();
		i_related5 = item.getIRelated5();
		i_thumbnail = item.getIThumbnail();
		i_image = item.getIImage();
		i_srp = item.getISrp();
		i_cost = item.getICost();
		i_avail = item.getIAvail();
		i_isbn = item.getIIsbn();
		i_page = item.getIPage();
		i_backing = item.getIBacking();
		i_dimensions = item.getIDimensions();
		a_id = author.getAId();
		a_fname = author.getAFname();
		a_lname = author.getALname();
	}
	
	public String i_title;
	// public int i_a_id; // Redundant
	public Date i_pub_Date;
	public String i_publisher;
	public String i_subject;
	public String i_desc;
	public int i_related1;
	public int i_related2;
	public int i_related3;
	public int i_related4;
	public int i_related5;
	public String i_thumbnail;
	public String i_image;
	public double i_srp;
	public double i_cost;
	public Date i_avail;
	public String i_isbn;
	public int i_page;
	public String i_backing;
	public String i_dimensions;

	// From Author
	public int a_id;
	public String a_fname;
	public String a_lname;
}
