package com.github.grantjforrester.bdd.rest.matchers;

import java.util.Arrays;

public class ByteArrayEqualityMatcher {

	public static boolean matches(byte[] expected, byte[] actual) {
		return Arrays.equals(expected, actual);
	}
}
