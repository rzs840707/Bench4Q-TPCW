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

public class OrderLine {
	public OrderLine(ResultSet rs) {
		try {
			ol_i_id = rs.getInt("ol_i_id");
			i_title = rs.getString("i_title");
			i_publisher = rs.getString("i_publisher");
			i_cost = rs.getDouble("i_cost");
			ol_qty = rs.getInt("ol_qty");
			ol_discount = rs.getDouble("ol_discount");
			ol_comments = rs.getString("ol_comments");
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
		}
	}

	public int ol_i_id;
	public String i_title;
	public String i_publisher;
	public double i_cost;
	public int ol_qty;
	public double ol_discount;
	public String ol_comments;
}
