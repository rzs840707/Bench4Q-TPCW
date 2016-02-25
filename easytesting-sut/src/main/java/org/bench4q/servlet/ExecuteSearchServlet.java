package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import easy.testing.sut.entity.Item;
import easy.testing.sut.service.ItemService;

public class ExecuteSearchServlet extends HttpServlet {

	private static final long serialVersionUID = -3888865319481400580L;
	private static Logger LOGGER = LoggerFactory.getLogger(ExecuteSearchServlet.class);

	/**
	 * 2009-3-6 author: duanzhiquan Technology Center for Software Engineering
	 * Institute of Software, Chinese Academy of Sciences Beijing 100190, China
	 * Email:duanzhiquan07@otcaix.iscas.ac.cn
	 * 
	 * 
	 */

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Date before = new Date(System.currentTimeMillis());

		int i;

		Util.determinePriorityLevel(req);

		String search_type = req.getParameter("search_type");
		String search_string = req.getParameter("search_string");

		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");
		String url;
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");

		// Set the content type of this servlet's result.
		res.setContentType("text/html");
		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML><HEAD><TITLE>Search Results Page: " + search_string + "</TITLE></HEAD>\n");
		out.print("<BODY BGCOLOR=\"#ffffff\">\n");
		out.print("<P ALIGN=\"center\">\n");

		out.print("<H2 ALIGN=\"center\">Search Result Page - " + search_type + ": " + search_string + "</H2>\n");

		// Display promotions
		PromotionalProcessing.displayPromotions(this.getServletContext(), out, req, res, -1);

		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ItemService itemService = webApplicationContext.getBean(ItemService.class);

		List<Item> items = null; // placate javac
		// Display new products
		Date databaseBefore = new Date(System.currentTimeMillis());
		if (search_type.equals("author")) {
			items = itemService.getItemsByAuthor(search_string, 50);
		} else if (search_type.equals("title")) {
			items = itemService.getItemsByTitle(search_string, 50);
		} else if (search_type.equals("subject")) {
			items = itemService.getItemsBySubject(search_string, 50);
		}
		Date databaseAfter = new Date(System.currentTimeMillis());
		LOGGER.debug(
				"ExecuteSearchServlet - Database - " + (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		out.print("<TABLE BORDER=\"1\" CELLPADDING=\"1\" CELLSPACING=\"1\">\n");
		out.print("<TR> <TD WIDTH=\"30\"></TD>\n");
		out.print("<TD><FONT SIZE=\"+1\">Author</FONT></TD>\n");
		out.print("<TD><FONT SIZE=\"+1\">Title</FONT></TD></TR>\n");

		// Print out a line for each item returned by the DB
		for (i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			out.print("<TR><TD>" + (i + 1) + "</TD>\n");
			out.print("<TD><I>" + item.getAuthor().getFirstName() + " " + item.getAuthor().getLastName() + "</I></TD>");
			url = "product_detail?I_ID=" + String.valueOf(item.getId());
			if (SHOPPING_ID != null)
				url = url + "&SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
			out.print("<TD><A HREF=\"" + res.encodeURL(url));
			out.print("\">" + item.getTitle() + "</A></TD></TR>\n");
		}

		out.print("</TABLE><P><CENTER>\n");

		url = "shopping_cart?ADD_FLAG=N";
		if (SHOPPING_ID != null)
			url = url + "&SHOPPING_ID=" + SHOPPING_ID;
		if (C_ID != null)
			url = url + "&C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"Images/shopping_cart_B.gif\" " + "ALT=\"Shopping Cart\"></A>\n");

		url = "search_request";
		if (SHOPPING_ID != null) {
			url = url + "?SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
		} else if (C_ID != null)
			url = url + "?C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"Images/search_B.gif\" " + "ALT=\"Search\"></A>\n");

		url = "home";
		if (SHOPPING_ID != null) {
			url = url + "?SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
		} else if (C_ID != null)
			url = url + "?C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"Images/home_B.gif\" " + "ALT=\"Home\"></A></P></CENTER>\n");
		out.print("</BODY> </HTML>\n");
		out.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("ExecuteSearchServlet - " + (after.getTime() - before.getTime()) + " ms");
	}

}
