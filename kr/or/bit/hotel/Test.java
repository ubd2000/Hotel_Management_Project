package kr.or.bit.hotel;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
//		HotelDate h = new CheckInDate(2019, 12, 31);
//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(f.format(h.getCheckTime().getTime()));
		String regex = "\\p{Alnum}+";
		String regex2 = "^010[0-9]{8}$";
		String str = "a11b1c1";
		String str2 = "abc1ㅁㄴ";
		String str3 = "abc!1";
		String str4 = "1abn245";
		System.out.println(str.matches(regex));
		System.out.println(str2.matches(regex));
		System.out.println(str3.matches(regex));
		System.out.println(str4.matches(regex));
//		String sc = new Scanner(System.in).nextLine();
//		System.out.println(sc.matches(regex) && sc.length() <= 10);
//		String sc2 = new Scanner(System.in).nextLine();
//		System.out.println(sc2.matches(regex2));
		HotelManager h = new HotelManager();
		h.managerMenuPrint();
		
	}
}
