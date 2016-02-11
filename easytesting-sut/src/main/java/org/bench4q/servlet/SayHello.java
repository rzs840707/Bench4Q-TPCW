package org.bench4q.servlet;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SayHello {

	public static void print_hello(HttpSession session, HttpServletRequest req, PrintWriter out) {

		// If we have seen this session id before
		if (!session.isNew()) {
			int C_ID[] = (int[]) session.getAttribute("C_ID");
			// check and see if we have a customer name yet
			if (C_ID != null) { // Say hello.
				out.println("Hello " + (String) session.getAttribute("C_FNAME") + " "
						+ (String) session.getAttribute("C_LNAME"));
			} else {
				out.println("Hello unknown user");
			}
		} else {
			// This is a brand new session
			out.println("This is a new session!");
			// Check to see if a C_ID was given. If so, get the customer name
			// from the database and say hello.
			String C_IDstr = req.getParameter("C_ID");
			if (C_IDstr != null) {
				String name[];
				int C_ID[] = new int[1];
				C_ID[0] = Integer.parseInt(C_IDstr, 10);
				out.flush();
				// Use C_ID to get the user name from the database.
				name = Database.getName(C_ID[0]);
				// Set the values for this session.
				if (name == null) {
					out.println("Hello unknown user!");
					return;
				}
				session.setAttribute("C_ID", C_ID);
				session.setAttribute("C_FNAME", name[0]);
				session.setAttribute("C_LNAME", name[1]);
				out.println("Hello " + name[0] + " " + name[1] + ".");

			} else {
				out.println("Hello unknown user!");
			}
		}
	}
}
