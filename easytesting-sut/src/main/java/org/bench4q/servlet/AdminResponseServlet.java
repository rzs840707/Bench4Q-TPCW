package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

public class AdminResponseServlet extends HttpServlet {

	private static final long serialVersionUID = -1096843381068600700L;
	private static Logger LOGGER = LoggerFactory.getLogger(AdminResponseServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		UUID uuid = UUID.randomUUID();
		Date before = new Date(System.currentTimeMillis());

		String url;
		PrintWriter out = res.getWriter();

		// Set the content type of this servlet's result.
		res.setContentType("text/html");

		Util.determinePriorityLevel(req);

		// Pull out the parameters
		int I_ID = Integer.parseInt(req.getParameter("I_ID"));
		String I_NEW_IMAGE = req.getParameter("I_NEW_IMAGE");
		String I_NEW_THUMBNAIL = req.getParameter("I_NEW_THUMBNAIL");
		String I_NEW_COSTstr = req.getParameter("I_NEW_COST");
		Double I_NEW_COSTdbl = Double.valueOf(I_NEW_COSTstr);

		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");

		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ItemService itemService = webApplicationContext.getBean(ItemService.class);

		// Get this book out of the database
		Date databaseBefore = new Date(System.currentTimeMillis());
		Item item = itemService.getItemById(I_ID);
		Date databaseAfter = new Date(System.currentTimeMillis());
		LOGGER.debug("AdminResponseServlet - " + uuid.toString() + " - Database - "
				+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		// Spit out the HTML
		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML> <HEAD><TITLE>Admin Response Page</TITLE></HEAD>\n");
		out.print("<BODY BGCOLOR=\"#FFFFFF\">\n");
		out.print("<H1 ALIGN=\"center\">Bench4Q</H1>\n");
		out.print("<H1 ALIGN=\"center\">A QoS oriented B2C benchmark for Internetware Middleware</H1>\n");

		if (I_NEW_COSTstr.length() == 0 || I_NEW_IMAGE.length() == 0 || I_NEW_THUMBNAIL.length() == 0) {
			out.print("<H2>Invalid Input</H2>");
		} else {

			// Update the database
			databaseBefore = new Date(System.currentTimeMillis());
			int itemId = I_ID;
			double newCost = I_NEW_COSTdbl.doubleValue();
			String newImage = I_NEW_IMAGE;
			String newThumbnail = I_NEW_THUMBNAIL;
			itemService.updateInformation(itemId, newCost, newImage, newThumbnail);
			itemService.updateRelatedItems(itemId);
			databaseAfter = new Date(System.currentTimeMillis());
			LOGGER.debug("AdminResponseServlet - " + uuid.toString() + " - Database - "
					+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

			out.print("<H2>Product Updated</H2>");
			out.print("<H2>Title: " + item.getTitle() + "</H2>\n");
			out.print(
					"<P>Author: " + item.getAuthor().getFirstName() + " " + item.getAuthor().getLastName() + "</P>\n");
			out.print("<P><IMG SRC=\"Images/" + I_NEW_IMAGE + "\" ALIGN=\"RIGHT\" BORDER=\"0\" WIDTH=\"200\" "
					+ "HEIGHT=\"200\">");
			out.print("<IMG SRC=\"Images/" + I_NEW_THUMBNAIL + "\" ALT=\"Book 1\" ALIGN=\"RIGHT\" WIDTH=\"100\""
					+ " HEIGHT=\"150\">\n");
			out.print("Description: " + item.getDescription() + "</P>\n");
			out.print("<BLOCKQUOTE><P><B>Suggested Retail: $" + item.getSuggestedRetailPrice()
					+ "</B><BR><B>Our Price: </B><FONT COLOR=\"#DD0000\"><B>" + I_NEW_COSTstr
					+ "</B></FONT><BR><B>You Save: </B><FONT " + "COLOR=\"#DD0000\"><B>"
					+ Double.toString((item.getSuggestedRetailPrice() - (Double.valueOf(I_NEW_COSTstr)).doubleValue()))
					+ "</B></FONT></P></BLOCKQUOTE> ");
			out.print("<P><FONT SIZE=\"2\">" + item.getBacking() + ", " + item.getPage() + " pages<BR>\n");
			out.print("Published by " + item.getPublisher() + "<BR>\n");
			out.print("Publication date: " + item.getPublishDate() + "<BR>\n");
			out.print("Dimensions (in inches): " + item.getDimensions() + "<BR>\n");
			out.print("ISBN: " + item.getIsbn() + "</FONT><BR CLEAR=\"ALL\"></P>\n");

			out.print("<CENTER>");
			url = "search_request";
			if (SHOPPING_ID != null) {
				url = url + "?SHOPPING_ID=" + SHOPPING_ID;
				if (C_ID != null)
					url = url + "&C_ID=" + C_ID;
			} else if (C_ID != null) {
				url = url + "?C_ID=" + C_ID;
			}

			out.print("<A HREF=\"" + res.encodeURL(url));
			out.print("\"><IMG SRC=\"Images/search_B.gif\" " + "ALT=\"Search\"></A>\n");

			url = "home";
			if (SHOPPING_ID != null) {
				url = url + "?SHOPPING_ID=" + SHOPPING_ID;
				if (C_ID != null)
					url = url + "&C_ID=" + C_ID;
			} else if (C_ID != null) {
				url = url + "?C_ID=" + C_ID;
			}

			out.print("<A HREF=\"" + res.encodeURL(url));

			out.print("\"><IMG SRC=\"Images/home_B.gif\" " + "ALT=\"Home\"></A></P></CENTER>\n");

			out.print("</FORM>\n");
		}
		out.print("</BODY></HTML>");
		out.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug(
				"AdminResponseServlet - " + uuid.toString() + " - Total - " + (after.getTime() - before.getTime()) + " ms");
	}

}
