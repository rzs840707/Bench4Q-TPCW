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
import java.util.Vector;

public class Cart {

	public double SC_SUB_TOTAL;
	public double SC_TAX;
	public double SC_SHIP_COST;
	public double SC_TOTAL;

	public Vector lines;

	public Cart(ResultSet rs, double C_DISCOUNT) throws java.sql.SQLException {
		int i;
		int total_items;
		lines = new Vector();
		while (rs.next()) {// While there are lines remaining
			CartLine line = new CartLine(rs.getString("i_title"), rs.getDouble("i_cost"), rs
					.getDouble("i_srp"), rs.getString("i_backing"), rs.getInt("scl_qty"), rs
					.getInt("scl_i_id"));
			lines.addElement(line);
		}

		SC_SUB_TOTAL = 0;
		total_items = 0;
		for (i = 0; i < lines.size(); i++) {
			CartLine thisline = (CartLine) lines.elementAt(i);
			SC_SUB_TOTAL += thisline.scl_cost * thisline.scl_qty;
			total_items += thisline.scl_qty;
		}

		// Need to multiply the sub_total by the discount.
		SC_SUB_TOTAL = SC_SUB_TOTAL * ((100 - C_DISCOUNT) * 0.01);
		SC_TAX = SC_SUB_TOTAL * .0825;
		SC_SHIP_COST = 3.00 + (1.00 * total_items);
		SC_TOTAL = SC_SUB_TOTAL + SC_SHIP_COST + SC_TAX;
	}
}
