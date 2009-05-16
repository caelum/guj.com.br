package br.com.guj.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtil {

	@SuppressWarnings("unchecked")
	public static void extractZipAndCopyFilesToDisk(File zip, String filesPath,
			String articlePath) {

		Enumeration entries = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(zip);
			entries = zipFile.entries();

			File directory = new File(filesPath + articlePath);

			File[] listFiles = directory.listFiles();

			if (listFiles != null) {

				for (File file : listFiles) {
					
					file.delete();
					
				}
				
			}

			while (entries.hasMoreElements()) {

				ZipEntry entry = (ZipEntry) entries.nextElement();

				directory.mkdirs();

				File file = new File(directory, entry.getName());
				file.createNewFile();

				copyFileToDisk(zipFile.getInputStream(entry),
						new BufferedOutputStream(new FileOutputStream(file)));
			}

			zipFile.close();

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}

	}

	public static void prepareAndCopyCodes(File codes, String filesPath,
			String articlePath) {

		try {
			
			InputStream input = new BufferedInputStream(new FileInputStream(
					codes));

			File directory = new File(filesPath + articlePath);
			directory.mkdirs();

			File file = new File(directory, "codigos.zip");
			file.createNewFile();

			OutputStream output = new BufferedOutputStream(
					new FileOutputStream(file));

			copyFileToDisk(input, output);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void copyFileToDisk(InputStream in, OutputStream out)
			throws IOException {

		byte[] buffer = new byte[1024];
		int lenght;

		while ((lenght = in.read(buffer)) >= 0) {

			out.write(buffer, 0, lenght);

		}

		in.close();
		out.close();
	}
}
