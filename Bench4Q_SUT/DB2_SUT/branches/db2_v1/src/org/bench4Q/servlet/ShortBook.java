/**
 * 
 * Create at:   2009-5-14
 * @version:    1.0 
 *
 * 
 * Copyright Technology Center for Software Engineering,Institute of Software, Chinese Academy of Sciences 
 * and distributed according to the GNU Lesser General Public Licence. 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by   
 * the Free Software Foundation; either version 2.1 of the License, or any
 * later version.
 * See Copyright.txt for full copyright information.
 *
 *
 *  http://otc.iscas.ac.cn/
 * Technology Center for Software Engineering
 * Institute of Software, Chinese Academy of Sciences
 * Beijing 100190, China
 *
 * This version is a based on the implementation from University of Wisconsin
 *
 *
 *  * Initial developer(s): Zhiquan Duan, Wei Wang.
 * 
 */
package org.bench4Q.servlet;

import java.sql.ResultSet;

public class ShortBook {
	// Construct a book from a ResultSet
	public ShortBook(ResultSet rs) {
		// The result set should have all of the fields we expect.
		// This relies on using field name access. It might be a bad
		// way to break this up since it does not allow us to use the
		// more efficient select by index access method. This also
		// might be a problem since there is no type checking on the
		// result set to make sure it is even a reasonble result set
		// to give to this function.

		try {
			i_id = rs.getInt("i_id");
			i_title = rs.getString("i_title");
			a_fname = rs.getString("a_fname");
			a_lname = rs.getString("a_lname");
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}
	}

	// From Item
	public int i_id;
	public String i_title;
	public String a_fname;
	public String a_lname;
}
