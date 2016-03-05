package easy.testing.sut.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ImageService {
	public byte[] getImage(UUID requestId, String className, String imageName) {
		try {
			Logger LOGGER = LoggerFactory.getLogger(className);
			Date before = new Date(System.currentTimeMillis());
			InputStream inputStream = this.getClass().getResourceAsStream("/images/" + imageName);
			if (inputStream == null) {
				return null;
			}
			int size = inputStream.available();
			byte data[] = new byte[size];
			inputStream.read(data);
			inputStream.close();
			Date after = new Date(System.currentTimeMillis());
			LOGGER.debug("ImageService - " + requestId.toString() + " - Disk - " + (after.getTime() - before.getTime())
					+ " ms");
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
