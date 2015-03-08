package com.github.grantjforrester.bdd.rest.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {

	public static byte[] getFileContent(File file) {
		byte[] bytes = new byte[(int) file.length()];
		DataInputStream stream = null;
		try {
			stream = new DataInputStream(new FileInputStream(file));
			stream.readFully(bytes);
			return bytes;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					// we tried :(
				}
		}
	}

}
