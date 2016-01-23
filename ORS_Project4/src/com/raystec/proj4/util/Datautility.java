package com.raystec.proj4.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class Datautility {

	// Data Utility class to format data from one format to another

	public static final String APP_DATE_FORMAT = "MM/dd/yyyy";
	public static final String APP_TIME_FORMAT = "MM/dd/yyy HH:mm:ss";

	// Date formatter

	private static final SimpleDateFormat formatter = new SimpleDateFormat(
			APP_DATE_FORMAT);
	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(
			APP_TIME_FORMAT);

	// Trims and trailing and leading spaces of a String

	public static String getString(String val) {

		if (DataValidator.isNotNull(val)) {
			return val.trim();

		} else {
			return val;
		}
	}

	// Converts and Object to String

	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	// Converts String into Integer

	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);

		} else {
			return 0;
		}
	}

	// Converts String into Long

	 public static long getLong(String val) {
	        if (DataValidator.isLong(val)) {
	        //	System.out.println("demogetlong"+Long.parseLong(val));
	            return Long.parseLong(val);
	        } else {
	            return 0;
	        }
	    }

	// Converts String into Date

	public static Date getDate(String val) throws ParseException {
		Date date = null;
		date = formatter.parse(val);
		return date;

	}

	// Converts Date into String

	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {

		}
		return "";
	}

	public static Date getDate(Date date, int day) {
		return null;
	}

	public static Timestamp getTimestamp(String val) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static void main(String[] args) {
		System.out.println(getInt("124"));
	}

}
