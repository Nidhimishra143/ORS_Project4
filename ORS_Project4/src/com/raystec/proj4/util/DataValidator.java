package com.raystec.proj4.util;

import java.text.ParseException;
import java.util.Date;

public class DataValidator {

	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDOB(String val) throws Exception {

		Date c = Datautility.getDate(val);
		Date d = new Date();

		int i = d.compareTo(c);
		if (i > 0) {
			try {
				return true;
			} catch (NumberFormatException e) {
				return false;

			}
		} else {
			return false;
		}

	}

	public static boolean name(String val) {
		String name = "^[a-zA-Z]*$";
		if (isNotNull(val)) {
			try {
				return val.matches(name);
			} catch (NumberFormatException e) {

				return false;
			}
		} else {
			return false;
		}

	}

	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isEmail(String val) {

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isPhone(String val) {

		String regex = "\\d{10}";

		if (isNotNull(val)) {
			try {
				return val.matches(regex);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isString(String val) {
		String name = ("[a-zA-z]+([ '-][a-zA-Z]+)*");

		if (isNotNull(val)) {
			try {
				return val.matches(name);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isDate(String val) throws ParseException {

		Date d = null;
		if (isNotNull(val)) {
			d = Datautility.getDate(val);
		}
		return d != null;
	}

	public static void main(String[] args) throws ParseException {

		System.out.println("Not Null 2" + isNotNull("ABC"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));
		System.out.println("Is String" + isPhone("1323"));
		System.out.println(isDate("12/35/2014"));
		System.out.println(isDate("ewrwrwer"));
		System.out.println(isDate(""));
		System.out.println(isDate(null));
		System.out.println(Datautility.getDate("24/29/2015"));
	}

}
