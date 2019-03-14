package kr.or.bit.hotel;

import java.util.Calendar;

public abstract class HotelDate {
	private Calendar date;
	private Calendar checkTime;
	
	public HotelDate(int year, int month, int day, int hourOfDay, int minute) {
		date = Calendar.getInstance();
		checkTime = Calendar.getInstance();
		date.set(year, month - 1, day);
		checkTime.set(year, month - 1, day, hourOfDay, minute);	
	}
	
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Calendar checkTime) {
		this.checkTime = checkTime;
	}
}
