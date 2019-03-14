package kr.or.bit.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.sun.org.apache.xml.internal.dtm.ref.CustomStringPool;

public class HotelManager {

	public static void main(String[] args) {

		HotelManager hotelManager = new HotelManager();

		// 호텔 사이즈 설정 테스트
//		hotelManager.setHotel();
//		System.out.println(hotelManager.myHotel.toString());

		// Room
		Room room = new Room(null, 0, 0, 0, 0, false);

		// 객실관리 테스트
//		hotelManager.roomManage();
		// 기본가격 테스트
//		hotelManager.setPrice();
		// 정보보기 테스트
		hotelManager.getInfo();

	}

	private Hotel myHotel;
	private Scanner sc;
	private File file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	// 생성자!
	public HotelManager() {
		this.sc = new Scanner(System.in);
	}

	// 지훈, 세림
	// 호텔 사이즈 설정
	private Hotel setHotel() {
		while (true) {
			System.out.println("호텔사이즈 입력[소형, 중형, 대형]: ");
			String hotelSize = sc.nextLine();

			switch (hotelSize) {
			case "소형":
				System.out.println("소형 호텔이 생성되었다.");
				return this.myHotel = new SmallHotel();
			case "중형":
				System.out.println("중형 호텔이 생성되었다.");
				return this.myHotel = new MediumHotel();
			case "대형":
				System.out.println("대형 호텔이 생성되었다.");
				return this.myHotel = new LargeHotel();
			default:
				System.out.println("소형, 중형, 대형 중에 선택하세요.");
				break;
			}
		}
	}

	private void loadHotel() {
		file = new File(CustomString.PATH_HOTEL);
		if (!file.exists()) {
			System.out.println("호텔 정보가 존재하지 않습니다.");
			setHotel();
			return;
		}
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			myHotel = (Hotel) in.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 호텔 정보 저장하기
	 * 
	 * 변경된 호텔 정보를 저장
	 * 
	 * 폴더가 없으면 자동으로 폴더 생성 후 저장
	 * 
	 * 작성자 : 윤종석
	 */
	private void saveHotel() {
		file = new File(CustomString.PATH_DIRECTORY);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(CustomString.PATH_HOTEL);
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(myHotel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 세림
	// 객실관리 : 투숙객, 부가서비스, 체크인아웃을 관리하는 메뉴를 보여준다.
	private void roomManage() {
		loadHotel();
		String menu = "";

		while (true) {
			System.out.println("객실관리: 원하는 번호를 입력하세요.");
			System.out.println("1. 투숙객 정보 확인");
			System.out.println("2. 부가서비스 변경");
			System.out.println("3. 체크인, 체크아웃 관리");

			menu = sc.nextLine();

			switch (menu) {
			case "1":
				getGuest();
				return;
			case "2":
				setService();
				saveHotel();
				return;
			case "3":
				setCheckInOut();
				saveHotel();
				return;
			default:
				System.out.println("객실관리: 1,2,3 중에 선택해주세요");
				break;
			}
		}
	}

	// 세림
	// 투숙객 정보
	private void getGuest() {
		// System.out.println("투숙객 정보보기 메서드");

		// 1. 원하는 객실을 입력받는다.(scanner)
		System.out.println("원하는 객실을 입력하세요[ex: 201~206, 301~306, 401~403, 501~502]: ");
		String wantRoom = sc.nextLine();

		// 2. [원하는 객실]에 머무는 [투숙객]을 찾는다.
		String frontRoom = wantRoom.substring(0, 1);
		// System.out.println(frontRoom);
		String endRoom = wantRoom.substring(wantRoom.length() - 1, wantRoom.length());
		// System.out.println(endRoom);

		Member roomInfo = (Member) myHotel.getRooms().get(Integer.parseInt(frontRoom) - 2)
				.get(Integer.parseInt(endRoom) - 1).getGuests();

		// 3. 그 투숙객의 정보를 출력한다. (이름, 인원수, 부가서비스, 총 요금, 체크인, 체크아웃 날짜)
		System.out.println(wantRoom + "의 투숙객 정보입니다.");
		System.out.println("이름: " + roomInfo.getName() + "인원 수: " + roomInfo.getReservation()
//		+ "부가서비스: " + roomInfo.
				+ "총 요금: " + roomInfo.getReservation().getAmountPaid() + "체크인: "
				+ roomInfo.getReservation().getDateCheckIn() + "체크아웃: " + roomInfo.getReservation().getDateCheckOut());
	}

	// 세림
	// 부가서비스 변경
	private void setService() {
		System.out.println("부가서비스 변경  메서드");

		// 1. 원하는 객실을 입력받는다.
		// 2. [원하는 객실]에 머무는 [투숙객]을 찾는다.
		// 3. 그 투숙객의 객실 정보를 출력한다.(이름, 인원수, 부가서비스, 총 요금, 체크인, 체크아웃 날짜)

		/* 관리자는 투숙객의 부가서비스를 변경할 수 있다. */

	}

	// 체크인아웃 설정
	private void setCheckInOut() {
		System.out.println("체크인체크아웃 설정  메서드");
		/* 투숙객의 체크인, 체크아웃을 변경할 수 있다. */
	}

	// 지훈
	// 기본가격 설정 : 방 가격 설정, 부가서비스 메뉴를 보여준다.
	private void setPrice() {
		String menu = "";

		while (true) {
			System.out.println("기본가격 설정: 원하는 번호를 입력하세요.");
			System.out.println("1. 방 가격 설정");
			System.out.println("2. 부가서비스 가격 설정");

			menu = sc.nextLine();

			switch (menu) {
			case "1":
				setRoomPrice();
				return;
			case "2":
				setServicePrice();
				return;
			default:
				System.out.println("기본가격 설정: 1,2 중에 선택해주세요");
				break;
			}
		}
	}

	// 지훈
	// 방 가격 설정
	private void setRoomPrice() {
		int room;

		do {
			try {
				System.out.println("가격을 바꾸실 객실을 선택해주세요.");
				System.out.printf("1. %s 2. %s 3. %s\n", myHotel.getRoomInfos()[0].getRoomName(),
						myHotel.getRoomInfos()[1].getRoomName(), myHotel.getRoomInfos()[2].getRoomName());
				;

				room = Integer.parseInt(sc.nextLine());

				if (room >= 1 && room <= 3) {
					System.out.println("[" + myHotel.getRoomInfos()[room - 1].getRoomName() + "룸을 선택하셨습니다.");
					break;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해주세요.");
			}
		} while (true);

		exit: while (true) {
			switch (room) {
			case 1:
			case 2:
			case 3:
				System.out.println("변경할 가격을 입렵해주세요");
				myHotel.getRoomPrices()[room - 1] = Integer.parseInt(sc.nextLine());
				System.out.println("가격이 변경 되었습니다.");
				break exit;
			default:
				System.out.println("잘못 입력하였습니다.");
			}

		}

		System.out.println();
	}

	// 지훈
	// 부가서비스 가격 설정
	private void setServicePrice() {
		int service;
		String[] servicename = { "Breakfast", "Therapy" };

		do {
			try {
				System.out.println("가격을 바꾸실 부가서비스를 선택해주세요.");
				System.out.printf("1. %s 2. %s \n", servicename[0], servicename[1]);
				;

				service = Integer.parseInt(sc.nextLine());

				if (service >= 1 && service <= 2) {
					System.out.println("[" + servicename[service - 1] + "]" + "을 선택하셨습니다.");
					break;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해주세요.");
			}
		} while (true);

		while (true) {
			switch (service) {
			case 1:
				System.out.println("변경할 가격을 입렵해주세요");
				Number.breakfast = Integer.parseInt(sc.nextLine());
				System.out.println("가격이 변경 되었습니다.");
				return;
			case 2:
				System.out.println("변경할 가격을 입렵해주세요");
				Number.therapy = Integer.parseInt(sc.nextLine());
				System.out.println("가격이 변경 되었습니다.");
				return;
			default:
				System.out.println("잘못 입력하였습니다.");
				break;
			}

		}
	}

	// 정보 보기 : 매출확인
	private void getInfo() {
		String menu = "";

		while (true) {
			System.out.println("정보보기: 원하는 번호를 입력하세요.");
			System.out.println("1. 매출확인");
			System.out.println("2. 회원정보 확인");
			System.out.println("3. 투숙정보 확인");

			menu = sc.nextLine();

			switch (menu) {
			case "1":
				getSales();
				return;
			case "2":
				getMemberInfo();
				return;
			case "3":
				getRecord();
				return;
			default:
				System.out.println("기본가격 설정: 1,2,3 중에 선택해주세요");
				break;
			}
		}
	}

	// 매출 보기
	private void getSales() {
		System.out.println("매출보기 메서드");
	}

	// 세림
	// 회원 정보 보기
	private void getMemberInfo() {
		System.out.println("회원 정보 보기");

		Member memberLoggedIn = new Member("ubd2000", "김동민", "12345", "123441241", "8765544");
		myHotel.getMembers().put("ubd2000", memberLoggedIn);
		Member memberLoggedIn2 = new Member("ubd2001", "우세림", "67890", "123567945", "23454125");
		myHotel.getMembers().put("ubd2001", memberLoggedIn2);
		Member memberLoggedIn3 = new Member("ubd2002", "정진호", "34567", "35645167", "46434125");
		myHotel.getMembers().put("ubd2002", memberLoggedIn3);
		Member memberLoggedIn4 = new Member("ubd2003", "장지훈", "56789", "145897654", "3345125");
		myHotel.getMembers().put("ubd2003", memberLoggedIn4);
		Member memberLoggedIn5 = new Member("ubd2004", "권태환", "23456", "91256434", "3544125");
		myHotel.getMembers().put("ubd2004", memberLoggedIn5);

		Map<String, Member> memberId = myHotel.getMembers();

		Set<String> set = memberId.keySet();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// 아이디를 가지는 사람들
		// 전체 회원정보를 가져온다
//		System.out.println("이름: "+ member.getName()
//							+ "생년월일: "+ member.getBirthday()
//							+ "전화번호: "+ member.getPhoneNumber()
//							+ "VIP유무: "+ member
//							+ "총누적금액: "+ member.getRecords());
	}

	// 지훈, 세림
	// 투숙 기록 보기
	private void getRecord() {
		System.out.println("투숙 기록보기 메서드");
		
//		for(int i=0; i<2; i++) {
//			for(int j=1; j<6; j++) {
//				myHotel.getRooms().get(i).get(j).getGuests();
//			}
//		}
//		myHotel.getRooms().
		
		//날짜별 체크인, 아웃, 투숙 고객(예약한 사람 이름)
		
		//호텔에 예약한 기록들을 전부 다 본다.
		
		
	}

}