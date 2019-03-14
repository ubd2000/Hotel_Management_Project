package kr.or.bit.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class HotelBooking {
	private boolean loginCheck; // 로그인 유무
	private Member memberLoggedin; // 로그인된 회원
	private Hotel hotel;
	private Scanner sc;
	String id = null;
	public HotelBooking() {
		memberLoggedin = new Member();
		sc = new Scanner(System.in);
		hotel = new SmallHotel();
	}
	public void getRoomInfo() { // 객실정보보기

	}

	public void reserveRoom() { // 객실예약

	}

	public void setNumberPeople() { // 인원설정

	}

	public void setRoom() { // 객실 선택

	}

	public void setDate() { // 날짜 선택

	}

	public void setService() { // 부가서비스 선택

	}

	public void getReservation() { // 예약 확인

	}

	public void cancelReservation() { // 예약 취소

	}

	public void changeReservation() { // 예약 변경

	}

	public void signUp() { // 회원 가입
		String password = null;
		String phoneNumber = null;
		String birthday = null;

		System.out.println("회원가입 절차");
		System.out.println("이름을 입력해 주세요.");
		String name = sc.nextLine();
		while (true) {
			System.out.println("아이디를 입력해주세요. (4자 이상  10자 이내)");
			this.id = sc.nextLine();
			if (id.length() > 10 || id.length() < 4) {
				JOptionPane.showMessageDialog(null, "ID의 길이를 확인해주세요.");
				System.out.println("ID의 길이가 올바르지 않습니다.");
			} else if (!id.matches("\\p{Alnum}+")) {
				JOptionPane.showMessageDialog(null, "ID형식이 올바르지 않습니다.");
				System.out.println("영어,숫자만 입력해주세요.");
			} else if (hotel.getMembers().containsKey(id)) {
				System.out.println("동일한 ID가 존재합니다.");
				System.out.println("다른 아이디를 입력해주세요.");
			} else {
				break;
			}
		}
		while (true) {
			System.out.println("비밀번호를 입력해주세요. (6자이상 10자 이내)");
			password = sc.nextLine();
			if (password.length() > 10 || password.length() < 6) {
				JOptionPane.showMessageDialog(null, "비밀번호의 길이를 확인해주세요.");
				System.out.println("PWD의 길이가 올바르지 않습니다.");
			} else if (!password.matches("\\p{Alnum}+")) {
				JOptionPane.showMessageDialog(null, "비밀번호 형식이 올바르지 않습니다.");
				System.out.println("영어,숫자만 입력해주세요.");
			} else {
				break;
			}
		}
		while (true) {
			System.out.println("핸드폰번호를 입력해주세요. ( - 생략)");
			System.out.println("Ex) 01012345678");
			phoneNumber = sc.nextLine();
			if (phoneNumber.length() > 12 || phoneNumber.length() < 11) {
				System.out.println("전화번호의 형식이 잘못됐습니다 ( 길이 )");
			} else if (!phoneNumber.matches("^010[0-9]{8}")) {
				System.out.println("잘못 입력하였습니다.");
			} else {
				break;
			}
		}
		while (true) {
			System.out.println("생년월일 8자리를 입력해주세요.");
			System.out.println("Ex)96년생 7월 15일생이면 >> 19960715");
			birthday = sc.nextLine();
			if (birthday.length() > 9 || birthday.length() <= 7) {
				System.out.println("생년월일의 형식이 잘못 됐습니다 ( 길이 )");
			} else if (!birthday.matches(
					"^(19[0-9]|200)[0-9](((0(1|3|5|7|8)|1(0|2))(0[1-9]|[1-2][0-9]|3[0-1]))|((0(4|6|9)|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|(1|2)[0-9]$)))")) {
				System.out.println("잘못된 형식입니다. 다시 입력해주세요.");
			} else {
				break;
			}
		}
		System.out.println("성공적으로 회원가입을 하셨습니다.!!");
		System.out.println(name + "님 환영합니다^^~");
		memberLoggedin = new Member(name, password, phoneNumber, birthday);
		hotel.getMembers().put(id, memberLoggedin);

	}

	public void login() { // 로그인
		System.out.println("로그인");
		while (true) {
			System.out.println("ID입력");
			String id = sc.nextLine();
			System.out.println("비밀번호 입력");
			String pwd = sc.nextLine();
			if (!hotel.getMembers().containsKey(id)) {
				System.out.println("ID를 확인해주세요.");
			} else {
				if (!hotel.getMembers().get(id).getPassword().equals(pwd)) {
					System.out.println("비밀번호를 확인해주세요.");
				} else {
					break;
				}
			}
		}
		System.out.println("로그인 성공!");

	}

	public void changelInfo() { // 회원 정보 수정
		String password = null;
		String password2 = null;
		String phone = null;
		exit: while (true) {
			System.out.println("회원 정보 수정입니다.");
			System.out.println("1. 비밀번호 재설정");
			System.out.println("2. 전화번호 재설정");
			System.out.println("3. 이전 화면 돌아가기");
			String number = sc.nextLine();

			switch (number) {
			case "1":
				System.out.println("비밀번호 재설정입니다.");
				System.out.println("바꾸실 비밀번호를 입력해주세요. (6자이상 10자 이내)");
				password = sc.nextLine();
				System.out.println("다시 한번 입력해주세요.");
				password2 = sc.nextLine();
				if (password.equals(password2)) {
					memberLoggedin.setPassword(password2);
					System.out.println("비밀번호가 바뀌었습니다.");
				} else if (password.length() > 10 || password.length() < 6) {
					JOptionPane.showMessageDialog(null, "비밀번호의 길이를 확인해주세요.");
					System.out.println("PWD의 길이가 올바르지 않습니다.");
				} else if (!password.matches("\\p{Alnum}+")) {
					JOptionPane.showMessageDialog(null, "비밀번호 형식이 올바르지 않습니다.");
					System.out.println("영어,숫자만 입력해주세요.");
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
					System.out.println("다시 시도 해 주세요.");
					break;
				}

				changelInfo();

			case "2":
				System.out.println("전화번호 재 설정입니다.");
				System.out.println("바꾸실 전화번호를 입력해주세요.");
				phone = sc.nextLine();
				if (phone.matches("^010[0-9]{8}")) {
					memberLoggedin.setPhoneNumber(phone);
					System.out.println("바꾸신 핸드폰 번호입니다.");
					System.out.println("바뀐 전화번호 : " + phone);
				} else {
					System.out.println("잘못된 핸드폰번호입니다.");
					System.out.println("Ex)01012341234 ( - 제외 )");
					break;
				}
				changelInfo();
			case "3":
				break exit;
			default:
				System.out.println("잘못된 메뉴 번호입니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	public void quit() { // 회원 탈퇴
		System.out.println("회원 탈퇴를 하시겠습니까?");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		String select = sc.nextLine();
		switch (select) {
		case "1" : System.out.println("1. 회원 탈퇴 하였습니다.");
                      hotel.getMembers().remove(this.id, memberLoggedin);
			break;
		case "2" : System.out.println("취소 하였습니다.");
                     quit();
		break;
		default: System.out.println("잘못된 메뉴 번호입니다.");
                     quit();
			break;
		}

	}

	public static void main(String[] args) {
		HotelBooking b = new HotelBooking();
			b.signUp();
	}

}
