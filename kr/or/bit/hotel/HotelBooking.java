package kr.or.bit.hotel;

import java.util.List;
import java.util.Scanner;

public class HotelBooking {
	private boolean loginCheck; // 로그인 유무
	private List<Member> memberLoggedin; // 로그인된 회원
	private Hotel hotel = new SmallHotel(); // 예약하는 호텔
	private Scanner sc = new Scanner(System.in);

	private void getRoomInfo() { // 객실정보보기
		for (int i = 0; i < hotel.getRoomInfos().length; i++) {
			String kitchen = "";
			if (hotel.getRoomInfos()[i].isKitchen()) {
				kitchen = "유";
			} else {
				kitchen = "무";
			}
			System.out.println("  [방이름] : " + hotel.getRoomInfos()[i].getRoomName() + "룸  " + "  [가격] : "
					+ hotel.getRoomPrices()[i] + "원  " + "  [기본인원] : "
					+ hotel.getRoomInfos()[i].getDefaultNumberPeople() + "명  " + "  [최대인원] : "
					+ hotel.getRoomInfos()[i].getMaxNumberPeople() + "명  " + "  [화장실] : "
					+ hotel.getRoomInfos()[i].getNumberBathroom() + "개  " + "  [침대] : "
					+ hotel.getRoomInfos()[i].getNumberBed() + "개  " + "  [주방] : " + kitchen);
		}
	}

	public void reserveRoom() { // 객실예약
		Reservation r = new Reservation();
		setDate(r);
		setRoom(r);
		setNumberPeople(r);
		setService(r);
	}

	private void setDate(Reservation r) { // 날짜 선택
		System.out.println("체크인 날짜를 입력해주세요.");
		String checkIn = sc.nextLine();
		System.out.println("체크아웃 날짜를 입력해주세요.");
		String checkOut = sc.nextLine();
		HotelDate checkInDate = new CheckInDate(2019, 12, 21); // String에서 parse
		HotelDate checkOutDate = new CheckOutDate(2019, 12, 23);
		r.setDateCheckIn(checkInDate);
		r.setDateCheckOut(checkOutDate);
	}

	private void setRoom(Reservation r) { // 객실 선택
		for (List<Room> floor : hotel.getRooms()) {
			for (Room room : floor) {
				
			}
		}
	}

	private void setNumberPeople(Reservation r) { // 인원설정

	}

	private void setService(Reservation r) { // 부가서비스 선택

	}

	public void getReservation() { // 예약 확인

	}

	public void cancelReservation() { // 예약 취소

	}

	public void changeReservation() { // 예약 변경

	}

	public void signUp() { // 회원 가입

	}

	public void login() { // 로그인

	}

	public void changelInfo() { // 회원 정보 수정

	}

	public void quit() { // 회원 탈퇴

	}

	public void MenuPrint() { // 메뉴 출력

	}

	public static void main(String[] args) {
		HotelBooking ho = new HotelBooking();

		ho.reserveRoom();
	}

}