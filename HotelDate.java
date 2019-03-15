package kr.or.bit.hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;;

public class HotelDate {
	private LocalDate checkDate;
	
	public HotelDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		checkDate = LocalDate.parse(date, formatter);
	}

	public LocalDate getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(LocalDate checkDate) {
		this.checkDate = checkDate;
	}
}
