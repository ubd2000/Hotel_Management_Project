package kr.or.bit.hotel;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomString {
	public static final String PATH_DIRECTORY = "C:\\Temp\\Hotel\\";
	public static final String PATH_HOTEL = "C:\\Temp\\Hotel\\hotel.info";
	public static final String breakfast = "조식";
	public static final String therapy = "전신테라피";
	
	public static final String PATH_RECORD_DIRECTORY(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return "C:\\Temp\\Hotel\\" + date.format(formatter);
	}
	
	
	public static final String PATH_RECORD(LocalDate date, String check) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String fileName = "";
		if (check == "in") {
			fileName = "CheckIn";
		} else {
			fileName = "CheckOut";
		}
		
		return "C:\\Temp\\Hotel\\" + date.format(formatter) + "\\" + fileName + ".info";
	}
	
	public static String putComma(long number) {
		String newNumber;
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		newNumber = decimalFormat.format(number);
		return newNumber;
	}
}
