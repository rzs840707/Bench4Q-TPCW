package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import easy.testing.sut.entity.Customer;
import easy.testing.sut.service.CustomerService;

public class BuyRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1381775065017531645L;
	private static Logger LOGGER = LoggerFactory.getLogger(BuyConfirmServlet.class);

	/**
	 * 2009-3-6 author: duanzhiquan Technology Center for Software Engineering
	 * Institute of Software, Chinese Academy of Sciences Beijing 100190, China
	 * Email:duanzhiquan07@otcaix.iscas.ac.cn
	 * 
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		UUID uuid = UUID.randomUUID();
		Date before = new Date(System.currentTimeMillis());

		PrintWriter out = res.getWriter();
		String url;
		// Set the content type of this servlet's result.
		res.setContentType("text/html");

		Util.determinePriorityLevel(req);

		String SHOPPING_ID = req.getParameter("SHOPPING_ID");
		String RETURNING_FLAG = req.getParameter("RETURNING_FLAG");

		Customer customer = null;

		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML><HEAD><TITLE>Buy Request</TITLE></HEAD>\n");
		out.print("<BODY BGCOLOR=\"ffffff\">\n");
		out.print("<H1 ALIGN=\"center\">Bench4Q</H1>\n");
		out.print("<H1 ALIGN=\"center\">A QoS oriented B2C benchmark for Internetware Middleware</H1>\n");
		out.print("<H2 ALIGN=\"CENTER\">Buy Request Page</H2>\n");
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		CustomerService customerService = webApplicationContext.getBean(CustomerService.class);
		if (RETURNING_FLAG == null) {
			out.print("ERROR: RETURNING_FLAG not set!</BODY><HTML>");
			return;
		}
		if (RETURNING_FLAG.equals("Y")) {
			String UNAME = req.getParameter("UNAME");
			String PASSWD = req.getParameter("PASSWD");
			if (UNAME.length() == 0 || PASSWD.length() == 0) {
				out.print("Error: Invalid Input</BODY></HTML>");
				return;
			}

			Date databaseBefore = new Date(System.currentTimeMillis());
			customer = customerService.getCustomerByUserName(UNAME);
			customerService.refreshSession(customer.getId());
			Date databaseAfter = new Date(System.currentTimeMillis());
			LOGGER.debug("BuyRequestServlet - " + uuid.toString() + " - Database - "
					+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");
			if (!PASSWD.equals(customer.getPassword())) {
				out.print("Error: Incorrect Password</BODY></HTML>");
				return;
			}
		} else if (RETURNING_FLAG.equals("N")) {
			String firstName = req.getParameter("FNAME");
			String lastName = req.getParameter("LNAME");
			String phone = req.getParameter("PHONE");
			String email = req.getParameter("EMAIL");
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("dd/MM/YYYY").parse(req.getParameter("BIRTHDATE"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String data = req.getParameter("DATA");

			String streetLine1 = req.getParameter("STREET1");
			String streetLine2 = req.getParameter("STREET2");
			String city = req.getParameter("CITY");
			String state = req.getParameter("STATE");
			String zipCode = req.getParameter("ZIP");
			String countryName = req.getParameter("COUNTRY");

			Date databaseBefore = new Date(System.currentTimeMillis());
			customer = customerService.newCustomer(firstName, lastName, phone, email, birthDate, data, streetLine1,
					streetLine2, city, state, zipCode, countryName);
			Date databaseAfter = new Date(System.currentTimeMillis());
			LOGGER.debug("BuyRequestServlet - " + uuid.toString() + " - Database - "
					+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		} else
			out.print("ERROR: RETURNING_FLAG not set to Y or N!\n");

		if (SHOPPING_ID == null) {
			out.print("ERROR: Shopping Cart ID not set!</BODY></HTML>");
			return;
		}
		// Update the shopping cart cost and get the current contents
		Date databaseBefore = new Date(System.currentTimeMillis());
		Cart mycart = Database.getCart(Integer.parseInt(SHOPPING_ID), customer.getDiscount());
		Date databaseAfter = new Date(System.currentTimeMillis());
		LOGGER.debug("BuyRequestServlet - " + uuid.toString() + " - Database - "
				+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		String sessionIdStrToAppend = Util.appendSessionId(req);

		// Print out the web page

		// by xiaowei zhou, change "$sessionid$" to "jsessionid=", 2010.11.4
		out.print("<HR><FORM ACTION=\"buy_confirm" + sessionIdStrToAppend + "\" METHOD=\"GET\">\n");

		out.print("<TABLE BORDER=\"0\" WIDTH=\"90%\">\n");
		out.print("<TR ALIGN=\"LEFT\" VALIGN=\"TOP\">\n");
		out.print("<TD VALIGN=\"TOP\" WIDTH=\"45%\">\n");
		out.print("<H2>Billing Information:</H2>\n");
		out.print("<TABLE WIDTH=\"100%\" BORDER=\"0\"><TR>\n");

		out.print("<TR><TD>Firstname:</TD><TD>" + customer.getFirstName() + "</TD></TR>\n");
		out.print("<TR><TD>Lastname: </TD><TD>" + customer.getLastName() + "</TD></TR>\n");
		out.print("<TR><TD>Addr_street_1:</TD><TD>" + customer.getAddress().getStreetLine1() + "</TD></TR>\n");
		out.print("<TR><TD>Addr_street_2:</TD><TD>" + customer.getAddress().getStreetLine2() + "</TD></TR>\n");
		out.print("<TR><TD>City:</TD><TD>" + customer.getAddress().getCity() + "</TD></TR>\n");
		out.print("<TR><TD>State:</TD><TD>" + customer.getAddress().getState() + "</TD></TR>\n");
		out.print("<TR><TD>Zip:</TD><TD>" + customer.getAddress().getZipCode() + "</TD></TR>\n");
		out.print("<TR><TD>Country:</TD><TD>" + customer.getAddress().getCountry().getName() + "</TD></TR>\n");
		out.print("<TR><TD>Email:</TD><TD>" + customer.getEmail() + "</TD></TR>\n");
		out.print("<TR><TD>Phone:</TD><TD>" + customer.getPhone() + "</TD></TR>\n");
		if (RETURNING_FLAG.equals("N")) {
			out.print("<TR><TD>USERNAME:</TD><TD>" + customer.getUserName() + "</TD></TR>\n");
			out.print("<TR><TD>C_ID:</TD><TD>" + customer.getId() + "</TD></TR>\n");
		}
		out.print("</TABLE></TD>");

		//
		// The Shipping Info Form
		//

		out.print("<TD VALIGN=\"TOP\" WIDTH=\"45%\">\n");
		out.print("<H2>Shipping Information:</H2>\n");
		out.print("<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\" WIDTH=\"100%\">\n");
		out.print("<TR><TD WIDTH=\"50%\">Addr_street_1:</TD>\n");
		out.print("<TD><INPUT NAME=\"STREET_1\" SIZE=\"40\" VALUE=\"\"></TD></TR>\n");
		out.print("<TR><TD>Addr_street_ 2:</TD>\n");
		out.print("<TD><INPUT NAME=\"STREET_2\" SIZE=\"40\" VALUE=\"\"></TD></TR>\n");
		out.print("<TR><TD>City:</TD><TD><INPUT NAME=\"CITY\" SIZE=\"30\" VALUE=\"\"></TD></TR>\n");
		out.print("<TR><TD>State:</TD><TD><INPUT NAME=\"STATE\" SIZE=\"20\" VALUE=\"\"></TD></TR>\n");
		out.print("<TR><TD>Zip:</TD><TD><INPUT NAME=\"ZIP\" SIZE=\"10\" VALUE=\"\"></TD></TR>\n");
		out.print("<TR><TD>Country:</TD><TD><INPUT NAME=\"COUNTRY\" VALUE=\"\" SIZE=\"40\"></TD></TR>\n");

		//
		// Order Information Section
		//

		out.print("</TABLE></TD></TR></TABLE>\n");
		out.print("<HR><H2>Order Information:</H2>\n");
		out.print("<TABLE BORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"0\">\n");
		out.print("<TR><TD><B>Qty</B></TD><TD><B>Product</B></TD></TR>\n");

		// Insert Shopping Cart Contents Here!
		//
		int i;
		for (i = 0; i < mycart.lines.size(); i++) {
			CartLine thisline = (CartLine) mycart.lines.elementAt(i);
			out.print("<TR><TD VALIGN=\"TOP\">" + thisline.scl_qty + "</TD>\n");
			out.print("<TD VALIGN=\"TOP\">Title:<I>" + thisline.scl_title + "</I> - Backing: " + thisline.scl_backing);
			out.print("<BR>SRP. $" + thisline.scl_srp);
			out.print("<FONT COLOR=\"#aa0000\">\n");
			out.print("<B>Your Price:" + thisline.scl_cost + "</B>\n");
			out.print("</FONT></TD></TR>");
		}

		out.print("</TABLE>\n");
		out.print("<P><BR></P><TABLE BORDER=\"0\">\n");
		out.print("<TR><TD><B>Subtotal with discount (" + customer.getDiscount()
				+ "%):</B></TD><TD ALIGN=\"RIGHT\"><B>$" + mycart.SC_SUB_TOTAL + "</B></TD></TR>\n");
		out.print("<TR><TD><B>Tax</B></TD><TD ALIGN=\"RIGHT\"><B>$" + mycart.SC_TAX + "</B></TD></TR>\n");
		out.print("<TR><TD><B>Shipping &amp; Handling</B></TD><TD ALIGN=\"RIGHT\"><B>$" + mycart.SC_SHIP_COST
				+ "</B></TD></TR>\n");
		out.print("<TR><TD><B>Total</B></TD><TD ALIGN=\"RIGHT\"><B>$" + mycart.SC_TOTAL + "</B></TD></TR></TABLE>\n");

		//
		// Credit Card Stuff
		//
		out.print("<HR WIDTH=\"700\"><P><BR></P>\n");

		out.print("<TABLE BORDER=\"1\" CELLPADDING=\"5\" " + "CELLSPACING=\"0\"><TR>\n");
		out.print("<TD>Credit Card Type</TD>\n");
		out.print("<TD><INPUT TYPE=\"RADIO\" NAME=\"CC_TYPE\" VALUE=\"Visa\" " + "CHECKED=\"CHECKED\">Visa\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"CC_TYPE\" " + "VALUE=\"Master\">MasterCard\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"CC_TYPE\" " + "VALUE=\"Discover\">Discover\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"CC_TYPE\" " + "VALUE=\"Amex\">American Express\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"CC_TYPE\" " + "VALUE=\"Diners\">Diners</TD></TR>\n");

		out.print("<TR><TD>Name on Credit Card</TD>\n");
		out.print("<TD><INPUT NAME=\"CC_NAME\" SIZE=\"30\" VALUE=\"\"></TD></TR>\n");
		out.print("<TR><TD>Credit Card Number</TD>\n");
		out.print("<TD><INPUT NAME=\"CC_NUMBER\" SIZE=\"16\" VALUE=\"\"></TD></TR>\n");
		out.print("<TR><TD>Credit Card Expiration Date</TD>\n");
		out.print("<TD><INPUT NAME=\"CC_EXPIRY\" SIZE=\"15\" VALUE=\"\"></TD></TR>\n");

		out.print("<TR><TD>Shipping Method</TD>\n");
		out.print("<TD><INPUT TYPE=\"RADIO\" NAME=\"SHIPPING\" VALUE=\"AIR\" CHECKED=\"CHECKED\">AIR");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"SHIPPING\" VALUE=\"UPS\">UPS\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"SHIPPING\" VALUE=\"FEDEX\">FEDEX\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"SHIPPING\" VALUE=\"SHIP\">SHIP\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"SHIPPING\" VALUE=\"COURIER\">COURIER\n");
		out.print("<INPUT TYPE=\"RADIO\" NAME=\"SHIPPING\" VALUE=\"MAIL\">MAIL\n");
		out.print("</TD></TR></TABLE><P><CENTER>\n");
		if (SHOPPING_ID != null)
			out.print("<INPUT TYPE=HIDDEN NAME=\"SHOPPING_ID\" value = \"" + SHOPPING_ID + "\">\n");
		out.print("<INPUT TYPE=HIDDEN NAME=\"C_ID\" value = \"" + customer.getId() + "\">\n");
		out.print("<INPUT TYPE=\"IMAGE\" NAME=\"Confirm Buy\" SRC=\"Images/submit_B.gif\">\n");
		url = "shopping_cart?ADD_FLAG=N&C_ID=" + customer.getId();
		if (SHOPPING_ID != null)
			url = url + "&SHOPPING_ID=" + SHOPPING_ID;
		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"Images/shopping_cart_B.gif\" " + "ALT=\"Shopping Cart\"></A>\n");

		url = "order_inquiry?C_ID=" + customer.getId();
		if (SHOPPING_ID != null)
			url = url + "&SHOPPING_ID=" + SHOPPING_ID;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"Images/order_status_B.gif\" " + "ALT=\"Order Status\"></A>\n");
		out.print("</P></CENTER></BODY></HTML>");
		out.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("BuyRequestServlet - " + uuid.toString() + " - Total - " + (after.getTime() - before.getTime()) + " ms");
	}

}
