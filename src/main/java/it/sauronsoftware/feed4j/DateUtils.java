package it.sauronsoftware.feed4j;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.ISODateTimeFormat;

public class DateUtils {

	/**
	 * Date format pattern used to parse HTTP date headers in RFC 1123 format.
	 */
	public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

	/**
	 * Date format pattern used to parse HTTP date headers in RFC 1036 format.
	 */
	public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";

	/**
	 * Date format pattern used to parse HTTP date headers in ANSI C
	 * <code>asctime()</code> format.
	 */
	public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";

	public static DateTime parse(String date) {
		DateTimeFormatter forPattern = DateTimeFormat
				.forPattern("E, dd MMM YYYY HH:mm:ss Z");

		return new DateTimeFormatterBuilder()
				.append(null,
						new DateTimeParser[] {
								DateTimeFormat.forPattern(PATTERN_RFC1123)
										.getParser(),
								DateTimeFormat.forPattern(PATTERN_RFC1036)
										.getParser(),
								DateTimeFormat.forPattern(PATTERN_ASCTIME)
										.getParser(),
								DateTimeFormat.forPattern(
										"dd MMM yyyy HH:mm:ss zzz").getParser(),
								DateTimeFormat.forPattern(
										"MMM dd, yyyy HH:mm:ss a zzz")
										.getParser(),
								DateTimeFormat.forPattern(
										"dd MMM yyyy HH:mm:ss Z").getParser(),
								DateTimeFormat
										.forPattern("yyyy-M-d HH:mm:ss Z")
										.getParser(),
								forPattern.getParser(),
								DateTimeFormat
										.forPattern("yyyy-M-d'T'HH:mm:ssZ")
										.getParser() }).toFormatter()
				.parseDateTime(date);
	}
}
