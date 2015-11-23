package org.bench4q.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageUtil {
	private static final int MAX = 1000;
	private static final String PREFIX = "/root/apache-tomcat-8.0.28/webapps/bench4q-mysql/Images/";

	private static int convertNumber(String imageFileName) {
		return (Integer.valueOf(imageFileName.replace("thumb_", "").replace("item_", "").replace(".jpg", ""))
				% (ImageUtil.MAX)) + 1;
	}

	public static String convertFileName(String imageFileName) {
		int number = ImageUtil.convertNumber(imageFileName);
		if (imageFileName.startsWith("item_")) {
			return "item_" + number + ".jpg";
		} else if (imageFileName.startsWith("thumb_")) {
			return "thumb_" + number + ".jpg";
		}
		return null;
	}

	public static void readAndWriteImage(String imageFileName) {
		try {
			String fileName = ImageUtil.convertFileName(imageFileName);
			File file = new File(ImageUtil.PREFIX + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			int size = inputStream.available();
			byte[] content = new byte[size];
			inputStream.read(content, 0, size);
			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(content, 0, size);
			outputStream.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
}
