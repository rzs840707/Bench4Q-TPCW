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
		UUID uuid = UUID.randomUUID();
		Date before = new Date(System.currentTimeMillis());

		PrintWriter out = res.getWriter();
		// Set the content type of this servlet's result.
		res.setContentType("text/html");

		Util.determinePriorityLevel(req);

		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");
		String url;

		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML> <HEAD><TITLE>Search Request Page</TITLE></HEAD>\n");
		out.print("<BODY BGCOLOR=\"#ffffff\">\n");
		out.print("<H2 ALIGN=\"center\">");
		out.print("<H2 ALIGN=\"center\">Search Request Page</H2>");

		// Insert Promotional processing
		PromotionalProcessing.displayPromotions(this.getClass(), uuid, this.getServletContext(), out, req, res, -1);

		String sessionIdStrToAppend = Util.appendSessionId(req);

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

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print(
				"\"><IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), "home_B.gif") + "\" ALT=\"Home\"></A>\n");
		url = "shopping_cart?ADD_FLAG=N";
		if (SHOPPING_ID != null)
			url = url + "&SHOPPING_ID=" + SHOPPING_ID;
		if (C_ID != null)
			url = url + "&C_ID=" + C_ID;

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), "shopping_cart_B.gif") + "\""
				+ " ALT=\"Shopping Cart\"></A>\n");
		out.print("</CENTER></P></FORM></BODY></HTML>");
		out.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("SearchRequestServlet - " + uuid.toString() + " - Total - " + (after.getTime() - before.getTime())
				+ " ms");
	}

}
