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

public class CartLine {
	public String scl_title;
	public double scl_cost;
	public double scl_srp;
	public String scl_backing;
	public int scl_qty;
	public int scl_i_id;

	public CartLine(String title, double cost, double srp, String backing, int qty, int id) {
		scl_title = title;
		scl_cost = cost;
		scl_srp = srp;
		scl_backing = backing;
		scl_qty = qty;
		scl_i_id = id;
	}
}
