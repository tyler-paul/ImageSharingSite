package tylerpaul.site.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.imgscalr.Scalr;

public class FileSystem {
	public enum Type {
		CATEGORY, IMAGE
	}

	public static void addFiles(HttpServletRequest request, Part filePart, Type type, int id) {
		InputStream fileContent;
		try {
			// save new image
			fileContent = filePart.getInputStream();
			BufferedImage bf = ImageIO.read(fileContent);
			File outputfile = new File(
					request.getServletContext().getRealPath(File.separator) + "/" + getType(type) + "/" + id + ".png");
			ImageIO.write(bf, "png", outputfile);

			// save thumbnail image
			BufferedImage thumb = Scalr.resize(bf, 150);
			outputfile = new File(
					request.getServletContext().getRealPath(File.separator) + "/" + getType(type) + "/thumbs/" + id + ".png");
			ImageIO.write(thumb, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFiles(HttpServletRequest request, Type type, int id) {
		File file = new File(request.getServletContext().getRealPath(File.separator) + "/" + getType(type) + "/" + id + ".png");
		File thumbFile = new File(request.getServletContext().getRealPath(File.separator) + "/" + getType(type) + "/thumbs/" + id + ".png");
		file.delete();
		thumbFile.delete();
	}
	
	private static String getType(Type type) {
		String fstype = "";
		if (type == FileSystem.Type.CATEGORY) {
			fstype = "categories";
		} else if (type == FileSystem.Type.IMAGE) {
			fstype = "images";
		}
		return fstype;
	}
}
