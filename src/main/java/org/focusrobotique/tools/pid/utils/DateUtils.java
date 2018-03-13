package org.focusrobotique.tools.pid.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Date Utils for PID
 */
public class DateUtils {

	/**
	 * Create a string representation of the date which could be used to generate some Files with timestamp.
	 * @param localDateTime
	 * @return
	 */
	public static String dateToString(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss");

        String result = localDateTime.format(formatter);

        return result;
	}
}
