package org.bench4q.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 6149172269244554791L;
	private static Logger LOGGER = LoggerFactory.getLogger(SearchRequestServlet.class);

	/**
	 * 2009-3-6 author: duanzhiquan Technology Center for Software Engineering
	 * Institute of Software, Chinese Academy of Sciences Beijing 100190, China
	 * Email:duanzhiquan07@otcaix.iscas.ac.cn
	 * 
	 * 
	 */

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		LOGGER.debug("Enter function: doGet");
		PrintWriter out = res.getWriter();
		// Set the content type of this servlet's result.
		res.setContentType("text/html");
		HttpSession session = req.getSession(false);

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

		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");
		String url;

		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML> <HEAD><TITLE>Search Request Page</TITLE></HEAD>\n");
		out.print("<BODY BGCOLOR=\"#ffffff\">\n");
		out.print("<H2 ALIGN=\"center\">");
		out.print("<H2 ALIGN=\"center\">Search Request Page</H2>");

		// Insert Promotional processing
		promotional_processing.DisplayPromotions(out, req, res, -1);

		// by xiaowei zhou, change "$sessionid$" to "jsessionid=", 2010.11.4
		String sessionIdStrToAppend = req.getRequestedSessionId();
		if (sessionIdStrToAppend != null) {
			sessionIdStrToAppend = ";jsessionid=" + sessionIdStrToAppend;
		} else {
			sessionIdStrToAppend = "";
		}

		// by xiaowei zhou, change "$sessionid$" to "jsessionid=", 2010.11.4
		out.print("<FORM ACTION=\"execute_search" + sessionIdStrToAppend + "\" METHOD=\"get\">\n");

		out.print("<TABLE ALIGN=\"center\"><TR><TD ALIGN=\"right\">\n");
		out.print("<H3>Search by:</H3></TD><TD WIDTH=\"100\"></TD></TR>\n");
		out.print("<TR><TD ALIGN=\"right\">\n");
		out.print("<SELECT NAME=\"search_type\" SIZE=\"1\">\n");
		out.print("<OPTION SELECTED=\"SELECTED\" VALUE=\"author\">Author</OPTION>\n");
		out.print("<OPTION VALUE=\"title\">Title</OPTION>\n");
		out.print("<OPTION VALUE=\"subject\">Subject</OPTION></SELECT></TD>\n");

		out.print("<TD><INPUT NAME=\"search_string\" SIZE=\"30\"></TD></TR>\n");
		out.print("</TABLE>\n");
		out.print("<P ALIGN=\"CENTER\"><CENTER>\n");
		out.print("<INPUT TYPE=\"IMAGE\" NAME=\"Search\"" + " SRC=\"Images/submit_B.gif\">\n");

		if (SHOPPING_ID != null)
			out.print("<INPUT TYPE=HIDDEN NAME=\"SHOPPING_ID\" value = \"" + SHOPPING_ID + "\">\n");
		if (C_ID != null)
			out.print("<INPUT TYPE=HIDDEN NAME=\"C_ID\" value = \"" + C_ID + "\">\n");

		url = "home";
		if (SHOPPING_ID != null) {
			url = url + "?SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
		} else if (C_ID != null)
			url = url + "?C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeUrl(url));
		out.print("\"><IMG SRC=\"Images/home_B.gif\" ALT=\"Home\"></A>\n");
		url = "shopping_cart?ADD_FLAG=N";
		if (SHOPPING_ID != null)
			url = url + "&SHOPPING_ID=" + SHOPPING_ID;
		if (C_ID != null)
			url = url + "&C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeUrl(url));
		out.print("\"><IMG SRC=\"Images/shopping_cart_B.gif\"" + " ALT=\"Shopping Cart\"></A>\n");
		out.print("</CENTER></P></FORM></BODY></HTML>");
		out.close();
		LOGGER.debug("Exit function: doGet");
	}
}
