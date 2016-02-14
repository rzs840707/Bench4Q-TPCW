package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		HttpSession session = req.getSession(false);

		determinePriorityLevel(req, session);

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
		PromotionalProcessing.displayPromotions(out, req, res, -1);

		Vector<Book> books = null; // placate javac
		// Display new products
		Date databaseBefore = new Date(System.currentTimeMillis());
		if (search_type.equals("author")) {
			books = Database.doAuthorSearch(search_string);
		} else if (search_type.equals("title")) {
			books = Database.doTitleSearch(search_string);
		} else if (search_type.equals("subject")) {
			books = Database.doSubjectSearch(search_string);
		}
		Date databaseAfter = new Date(System.currentTimeMillis());
		LOGGER.debug(
				"ExecuteSearchServlet - Database - " + (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		out.print("<TABLE BORDER=\"1\" CELLPADDING=\"1\" CELLSPACING=\"1\">\n");
		out.print("<TR> <TD WIDTH=\"30\"></TD>\n");
		out.print("<TD><FONT SIZE=\"+1\">Author</FONT></TD>\n");
		out.print("<TD><FONT SIZE=\"+1\">Title</FONT></TD></TR>\n");

		// Print out a line for each item returned by the DB
		for (i = 0; i < books.size(); i++) {
			Book myBook = (Book) books.elementAt(i);
			out.print("<TR><TD>" + (i + 1) + "</TD>\n");
			out.print("<TD><I>" + myBook.a_fname + " " + myBook.a_lname + "</I></TD>");
			url = "product_detail?I_ID=" + String.valueOf(myBook.i_id);
			if (SHOPPING_ID != null)
				url = url + "&SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
			out.print("<TD><A HREF=\"" + res.encodeURL(url));
			out.print("\">" + myBook.i_title + "</A></TD></TR>\n");
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

	private void determinePriorityLevel(HttpServletRequest req, HttpSession session) {
		// by xiaowei zhou, determine session-based differentiated service
		// priority level, 20101116
		String strSessionPriorityLevel = req.getParameter(Util.SESSION_PRIORITY_KEY);
		Integer igrSessionPri = null;
		if (strSessionPriorityLevel != null && !strSessionPriorityLevel.equals("")) {
			try {
				igrSessionPri = Integer.valueOf(strSessionPriorityLevel);
			} catch (NumberFormatException e) {
				// ignore, use default
			}
			if (igrSessionPri != null) {
				if (igrSessionPri < 1 || igrSessionPri > Util.PRIORITY_LEVELS) {
					igrSessionPri = Util.DEFAULT_PRIORITY;
				}
				if (session != null) {
					session.setAttribute(Util.DIFFSERV_SESSION_PRIORITY_KEY, igrSessionPri);
				}
			}
		}
	}
}
