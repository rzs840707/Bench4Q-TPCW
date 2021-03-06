/**
 * 
 * Create at:   2009-5-14
 * @version:    1.0 
 *
 * 
 * Copyright Technology Center for Software Engineering,Institute of Software, Chinese Academy of Sciences 
 * and distributed according to the GNU Lesser General Public Licence. 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by   
 * the Free Software Foundation; either version 2.1 of the License, or any
 * later version.
 * See Copyright.txt for full copyright information.
 *
 *
 *  http://otc.iscas.ac.cn/
 * Technology Center for Software Engineering
 * Institute of Software, Chinese Academy of Sciences
 * Beijing 100190, China
 *
 * This version is a based on the implementation from University of Wisconsin
 *
 *
 *  * Initial developer(s): Zhiquan Duan, Wei Wang.
 * 
 */
package org.bench4Q.servlet;

import java.util.Random;

public class Util {

	// public final String SESSION_ID="JIGSAW_SESSION_ID";
	// public static final String SESSION_ID="JServSessionIdroot";
	public static final String SESSION_ID = "$sessionid$";

	// This must be equal to the number of items in the ITEM table
	public static final int NUM_ITEMS = 1000;

	public static int getRandomI_ID() {
		Random rand = new Random();
		Double temp = new Double(Math.floor(rand.nextFloat() * NUM_ITEMS));
		return temp.intValue();
	}

	public static int getRandom(int i) { // Returns integer 1, 2, 3 ... i
		return ((int) (java.lang.Math.random() * i) + 1);
	}

	// Not very random function. If called in swift sucession, it will
	// return the same string because the system time used to seed the
	// random number generator won't change.
	public static String getRandomString(int min, int max) {
		String newstring = new String();
		Random rand = new Random();
		int i;
		final char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#', '$', '%', '^', '&', '*', '(',
				')', '_', '-', '=', '+', '{', '}', '[', ']', '|', ':', ';', ',', '.', '?', '/',
				'~', ' ' }; // 79
		// characters
		int strlen = (int) Math.floor(rand.nextDouble() * (max - min + 1));
		strlen += min;
		for (i = 0; i < strlen; i++) {
			char c = chars[(int) Math.floor(rand.nextDouble() * 79)];
			newstring = newstring.concat(String.valueOf(c));
		}
		return newstring;
	}

	// Defined in TPC-W Spec Clause 4.6.2.8
	private static final String[] digS = { "BA", "OG", "AL", "RI", "RE", "SE", "AT", "UL", "IN",
			"NG" };

	public static String DigSyl(int d, int n) {
		String s = "";

		if (n == 0)
			return (DigSyl(d));
		for (; n > 0; n--) {
			int c = d % 10;
			s = digS[c] + s;
			d = d / 10;
		}

		return (s);
	}

	public static String DigSyl(int d) {
		String s = "";

		for (; d != 0; d = d / 10) {
			int c = d % 10;
			s = digS[c] + s;
		}

		return (s);
	}
}
