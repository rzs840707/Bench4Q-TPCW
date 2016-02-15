package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderInquiryServlet extends HttpServlet {

	private static final long serialVersionUID = 8278172442856095993L;
	private static Logger LOGGER = LoggerFactory.getLogger(OrderInquiryServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Date before = new Date(System.currentTimeMillis());

		Util.determinePriorityLevel(req);

		PrintWriter out = res.getWriter();
		// Set the content type of this servlet's result.
		res.setContentType("text/html");
		String username = "";
		String url;
		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");

		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML><HEAD><TITLE>Order Inquiry Page</TITLE>\n");
		out.print("</HEAD><BODY BGCOLOR=\"#ffffff\">\n");
		out.print("<H1 ALIGN=\"center\">Bench4Q</H1>\n");
		out.print("<H1 ALIGN=\"center\">A QoS oriented B2C benchmark for Internetware Middleware</H1>\n");
		out.print("<H2 ALIGN=\"center\">Order Inquiry Page</H2>\n");

		// by xiaowei zhou, change "$sessionid$" to "jsessionid=", 2010.11.4
		String sessionIdStrToAppend = req.getRequestedSessionId();
		if (sessionIdStrToAppend != null) {
			sessionIdStrToAppend = ";jsessionid=" + sessionIdStrToAppend;
		} else {
			sessionIdStrToAppend = "";
		}

		// by xiaowei zhou, change "$sessionid$" to "jsessionid=", 2010.11.4
		out.print("<FORM ACTION=\"order_display" + sessionIdStrToAppend + "\" METHOD=\"get\">\n");

		out.print("<TABLE ALIGN=\"CENTER\">\n");
		out.print("<TR> <TD> <H4>Username:</H4></TD>\n");
		out.print("<TD><INPUT NAME=\"UNAME\" VALUE=\"" + username + "\" SIZE=\"23\"></TD></TR>\n");
		out.print("<TR><TD> <H4>Password:</H4></TD>\n");
		out.print("<TD> <INPUT NAME=\"PASSWD\" SIZE=\"14\" " + "TYPE=\"password\"></TD>\n");
		out.print("</TR></TABLE> <P ALIGN=\"CENTER\"><CENTER>\n");

		out.print("<INPUT TYPE=\"IMAGE\" NAME=\"Display Last Order\" " + "SRC=\"Images/display_last_order_B.gif\">\n");
		if (SHOPPING_ID != null)
			out.print("<INPUT TYPE=HIDDEN NAME=\"SHOPPING_ID\" value = \"" + SHOPPING_ID + "\">\n");
		if (C_ID != null)
			out.print("<INPUT TYPE=HIDDEN NAME=\"C_ID\" value = \"" + C_ID + "\">\n");
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
		out.print("</CENTER></FORM></BODY></HTML>");
		out.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("OrderInquiryServlet - " + (after.getTime() - before.getTime()) + " ms");
	}

}
