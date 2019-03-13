package kr.or.bit.hotel;

import java.text.SimpleDateFormat;

public class Test {
	public static void main(String[] args) {
		HotelDate h = new CheckInDate(2019, 12, 31);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(f.format(h.getCheckTime().getTime()));
	}
}
