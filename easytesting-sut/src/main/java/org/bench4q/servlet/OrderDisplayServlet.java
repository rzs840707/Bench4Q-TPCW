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

import easy.testing.sut.entity.Customer;
import easy.testing.sut.entity.Order;
import easy.testing.sut.entity.OrderLine;
import easy.testing.sut.service.CustomerService;
import easy.testing.sut.service.OrderService;

public class OrderDisplayServlet extends HttpServlet {

	private static final long serialVersionUID = 783064388417406500L;
	private static Logger LOGGER = LoggerFactory.getLogger(OrderDisplayServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		UUID uuid = UUID.randomUUID();
		Date before = new Date(System.currentTimeMillis());

		PrintWriter out = res.getWriter();

		Util.determinePriorityLevel(req);

		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");
		String url;

		// Set the content type of this servlet's result.
		res.setContentType("text/html");

		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML><HEAD><TITLE>Order Display Page</TITLE></HEAD>\n");
		out.print("<H1 ALIGN=\"center\">Bench4Q</H1>\n");
		out.print("<H1 ALIGN=\"center\">A QoS oriented B2C benchmark for Internetware Middleware</H1>\n");
		out.print("<H2 ALIGN=\"CENTER\">Order Display Page</H2>\n");
		out.print("<BLOCKQUOTE> <BLOCKQUOTE> <BLOCKQUOTE> <BLOCKQUOTE> <HR>\n");

		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		CustomerService customerService = webApplicationContext.getBean(CustomerService.class);
		OrderService orderService = webApplicationContext.getBean(OrderService.class);

		String uname = req.getParameter("UNAME");
		String passwd = req.getParameter("PASSWD");
		if (uname != null && passwd != null) {
			Date databaseBefore = new Date(System.currentTimeMillis());
			Customer customer = customerService.getCustomerByUserName(uname);
			if (!customer.getPassword().equals(passwd)) {
				out.print("Error: Incorrect password.\n");
			} else {
				Order order = orderService.getMostRecentOrder(uname);
				if (order != null) {
					List<OrderLine> orderLines = orderService.getOrderLines(order.getId());
					printOrder(order, orderLines, out);
				} else {
					out.print("User has no order!\n");
				}
			}
			Date databaseAfter = new Date(System.currentTimeMillis());
			LOGGER.debug("OrderDisplayServlet - " + uuid.toString() + " - Database - "
					+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		} else
			out.print("Error:order_display, " + "uname and passwd not set!.\n");

		// Print out the buttons that are on the bottom of the page
		out.print("<CENTER>\n");
		url = "search_request";
		if (SHOPPING_ID != null) {
			url = url + "?SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
		} else if (C_ID != null)
			url = url + "?C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"" + Util.buildImageUrl(this.getServletContext(), uuid, this.getClass(), "search_B.gif")
				+ "\" " + "ALT=\"Search\"></A>\n");

		url = "home";
		if (SHOPPING_ID != null) {
			url = url + "?SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
		} else if (C_ID != null)
			url = url + "?C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"" + Util.buildImageUrl(this.getServletContext(), uuid, this.getClass(), "home_B.gif")
				+ "\" " + "ALT=\"Home\"></A></P></CENTER>\n");
		out.print("</CENTER></FORM></BODY></HTML>");
		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("OrderDisplayServlet - " + uuid.toString() + " - Total - " + (after.getTime() - before.getTime())
				+ " ms");
	}

	private void printOrder(Order order, List<OrderLine> lines, PrintWriter out) {
		int i;
		out.print("<P>Order ID:" + order.getId() + "<BR>\n");
		out.print("Order Placed on " + order.getOrderDate() + "<BR>\n");
		out.print("Shipping Type:" + order.getShipType() + "<BR>\n");
		out.print("Ship Date: " + order.getShipDate() + "<BR>\n");
		out.print("Order Subtotal: " + order.getSubTotal() + "<BR>\n");
		out.print("Order Tax: " + order.getTax() + "<BR>\n");
		out.print("Order Total:" + order.getTotal() + "<BR></P>\n");

		out.print("<TABLE BORDER=\"0\" WIDTH=\"80%\">\n");
		out.print("<TR><TD><B>Bill To:</B></TD><TD><B>Ship To:</B></TD></TR>");
		out.print("<TR><TD COLSPAN=\"2\"> <H4>" + order.getCustomer().getFirstName() + " "
				+ order.getCustomer().getLastName() + "</H4></TD></TR>\n");
		out.print("<TR><TD WIDTH=\"50%\"><ADDRESS>" + order.getShipAddress().getStreetLine1() + "<BR>\n");
		out.print(order.getShipAddress().getStreetLine2() + "<BR>\n");
		out.print(order.getShipAddress().getState() + " " + order.getShipAddress().getZipCode() + "<BR>\n");
		out.print(order.getShipAddress().getCountry().getName() + "<BR><BR>\n");
		out.print("Email: " + order.getCustomer().getEmail() + "<BR>\n");
		out.print("Phone: " + order.getCustomer().getPhone() + "</ADDRESS><BR><P>\n");
		out.print("Credit Card Type: " + order.getCreditCardTransaction().getType() + "<BR>\n");
		out.print("Order Status: " + order.getStatus() + "</P></TD>\n");
		out.print("<TD VALIGN=\"TOP\" WIDTH=\"50%\"><ADDRESS>" + order.getBillAddress().getStreetLine1() + "<BR>\n");
		out.print(order.getBillAddress().getStreetLine2() + "<BR>\n");
		out.print(order.getBillAddress().getState() + " " + order.getBillAddress().getZipCode() + "<BR>\n");
		out.print(order.getBillAddress().getCountry().getName() + "\n");
		out.print("</ADDRESS></TD></TR></TABLE>");
		out.print("</BLOCKQUOTE></BLOCKQUOTE></BLOCKQUOTE></ BLOCKQUOTE>");

		// Print out the list of items
		out.print("<CENTER><TABLE BORDER=\"1\" CELLPADDING=\"5\"" + " CELLSPACING=\"0\">\n");
		out.print("<TR><TD><H4>Item #</H4></TD>");
		out.print("<TD><H4>Title</H4></TD>");
		out.print("<TD> <H4>Cost</H4></TD>");
		out.print("<TD> <H4>Qty</H4></TD> ");
		out.print("<TD> <H4>Discount</H4></TD>");
		out.print("<TD> <H4>Comment</H4></TD></TR>\n");
		if (lines != null) {
			for (i = 0; i < lines.size(); i++) {
				OrderLine line = lines.get(i);
				out.print("<TR>");
				out.print("<TD> <H4>" + line.getItem().getId() + "</H4></TD>\n");
				out.print("<TD VALIGN=\"top\"><H4>" + line.getItem().getTitle() + "<BR>Publisher: "
						+ line.getItem().getPublisher() + "</H4></TD>\n");
				out.print("<TD> <H4>" + line.getItem().getCost() + "</H4></TD>\n"); // Cost
				out.print("<TD> <H4>" + line.getQuantity() + "</H4></TD>\n"); // Qty
				out.print("<TD> <H4>" + line.getDiscount() + "</H4></TD>\n"); // Discount
				out.print("<TD> <H4>" + line.getComments() + "</H4></TD></TR>\n");
			}
		}
		out.print("</TABLE><BR></CENTER>\n");
		out.close();

	}
}
