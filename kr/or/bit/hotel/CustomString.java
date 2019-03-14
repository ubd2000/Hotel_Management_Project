package kr.or.bit.hotel;

import java.text.DecimalFormat;

public class CustomString {
	public static final String PATH_DIRECTORY = "C:\\Temp\\Hotel\\";
	public static final String PATH_HOTEL = "C:\\Temp\\Hotel\\hotel.info";
	
	public static String putComma(long number) {
		String newNumber;
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		newNumber = decimalFormat.format(number);
		return newNumber;
	}
}
