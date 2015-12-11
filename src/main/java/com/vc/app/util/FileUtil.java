package com.vc.app.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FileUtil {
	/**
	 * 日志对象
	 */
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 读取Properties文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @return Properties对象或者null
	 */
	public static Properties loadProperties(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			logger.warn("File path is null or empty");
			return null;
		}

		FileReader reader = null;
		Properties prop = null;
		try {
			reader = new FileReader(filePath);
			prop = new Properties();
			prop.load(reader);
		} catch (FileNotFoundException e1) {
			logger.warn(e1.getMessage());
		} catch (IOException e2) {
			logger.error(e2.getMessage());
		} catch (Exception e3) {
			logger.error(e3.getMessage());
		} finally {
			close(reader);
		}

		return prop;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件内容或者null
	 */
	public static String loadContent(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			logger.warn("File path is null or empty");
			return null;
		}

		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String separator = System.getProperty("line.separator");
			String line = null;

			while ((line = reader.readLine()) != null) {
				builder.append(line + separator);
			}
		} catch (FileNotFoundException e1) {
			logger.warn(e1.getMessage());
		} catch (IOException e2) {
			logger.error(e2.getMessage());
		} catch (Exception e3) {
			logger.error(e3.getMessage());
		} finally {
			close(reader);
		}
		return builder.toString();
	}

	/**
	 * 保存Properties文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param prop
	 *            Properties对象
	 * @return 保存成功还是失败
	 */
	public static boolean saveProperties(String filePath, Properties prop) {
		if (filePath == null || filePath.isEmpty()) {
			logger.warn("File path is null or empty");
			return false;
		}

		if (prop == null) {
			logger.warn("The param is null[prop]");
			return false;
		}
		FileWriter writer = null;
		boolean success = true;
		try {
			writer = new FileWriter(filePath);
			prop.store(writer, null);
		} catch (FileNotFoundException e1) {
			logger.warn(e1.getMessage());
			success = false;
		} catch (IOException e2) {
			logger.error(e2.getMessage());
			success = false;
		} catch (Exception e3) {
			logger.error(e3.getMessage());
			success = false;
		} finally {
			close(writer);
		}

		return success;
	}

	/**
	 * 保存文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            文件内容
	 * @return 保存成功还是失败
	 */
	public static boolean saveContent(String filePath, String content) {
		if (filePath == null || filePath.isEmpty()) {
			logger.warn("File path is null or empty");
			return false;
		}

		if (content == null) {
			logger.warn("The param is null[content]");
			return false;
		}

		BufferedWriter writer = null;
		boolean success = true;
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(content);
		} catch (FileNotFoundException e1) {
			logger.warn(e1.getMessage());
			success = false;
		} catch (IOException e2) {
			logger.error(e2.getMessage());
			success = false;
		} catch (Exception e3) {
			success = false;
			logger.error(e3.getMessage());
		} finally {
			close(writer);
		}
		return success;
	}

	/**
	 * 将输入流中的内容保存到指定文件中
	 * 
	 * @param is
	 *            输入流
	 * @param dstFilePath
	 *            目的文件路径
	 */
	public static boolean saveStream(InputStream is, String dstFilePath) {
		File file = new File(dstFilePath);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		final int bufLen = 1024;
		byte[] buf = new byte[bufLen];
		BufferedInputStream fileIn = new BufferedInputStream(is);
		BufferedOutputStream fileOut = null;
		boolean success = true;

		try {
			fileOut = new BufferedOutputStream(new FileOutputStream(file));
			int bytesIn = -1;
			while ((bytesIn = fileIn.read(buf, 0, bufLen)) != -1) {
				fileOut.write(buf, 0, bytesIn);
			}

			fileOut.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
			success = false;
		} finally {
			close(fileOut);
		}

		return success;
	}

	/**
	 * 关闭文件流
	 * 
	 * @param file
	 *            可关闭的文件对象
	 */
	public static void close(Closeable file) {
		if (file != null) {
			try {
				file.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	public static String getFileExt(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		if (pos != -1 && pos != 0) {
			return fileName.substring(pos + 1);
		} else {
			return "";
		}
	}

	/*
	 * Java文件操作 获取不带扩展名的文件名
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
}
