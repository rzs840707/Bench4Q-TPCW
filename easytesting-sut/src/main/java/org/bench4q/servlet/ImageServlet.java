package org.bench4q.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 5304713108552340874L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		UUID uuid = UUID.fromString(req.getParameter("uuid"));
		String className = req.getParameter("className");
		String imageName = req.getParameter("imageName");

		Logger LOGGER = LoggerFactory.getLogger(className);
		Date before = new Date(System.currentTimeMillis());

		if (imageName.endsWith(".jpg")) {
			res.setContentType("image/jpeg");
		} else if (imageName.endsWith(".gif")) {
			res.setContentType("image/gif");
		}

		InputStream inputStream = this.getClass().getResourceAsStream("/images/" + imageName);
		if (inputStream == null) {
			res.setContentType("image/jpeg");
			inputStream = this.getClass().getResourceAsStream("/images/item.jpg");
		}

		int size = inputStream.available();
		byte data[] = new byte[size];
		inputStream.read(data);
		inputStream.close();
		OutputStream os = res.getOutputStream();
		os.write(data);
		os.flush();
		os.close();

		Date after = new Date(System.currentTimeMillis());
		LOGGER.debug("ImageServlet - " + uuid.toString() + " - Disk - " + (after.getTime() - before.getTime()) + " ms");
	}
}
