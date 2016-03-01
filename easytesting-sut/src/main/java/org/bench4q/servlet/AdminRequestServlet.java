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

public class AdminRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 8997466111706977319L;
	private static Logger LOGGER = LoggerFactory.getLogger(AdminRequestServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		UUID uuid = UUID.randomUUID();
		Date before = new Date(System.currentTimeMillis());

		String url;
		PrintWriter out = res.getWriter();

		// Set the content type of this servlet's result.
		res.setContentType("text/html");

		Util.determinePriorityLevel(req);

		String I_IDstr = req.getParameter("I_ID");
		String C_ID = req.getParameter("C_ID");
		String SHOPPING_ID = req.getParameter("SHOPPING_ID");

		String sessionIdStrToAppend = Util.appendSessionId(req);

		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ItemService itemService = webApplicationContext.getBean(ItemService.class);

		int I_ID = Integer.parseInt(I_IDstr, 10);

		Date databaseBefore = new Date(System.currentTimeMillis());
		Item item = itemService.getItemById(I_ID);
		Date databaseAfter = new Date(System.currentTimeMillis());
		LOGGER.debug("AdminRequestServlet - " + uuid.toString() + " - Database - "
				+ (databaseAfter.getTime() - databaseBefore.getTime()) + " ms");

		out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
		out.print("<HTML><HEAD><TITLE>Product Update Page</TITLE></HEAD>");
		out.print("<BODY BGCOLOR=\"#ffffff\">\n");
		out.print("<H1 ALIGN=\"center\">Bench4Q</H1>\n");
		out.print("<H1 ALIGN=\"center\">A QoS oriented B2C benchmark for Internetware Middleware</H1>\n");

		out.print("<H2 ALIGN=\"center\">Admin Request Page</H2>");

		out.print("<H2 ALIGN=\"center\">Title:" + item.getTitle() + "</H2>\n");
		out.print("<P ALIGN=\"LEFT\">Author: " + item.getAuthor().getFirstName() + " " + item.getAuthor().getLastName()
				+ "<BR></P>\n");
		out.print("<IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), item.getImage())
				+ "\" ALIGN=\"RIGHT\" BORDER=\"0\" " + "WIDTH=\"200\" HEIGHT=\"200\" >\n");
		out.print("<IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), item.getThumbnail())
				+ "\" ALIGN=\"RIGHT\" BORDER=\"0\">");
		out.print("<P><BR><BR></P>");

		// by xiaowei zhou, change "$sessionid$" to "jsessionid=", 2010.11.4
		out.print("<FORM ACTION=\"admin_response" + sessionIdStrToAppend + "\" METHOD=\"get\">\n");

		out.print("<INPUT NAME=\"I_ID\" TYPE=\"hidden\" VALUE=\"" + I_ID + "\">\n");
		out.print("<TABLE BORDER=\"0\">\n");
		out.print(
				"<TR><TD><B>Suggested Retail:</B></TD><TD><B>$ " + item.getSuggestedRetailPrice() + "</B></TD></TR>\n");
		out.print("<TR><TD><B>Our Current Price: </B></TD>" + "<TD><FONT COLOR=\"#dd0000\"><B>$ " + item.getCost()
				+ "</B></FONT></TD></TR>\n");
		out.print(
				"<TR><TD><B>Enter New Price</B></TD>" + "<TD ALIGN=\"right\">$ <INPUT NAME=\"I_NEW_COST\"></TD></TR>");
		out.print("<TR><TD><B>Enter New Picture</B></TD><TD ALIGN=\"right\">"
				+ "<INPUT NAME=\"I_NEW_IMAGE\"></TD></TR>\n");
		out.print("<TR><TD><B>Enter New Thumbnail</B></TD><TD ALIGN=\"RIGHT\">"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"I_NEW_THUMBNAIL\"></TD></TR>\n");
		out.print("</TABLE>");
		out.print("<P><BR CLEAR=\"ALL\"></P> <P ALIGN=\"center\">");
		if (SHOPPING_ID != null) {
			out.print("<INPUT TYPE=HIDDEN NAME=\"SHOPPING_ID\" value = \"" + SHOPPING_ID + "\">\n");
		}
		if (C_ID != null) {
			out.print("<INPUT TYPE=HIDDEN NAME=\"C_ID\" value = \"" + C_ID + "\">\n");
		}
		out.print("<INPUT TYPE=\"IMAGE\" NAME=\"Submit\"" + " SRC=\"Images/submit_B.gif\">\n");
		url = "search_request";
		if (SHOPPING_ID != null) {
			url = url + "?SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
		} else if (C_ID != null) {
			url = url + "?C_ID=" + C_ID;
		}
		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), "search_B.gif") + "\" "
				+ "ALT=\"Search\"></A>\n");
		url = "home";
		if (SHOPPING_ID != null) {
			url = url + "?SHOPPING_ID=" + SHOPPING_ID;
			if (C_ID != null)
				url = url + "&C_ID=" + C_ID;
		} else if (C_ID != null) {
			url = url + "?C_ID=" + C_ID;
		}

		out.print("<A HREF=\"" + res.encodeURL(url));
		out.print("\"><IMG SRC=\"" + Util.buildImageUrl(uuid, this.getClass(), "home_B.gif") + "\" "
				+ "ALT=\"Home\"></A></P>\n");

		out.print("</FORM></BODY></HTML>");
		out.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("AdminRequestServlet - " + uuid.toString() + " - Total - " + (after.getTime() - before.getTime())
				+ " ms");
	}

}
