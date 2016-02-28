package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

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

public class BestSellersServlet extends HttpServlet {

	private static final long serialVersionUID = -9007706622743406361L;
	private static Logger LOGGER = LoggerFactory.getLogger(BestSellersServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		UUID uuid = UUID.randomUUID();
		Date before = new Date(System.currentTimeMillis());

		String url;
		PrintWriter out = res.getWriter();

		Util.determinePriorityLevel(req);

		String subject = req.getParameter("subject");
		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");

		// Set the content type of this servlet's result.
		res.setContentType("text/html");
		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML><HEAD><TITLE> Best Sellers: " + subject + "</TITLE></HEAD>\n");
		out.print("<BODY BGCOLOR=\"#ffffff\">\n");
		out.print("<P ALIGN=\"center\">\n");

		out.print("<H2 ALIGN=\"center\">Best Sellers Page - Subject: " + subject + "</H2>\n");

		// Display promotions
		PromotionalProcessing.displayPromotions(uuid, this.getServletContext(), out, req, res, -1);

		// Display new products

		out.print("<TABLE BORDER=\"1\" CELLPADDING=\"1\" CELLSPACING=\"1\">\n");
		out.print("<TR> <TD WIDTH=\"30\"></TD>\n");
		out.print("<TD><FONT SIZE=\"+1\">Author</FONT></TD>\n");
		out.print("<TD><FONT SIZE=\"+1\">Title</FONT></TD></TR>\n");

		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ItemService itemService = webApplicationContext.getBean(ItemService.class);

		// Get best sellers from DB
		Date databaseBefore = new Date(System.currentTimeMillis());
		List<Item> items = itemService.getBestSellers(subject, 50);
		Date databaseAfter = new Date(System.currentTimeMillis());
		LOGGER.debug("BestSellersServlet - " + uuid.toString() + " - Database - "
				+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		// Print out the best sellers.
		int i;
		for (i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			out.print("<TR><TD>" + (i + 1) + "</TD>\n");
			out.print(
					"<TD><I>" + item.getAuthor().getFirstName() + " " + item.getAuthor().getLastName() + "</I></TD>\n");
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
		LOGGER.debug("BestSellerServlet - " + uuid.toString() + " - " + (after.getTime() - before.getTime()) + " ms");
	}
}
