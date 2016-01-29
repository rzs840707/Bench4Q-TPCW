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

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class promotional_processing {

	public static void DisplayPromotions(PrintWriter out, HttpServletRequest req,
			HttpServletResponse res, int new_sid) {
		int I_ID = Util.getRandomI_ID();
		Vector related_item_ids = new Vector();
		Vector thumbnails = new Vector();
		int i;
		String url;

		Database.getRelated(I_ID, related_item_ids, thumbnails);

		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");

		// Create table and "Click on our latest books..." row
		out.print("<TABLE ALIGN=CENTER BORDER=0 WIDTH=660>\n");
		out.print("<TR ALIGN=CENTER VALIGN=top>\n");
		out.print("<TD COLSPAN=5><B><FONT COLOR=#ff0000 SIZE=+1>"
				+ "Click on one of our latest books to find out more!" + "</FONT></B></TD></TR>\n");
		out.print("<TR ALIGN=CENTER VALIGN=top>\n");

		// Create links and references to book images
		for (i = 0; i < related_item_ids.size(); i++) {
			url = "product_detail";
			url = url + "?I_ID=" + String.valueOf(related_item_ids.elementAt(i));
			if (SHOPPING_ID != null)
				url = url + "&SHOPPING_ID=" + SHOPPING_ID;
			else if (new_sid != -1)
				url = url + "&SHOPPING_ID=" + new_sid;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
			out.print("<TD><A HREF=\"" + res.encodeUrl(url));
			out.print("\"><IMG SRC=\"Images/" + thumbnails.elementAt(i) + "\" ALT=\"Book "
					+ String.valueOf(i + 1) + "\" WIDTH=\"100\" HEIGHT=\"150\"></A>\n");
			out.print("</TD>");
		}
		out.print("</TR></TABLE>\n");
	}

}
