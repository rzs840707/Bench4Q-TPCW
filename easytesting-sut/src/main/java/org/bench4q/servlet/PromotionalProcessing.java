package org.bench4q.servlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import easy.testing.sut.entity.Item;
import easy.testing.sut.service.ItemService;

public class PromotionalProcessing {
	private static Logger LOGGER = LoggerFactory.getLogger(PromotionalProcessing.class);

	public static void displayPromotions(UUID requestUuid, ServletContext servletContext, PrintWriter out,
			HttpServletRequest req, HttpServletResponse res, int new_sid) {
		int I_ID = Util.getRandomI_ID();
		// Vector<Integer> related_item_ids = new Vector<Integer>();
		// Vector<String> thumbnails = new Vector<String>();
		int i;
		String url;

		Date databaseBefore = new Date(System.currentTimeMillis());
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		ItemService itemService = webApplicationContext.getBean(ItemService.class);
		List<Item> items = itemService.getRelatedItems(I_ID);
		// Database.getRelated(I_ID, related_item_ids, thumbnails);
		Date databaseAfter = new Date(System.currentTimeMillis());
		LOGGER.debug("PromotionalProcessing - " + requestUuid.toString() + " - Database - "
				+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");

		// Create table and "Click on our latest books..." row
		out.print("<TABLE ALIGN=CENTER BORDER=0 WIDTH=660>\n");
		out.print("<TR ALIGN=CENTER VALIGN=top>\n");
		out.print("<TD COLSPAN=5><B><FONT COLOR=#ff0000 SIZE=+1>" + "Click on one of our latest books to find out more!"
				+ "</FONT></B></TD></TR>\n");
		out.print("<TR ALIGN=CENTER VALIGN=top>\n");

		// Create links and references to book images
		for (i = 0; i < items.size(); i++) {
			url = "product_detail";
			url = url + "?I_ID=" + String.valueOf(items.get(i).getId());
			if (SHOPPING_ID != null) {
				url = url + "&SHOPPING_ID=" + SHOPPING_ID;
			} else if (new_sid != -1) {
				url = url + "&SHOPPING_ID=" + new_sid;
			}
			if (C_ID != null) {
				url = url + "&C_ID=" + C_ID;
			}
			out.print("<TD><A HREF=\"" + res.encodeURL(url));
			out.print("\"><IMG SRC=\"Images/" + items.get(i).getThumbnail() + "\" ALT=\"Book " + String.valueOf(i + 1)
					+ "\" WIDTH=\"100\" HEIGHT=\"150\"></A>\n");
			out.print("</TD>");
		}
		out.print("</TR></TABLE>\n");
	}

}
