package kr.or.bit.hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HotelBooking {
	private boolean loginCheck; // 로그인 유무
	private Member memberLoggedIn; // 로그인된 회원
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
		memberLoggedIn = new Member();
		if (memberLoggedIn.getReservation() != null) {
			System.out.println("이미 예약함");
			return;
		}
		memberLoggedIn = new Member();
		Reservation r = new Reservation();
		setDate(r);
//		setRoom(r);
//		setNumberPeople(r);
//		setService(r);
		memberLoggedIn.setReservation(r);
	}

	private void setDate(Reservation r) { // 날짜 선택
		LocalDate today = LocalDate.now();
		System.out.println(today);
		HotelDate checkInDate, checkOutDate;
		while (true) {
			System.out.println("체크인 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
			String checkIn = sc.nextLine();
			checkInDate = new HotelDate(checkIn);
			if (today.isAfter(checkInDate.getCheckDate())) {
				System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
			} else {
				break;
			}
		}

		while (true) {
			System.out.println("체크아웃 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
			String checkOut = sc.nextLine();
			checkOutDate = new HotelDate(checkOut);
			if (today.isBefore(checkOutDate.getCheckDate())
					|| checkInDate.getCheckDate().isAfter(checkOutDate.getCheckDate())
					|| checkInDate.getCheckDate().isEqual(checkOutDate.getCheckDate())) {
				System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
			} else {
				break;
			}
		}
		
		System.out.println("체크인 날짜 : " + checkInDate.getCheckDate());
		System.out.println("체크아웃 날짜 : " + checkOutDate.getCheckDate());

		r.setDateCheckIn(checkInDate);
		r.setDateCheckOut(checkOutDate);
	}

	private void setRoom(Reservation r) { // 객실 선택
//		for (List<Room> floor : hotel.getRooms()) {
//			for (Room room : floor) {
//				if (!(체크인 날짜가 끼어있는 경우 || 체크아웃 날짜가 끼어있는 경우 || 사이에 끼어있는 경우 || 더 범위가 넓은 경우)) {
//					방 번호와 정보를 보여준다
//				}
//			}
//		}

		System.out.println("예약할 방 번호를 입력해주세요");
		String roomNumber = sc.nextLine(); // 202호
		// roomNumber로 room을 가져옴
		Room room = null;
		room.getGuests().add(memberLoggedIn);
		r.setAmountPaid(r.getAmountPaid() + hotel.getRoomPrices()[0]);
		r.setRoom(room);
	}

	private void setNumberPeople(Reservation r) { // 인원설정
		System.out.println("숙박할 인원을 입력해주세요.");
		System.out.println("선택하신 방의 기본 인원은 {0}명, 최대 인원은 {1}명입니다.");
		System.out.println("인원 추가 시 X원이 추가됩니다.");
		String numberPeople = sc.nextLine();
		r.setNumberPeople(Integer.parseInt(numberPeople));
	}

	private void setService(Reservation r) { // 부가서비스 선택
		System.out.println("부가 서비스를 선택해주세요.");
		System.out.println("서비스에는 {0}, {1}이 있습니다.");
		System.out.println("{0}은 X원, {1}은 Y원입니다.");
		String service = sc.nextLine();
		r.setService(service);
	}

	public void getReservation() { // 예약 확인
		System.out.println(memberLoggedIn.getReservation());
	}

	public void cancelReservation() { // 예약 취소
		/*
		 * Member - reservation 삭제 예약됐던 룸 예약 정보 삭제
		 */
	}

	public void changeReservation() { // 예약 변경
		/*
		 * reserveRoom(); cancelReservation();
		 */

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