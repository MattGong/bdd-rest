/**
 * Copyright 2015 Grant Forrester
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
