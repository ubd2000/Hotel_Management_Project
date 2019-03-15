package kr.or.bit.hotel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;;

public class HotelDate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LocalDate checkDate;
	private String DateString;
	
	public HotelDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		checkDate = LocalDate.parse(date, formatter);
		DateString = checkDate.format(formatter);
	}

	public LocalDate getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(LocalDate checkDate) {
		this.checkDate = checkDate;
	}
	
	public String getDateString() {
		return DateString;
	}

	public void setDateString(String dateString) {
		DateString = dateString;
	}
}
