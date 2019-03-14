package kr.or.bit.hotel;

public class CheckInDate extends HotelDate {
	public CheckInDate(int year, int month, int day) {
		super(year, month, day, Number.CHECKIN_HOUR, 0);
	}
}