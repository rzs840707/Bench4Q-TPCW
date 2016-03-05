package org.bench4q.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import easy.testing.sut.service.ImageService;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 5304713108552340874L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ImageService imageService = webApplicationContext.getBean(ImageService.class);

		UUID uuid = UUID.fromString(req.getParameter("uuid"));
		String className = req.getParameter("className");
		String imageName = req.getParameter("imageName");

		if (imageName.endsWith(".jpg")) {
			res.setContentType("image/jpeg");
		} else if (imageName.endsWith(".gif")) {
			res.setContentType("image/gif");
		}

		byte[] data = imageService.getImage(uuid, className, imageName);

		if (data == null) {
			res.setContentType("image/jpeg");
			data = imageService.getImage(uuid, className, "item.jpg");
		}

		OutputStream os = res.getOutputStream();
		os.write(data);
		os.flush();
		os.close();
	}
}
