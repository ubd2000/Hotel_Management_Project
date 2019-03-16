package kr.or.bit.hotel;

public class Test {
	public static void main(String[] args) {
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
//		String sc3 = new Scanner(System.in).nextLine();
//		System.out.println(sc3.matches(
////				"^(19[0-9]|200)[0-9](((0(1|3|5|7|8)|1(0|2))(0[1-9]|[1-2][0-9]|3[0-1]))|((0(4|6|9)|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|(1|2)[0-9]$)))"));
//		LocalDate d1 = LocalDate.of(2019, 3, 16);
//		LocalDate d2 = LocalDate.of(2019, 3, 14);
//		Period diff = Period.between(d2, d1);
//		System.out.println(diff.getDays());
//		HotelBooking hb = new HotelBooking();
////		hb.setRoom(new Reservation());
//		LocalDate t = LocalDate.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
//		System.out.println(t.plusDays(3));
//		Period p = Period.between(d1, d2);
//		System.out.println(p.getDays());
//		hb.getRoomInfo();
		System.out.println("".length());
		HotelManager hm = new HotelManager();
		hm.run();
		HotelBooking hb = new HotelBooking();
		hb.run();

	}
			}
