package kr.or.bit.hotel;

public class CheckOutDate extends HotelDate {
	public CheckOutDate(int year, int month, int day) {
		super(year, month, day, Number.CHECKOUT_HOUR, 0);
	}
}
