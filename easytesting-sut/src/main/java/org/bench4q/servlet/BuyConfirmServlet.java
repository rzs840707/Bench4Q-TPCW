package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import easy.testing.sut.entity.BuyResult;
import easy.testing.sut.entity.ShoppingCartLine;
import easy.testing.sut.service.BuyService;

// DATABASE CONNECTIVITY NEEDED: Lots!
// 1. Given a SHOPPING_ID, I need a way to get the SCL_ID, SC_COST, and
// SC_QTY for each item in the shopping cart, as well as the SC_SUBTOTAL,
// SC_TAX, SC_SHIP_COST, and SC_TOTAL for the entire cart.
//
// 2. Given a C_ID, I need a DB call that returns the C_FNAME, C_LNAME,
// C_DISCOUNT, and C_ADDR_ID from the customer table.
//
// 3. I need a function which takes, as parameters, STREET_1, STREET_2, CITY,
// STATE, ZIP, and COUNTRY. The DB code should search the ADDRESS table for
// and address which matches these parameters. If one does not exist,
// a new row is added to the ADDRESS table with these parameters as columns.
// The lookup and the insertion must happen in a single DB transaction
// This is described in section 2.7.3.2

// 4. I also need a DB function which does what is described in section
// 2.7.3.3, given the following parameters: C_ID, SC_SUB_TOTAL, SC_TOTAL,
// SHIPPING, C_ADDR_ID, and ADDR_ID. This involves putting the order in
// The ORDER DB table, creating a message to the SSL PGE (which is currently
// never sent), putting an entry in the CC table, and then clearing all of
// the items from the cart. This function should return the newly created
// unique O_ID.
//   

public class BuyConfirmServlet extends HttpServlet {

	private static final long serialVersionUID = -8321102146103956957L;
	private static Logger LOGGER = LoggerFactory.getLogger(BuyConfirmServlet.class);

	/**
	 * 2009-3-6 author: duanzhiquan Technology Center for Software Engineering
	 * Institute of Software, Chinese Academy of Sciences Beijing 100190, China
	 * Email:duanzhiquan07@otcaix.iscas.ac.cn
	 * 
	 * 
	 */

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		BuyService buyService = webApplicationContext.getBean(BuyService.class);

		UUID uuid = UUID.randomUUID();
		Date before = new Date(System.currentTimeMillis());

		int i;
		String url;
		PrintWriter out = res.getWriter();
		// Set the content type of this servlet's result.
		res.setContentType("text/html");

		Util.determinePriorityLevel(req);

		String SHOPPING_IDstr = req.getParameter("SHOPPING_ID");
		int SHOPPING_ID = Integer.parseInt(SHOPPING_IDstr);
		String C_IDstr = req.getParameter("C_ID");
		int C_ID = Integer.parseInt(C_IDstr);

		String CC_TYPE = req.getParameter("CC_TYPE");
		String CC_NUMBERstr = req.getParameter("CC_NUMBER");
		long CC_NUMBER = Long.parseLong(CC_NUMBERstr);
		String CC_NAME = req.getParameter("CC_NAME");
		String CC_EXPIRYstr = req.getParameter("CC_EXPIRY");
		java.util.Date CC_EXPIRY = null;
		try {
			CC_EXPIRY = new SimpleDateFormat("dd/MM/YYYY").parse(CC_EXPIRYstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String SHIPPING = req.getParameter("SHIPPING");

		String STREET_1 = req.getParameter("STREET_1");
		BuyResult result = null;
		if (!STREET_1.equals("")) {
			String STREET_2 = req.getParameter("STREET_2");
			String CITY = req.getParameter("CITY");
			String STATE = req.getParameter("STATE");
			String ZIP = req.getParameter("ZIP");
			String COUNTRY = req.getParameter("COUNTRY");
			Date databaseBefore = new Date(System.currentTimeMillis());
			result = buyService.buy(SHOPPING_ID, C_ID, CC_TYPE, CC_NUMBER, CC_NAME, new Date(CC_EXPIRY.getTime()),
					SHIPPING, STREET_1, STREET_2, CITY, STATE, ZIP, COUNTRY);
			Date databaseAfter = new Date(System.currentTimeMillis());
			LOGGER.debug("BuyConfirmServlet - " + uuid.toString() + " - Database - "
					+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");
		} else {
			Date databaseBefore = new Date(System.currentTimeMillis());
			result = buyService.buy(SHOPPING_ID, C_ID, CC_TYPE, CC_NUMBER, CC_NAME, new Date(CC_EXPIRY.getTime()),
					SHIPPING);
			Date databaseAfter = new Date(System.currentTimeMillis());
			LOGGER.debug("BuyConfirmServlet - " + uuid.toString() + " - Database - "
					+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");
		}

		// Make Database call to read the current countent of the shopping
		// cart, etc

		// Make a call to database to update the order table
		// and do ssl stuff, (We are not currently passing info to/from a PGE).

		// Print out the HTML page
		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\"> <HTML>\n");
		out.print("<HEAD><TITLE>Order Confirmation</TITLE></HEAD> ");
		out.print("<BODY BGCOLOR=\"#FFFFFF\">");
		out.print("<H1 ALIGN=\"center\">Bench4Q</H1>\n");
		out.print("<H1 ALIGN=\"center\">A QoS oriented B2C benchmark for Internetware Middleware</H1>\n");
		out.print("<H2 ALIGN=\"CENTER\">Buy Confirm Page</H2>\n");
		out.print("<BLOCKQUOTE><BLOCKQUOTE><BLOCKQUOTE><BLOCKQUOTE>\n");
		out.print("<H2 ALIGN=\"LEFT\">Order Information:</H2>\n");
		out.print("<TABLE BORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"0\">\n");
		out.print("<TR><TD><B>Qty</B></TD><TD><B>Product</B></TD></TR> ");

		// For each item in the shopping cart, print out its contents
		for (i = 0; i < result.getShoppingCartList().getShoppingCartLines().size(); i++) {
			ShoppingCartLine line = result.getShoppingCartList().getShoppingCartLines().get(i);
			out.print("<TR><TD VALIGN=\"TOP\">" + line.getQuantity() + "</TD>\n");
			out.print("<TD VALIGN=\"TOP\">Title:<I>" + line.getItem().getTitle() + "</I> - Backing: "
					+ line.getItem().getBacking() + "<BR>SRP. $" + line.getItem().getSuggestedRetailPrice()
					+ "<FONT COLOR=\"#aa0000\"><B>Your Price: $" + line.getItem().getCost() + "</FONT> </TD></TR>\n");
		}
		out.print("</TABLE><H2 ALIGN=\"LEFT\">Your Order has been processed.</H2>\n");
		out.print("<TABLE BORDER=\"1\" CELLPADDING=\"5\" CELLSPACING=\"0\">\n");
		out.print("<TR><TD><H4>Subtotal with discount:</H4></TD>\n");
		out.print("<TD> <H4>$" + result.getShoppingCartList().getSubTotal() + "</H4></TD></TR>");
		out.print("<TR><TD><H4>Tax (8.25%):</H4></TD>\n");
		out.print("<TD><H4>$" + result.getShoppingCartList().getTax() + "</H4></TD></TR>\n");
		out.print("<TR><TD><H4>Shipping &amp; Handling:</H4></TD>\n");
		out.print("<TD><H4>$" + result.getShoppingCartList().getShipCost() + "</H4></TD></TR>\n");
		out.print("<TR><TD> <H4>Total:</H4></TD>\n");
		out.print("<TD><H4>$" + result.getShoppingCartList().getTotalCost() + "</H4></TD></TR></TABLE>\n");
		out.print("<P><BR></P><H2>Order Number: " + result.getOrder().getId() + "</H2>\n");
		out.print("<!--STUB Total:" + result.getShoppingCartList().getTotalCost() + "-->\n");
		out.print("<H1>Thank you for shopping at Bench4Q</H1> <P></P>\n");

		// Add the buttons
		url = "search_request?SHOPPING_ID=" + SHOPPING_ID;
		if (C_IDstr != null)
			url = url + "&C_ID=" + C_IDstr;
		out.print("<CENTER><P><A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), "search_B.gif") + "\""
				+ " ALT=\"Search\"></A>\n");

		url = "home?SHOPPING_ID=" + SHOPPING_ID;
		if (C_IDstr != null)
			url = url + "&C_ID=" + C_IDstr;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print(
				"\"><IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), "home_B.gif") + "\" ALT=\"Home\"></A>\n");
		out.print("</CENTER></BLOCKQUOTE></BLOCKQUOTE></BLOCKQUOTE>" + "</BLOCKQUOTE></BODY></HTML>");
		out.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("BuyConfirmServlet - " + uuid.toString() + " - Total - " + (after.getTime() - before.getTime())
				+ " ms");
	}

}
