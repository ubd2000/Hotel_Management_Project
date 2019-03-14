package kr.or.bit.hotel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;;

public class HotelDate implements Serializable {
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
