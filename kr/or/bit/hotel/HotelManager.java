package kr.or.bit.hotel;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HotelManager {
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

	public void run() {
		loadHotel();
		saveHotelCheckIn();
		saveHotelCheckOut();
		printMenu();
	}

	// 지훈, 세림
	// 호텔 사이즈 설정
	private Hotel setHotel() {
		while (true) {
			System.out.println("소형 호텔");
			System.out.println("1층 로비\n2층 디럭스 룸 4개\n3층 이그제큐티브 룸 1개\n      스위트 룸 1개");
			System.out.println("\n중형 호텔");
			System.out.println("1층 로비\n2층 디럭스 룸 6개\n3층 디럭스 룸 6개\n4층 이그제큐티브 룸 4개\n5층 스위트 룸 2개");
			System.out.println("\n대형 호텔");
			System.out.println(
					"1층 로비\n2층 디럭스 룸 8개\n3층 디럭스 룸 8개\n4층 디럭스 룸 8개\n5층 디럭스 룸 4개\n      이그제큐티브 룸 2개\n6층 이그제큐티브 룸 4개\n7층 스위트 룸 3개");
			System.out.println("\n호텔사이즈 입력[소형, 중형, 대형]: ");

			String hotelSize = sc.nextLine();

			switch (hotelSize) {
			case "소형":
				System.out.println("1층 로비\n2층 디럭스 룸 4개\n3층 이그제큐티브 룸 1개\n      스위트 룸 1개");
				System.out.println("소형 호텔이 생성되었습니다.");
				saveHotel();
				return this.myHotel = new SmallHotel();
			case "중형":
				System.out.println("1층 로비\n2층 디럭스 룸 6개\n3층 디럭스 룸 6개\n4층 이그제큐티브 룸 4개\n5층 스위트 룸 2개");
				System.out.println("중형 호텔이 생성되었습니다.");
				saveHotel();
				return this.myHotel = new MediumHotel();
			case "대형":
				System.out.println(
						"1층 로비\n2층 디럭스 룸 8개\n3층 디럭스 룸 8개\n4층 디럭스 룸 8개\n5층 디럭스 룸 4개\n      이그제큐티브 룸 2개\n6층 이그제큐티브 룸 4개\n7층 스위트 룸 3개");
				System.out.println("대형 호텔이 생성되었습니다.");
				saveHotel();
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
		} catch (IOException | ClassNotFoundException e) {
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

	private void printMenu() {
		while (true) {
			System.out.println("2조 호텔 관리 프로그램");
			System.out.println("┎                                  ┒");
			System.out.println("         1. 객실 관리");
			System.out.println();
			System.out.println("         2. 요금 변경");
			System.out.println();
			System.out.println("         3. 호텔 정보 확인");
			System.out.println();
			System.out.println("         4. 종료하기");
			System.out.println("┖                                  ┚");

			String select = sc.nextLine();
			switch (select) {
			case "1":
				roomManage();
				break;
			case "2":
				setPrice();
				break;
			case "3":
				getInfo();
				break;
			case "4":
				saveHotel();
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다.");
			}
		}
	}

	// 객실관리 : 투숙객, 부가서비스, 체크인아웃을 관리하는 메뉴를 보여준다.
	private void roomManage() {
		String menu;

		while (true) {
			System.out.println("객실 관리: 원하는 번호를 입력하세요.");
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
				return;
			case "3":
				setCheckInOut();
				return;
			default:
				System.out.println("객실 관리: 1,2,3 중에 선택해주세요");
				break;
			}
		}
	}

	/*
	 * 1. ID를 가져와서 
	 * 2. ID를 통해서 회원정보로 간다음에 
	 * 3. 회원 정보에서 예약정보를 가져오고 
	 * 4. 체크인 날짜 <= 오늘 날짜 <= 체크아웃 날짜 이런 사람을 찾아서 
	 * 5. 이사람 정보만 보여주게 
	 * 6. 없으면 투숙객 없음
	 */
	private void getGuest() {
		Member guest = null;
		LocalDate checkIn = null;
		LocalDate checkOut = null;

		System.out.println("투숙객 정보를 확인합니다.");
		System.out.println("원하는 객실을 입력하세요. [ex: 201~206, 301~306, 401~403, 501~502]: ");
		String roomNumber = sc.nextLine();
		char floor = roomNumber.charAt(0);
		char number = roomNumber.charAt(roomNumber.length() - 1);
		Room room = myHotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49

		for (String id : room.getGuests()) {
			checkIn = myHotel.getMembers().get(id).getReservation().getDateCheckIn().getCheckDate();
			checkOut = myHotel.getMembers().get(id).getReservation().getDateCheckOut().getCheckDate();
			if ((myHotel.getToday().isAfter(checkIn) || myHotel.getToday().isEqual(checkIn))
					&& myHotel.getToday().isBefore(checkOut)) {
				guest = myHotel.getMembers().get(id);
				break;
			}
		}

		if (guest == null) {
			System.out.println("투숙 중인 고객이 없습니다");
			return;
		}

		//부가서비스 확인
		String breakfast;
		String therapy;
		breakfast = guest.getReservation().isBreakfast() ? CustomString.breakfast + " O"
				: CustomString.breakfast + " X";
		therapy = guest.getReservation().isTherapy() ? CustomString.therapy + " O" : CustomString.therapy + " X";

		System.out.println("이름 : " + guest.getName() + "\n인원수 : " + guest.getReservation().getNumberPeople()
				+ "\n부가서비스 : " + breakfast + "/" + therapy + "\n총 요금 : "
				+ CustomString.putComma(guest.getReservation().getAmountPaid()) + "원" + "\n체크인 : " + checkIn
				+ "\n체크아웃 : " + checkOut);
	}

	/*
	 * 부가서비스 변경
	 * 1. ID를 가져와서 
	 * 2. ID를 통해서 회원정보로 간다음에 
	 * 3. 회원 정보에서 예약정보를 가져오고 
	 * 4. 체크인 날짜 <= 오늘 날짜 <= 체크아웃 날짜 이런 사람을 찾아서 
	 * 5. 이사람의 서비스 변경 
	 * 6. 조식이 1박당 1번 = 오늘부터 체크아웃 날짜까지 * 조식 가격 >> amountPaid에 
	 * 6-1. 오늘 체크아웃이면 서비스 변경 안되게 6-2. 전신 테라피는 취소하면 amountPaid 감소 
	 * 7. 없으면 투숙객 없음
	 * 
	 * 작성자 : 장지훈
	 * 수정 : 윤종석
	 */
	private void setService() {
		System.out.println("부가서비스를 변경합니다.");
		System.out.println("원하는 객실을 입력하세요. [ex: 201~206, 301~306, 401~403, 501~502]: ");
		String roomNumber = sc.nextLine();

		char floor = roomNumber.charAt(0);
		char number = roomNumber.charAt(roomNumber.length() - 1);
		Room room = myHotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49

		Member guest = null;
		LocalDate checkIn = null;
		LocalDate checkOut = null;

		for (String id : room.getGuests()) {
			checkIn = myHotel.getMembers().get(id).getReservation().getDateCheckIn().getCheckDate();
			checkOut = myHotel.getMembers().get(id).getReservation().getDateCheckOut().getCheckDate();
			if ((myHotel.getToday().isAfter(checkIn) || myHotel.getToday().isEqual(checkIn))
					&& myHotel.getToday().isBefore(checkOut)) {
				guest = myHotel.getMembers().get(id);
				break;
			}
		}

		if (guest == null) {
			System.out.println("투숙 중인 고객이 없습니다");
			return;
		}

		Period diff = Period.between(myHotel.getToday(), checkOut);

		System.out.println(roomNumber + "의 부가서비스 상황은: ");
		String breakfast = (guest.getReservation().isBreakfast()) ? "O" : "X";
		String therapy = (guest.getReservation().isTherapy()) ? "O" : "X";
		System.out.println("조식 : " + breakfast + " 전신 테라피 : " + therapy);

		System.out.println("변경하실 서비스를 선택해주세요.");
		System.out.println("1. 조식 2. 전신 테라피");
		String select = sc.nextLine();
		switch (select) {
		case "1":
			if (breakfast.equals("O")) {
				guest.getReservation().setBreakfast(false);
				guest.getReservation().setAmountPaid(
						guest.getReservation().getAmountPaid() - myHotel.getServicePrices()[0] * diff.getDays());
			} else {
				guest.getReservation().setBreakfast(true);
				guest.getReservation().setAmountPaid(
						guest.getReservation().getAmountPaid() + myHotel.getServicePrices()[0] * diff.getDays());
			}
			break;
		case "2":
			if (therapy.equals("O")) {
				guest.getReservation().setTherapy(false);
				guest.getReservation()
						.setAmountPaid(guest.getReservation().getAmountPaid() - myHotel.getServicePrices()[1]);
			} else {
				guest.getReservation().setTherapy(true);
				guest.getReservation()
						.setAmountPaid(guest.getReservation().getAmountPaid() + myHotel.getServicePrices()[1]);
			}
			break;
		default:
			System.out.println("잘못된 값을 입력하셨습니다.");
		}
	}

	// 체크인아웃 설정
	/*
	 * HotelBooking에서 예약하는 거랑 거의 같게 하면 될건데 대신 처음에 어떤 회원으로 예약할지 받게(로그인처럼) - 비밀번호
	 * 체크까지는 됐고
	 */
	private void setCheckInOut() {
		
		System.out.println("체크인체크아웃 설정- id 입력: ");
        String id = sc.nextLine();
        
        String roomnumber = myHotel.getMembers().get(id).getReservation().getRoom().getRoomNumber();
        
        System.out.println(myHotel.getMembers().get(id).getName() + "님의 예약 정보 입니다.");
        System.out.println("현재 예약 된 방 : " + roomnumber + "호");
        System.out.println("체크인 날짜 : " + myHotel.getMembers().get(id).getReservation().getDateCheckIn().getCheckDate());
        System.out.println("체크인 날짜 : " + myHotel.getMembers().get(id).getReservation().getDateCheckOut().getCheckDate());
        
        System.out.print("예약을 변경합니다.");
        
        myHotel.getMembers().get(id).getReservation().getRoom().getGuests().remove(id);
        myHotel.getMembers().get(id).setReservation(null);

        Reservation r = myHotel.getMembers().get(id).getReservation();
        
        
        LocalDate today = LocalDate.now();
		HotelDate dateCheckIn;
		HotelDate dateCheckOut;
		while (true) {
			// TODO : 정규표현식으로 포맷 제한
			System.out.println("체크인 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
			String checkIn = sc.nextLine();
			if (!checkIn.matches(
					"^20(\\d{2})(((0([13578])|1([02]))(0[1-9]|[1-2][0-9]|3[0-1]))|((0([469])|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|([12])[0-9]$)))")) {
				System.out.println("체크인 날짜를 선택해주세요.");
			} else {
				dateCheckIn = new HotelDate(checkIn);
				if (dateCheckIn.getCheckDate().isBefore(today)) {
					System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
				} else {
					break;
				}
			}
		}

		while (true) {
			// TODO : 정규표현식으로 포맷 제한
			System.out.println("체크아웃 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
			String checkOut = sc.nextLine();
			if (!checkOut.matches(
					"^20(\\d{2})(((0([13578])|1([02]))(0[1-9]|[1-2][0-9]|3[0-1]))|((0([469])|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|([12])[0-9]$)))")) {
				System.out.println("체크아웃 날짜를 선택해주세요.");
			} else {
				dateCheckOut = new HotelDate(checkOut);
				if (dateCheckOut.getCheckDate().isBefore(today)
						|| dateCheckOut.getCheckDate().isBefore(dateCheckIn.getCheckDate())
						|| dateCheckOut.getCheckDate().isEqual(dateCheckIn.getCheckDate())) {
					System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
				} else {
					break;
				}
			}
		}

		System.out.println("체크인 날짜 : " + dateCheckIn.getCheckDate());
		System.out.println("체크아웃 날짜 : " + dateCheckOut.getCheckDate());

		r.setDateCheckIn(dateCheckIn);
		r.setDateCheckOut(dateCheckOut);
		
        Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());
		System.out.println("예약 가능 객실 번호");
		
		List<Room> roomToReserve = new ArrayList<Room>();
		
		for (int i = 0; i < myHotel.getRooms().size(); i++) {
			for (int j = 0; j < myHotel.getRooms().get(i).size(); j++) {
				boolean canReserve = true;
				for (String guestId : myHotel.getRooms().get(i).get(j).getGuests()) {
					if (myHotel.getRooms().get(i).get(j).getGuests().size() == 0) {
						break;
					}
					LocalDate checkIn = myHotel.getMembers().get(guestId).getReservation().getDateCheckIn()
							.getCheckDate();
					LocalDate checkOut = myHotel.getMembers().get(guestId).getReservation().getDateCheckOut()
							.getCheckDate();
					if (checkIn.isBefore(r.getDateCheckIn().getCheckDate())
							&& checkOut.isAfter(r.getDateCheckIn().getCheckDate())
							|| checkIn.isEqual(r.getDateCheckIn().getCheckDate())
							|| checkOut.isEqual(r.getDateCheckOut().getCheckDate())) {
						canReserve = false;
						break;
					} else if (checkIn.isBefore(r.getDateCheckOut().getCheckDate())
							&& checkOut.isAfter(r.getDateCheckOut().getCheckDate())
							|| checkIn.isEqual(r.getDateCheckIn().getCheckDate())
							|| checkOut.isEqual(r.getDateCheckOut().getCheckDate())) {
						canReserve = false;
						break;
					} else if (checkIn.isAfter(r.getDateCheckIn().getCheckDate())
							&& checkIn.isBefore(r.getDateCheckOut().getCheckDate())
							&& checkOut.isAfter(r.getDateCheckIn().getCheckDate())
							&& checkOut.isBefore(r.getDateCheckOut().getCheckDate())
							|| checkIn.isEqual(r.getDateCheckIn().getCheckDate())
							|| checkOut.isEqual(r.getDateCheckOut().getCheckDate())) {
						canReserve = false;
						break;
					}
				}
				if (canReserve) {
					roomToReserve.add(myHotel.getRooms().get(i).get(j));
				}
			}
			System.out.println();
		}

		for (Room room1 : roomToReserve) {
			System.out.println(room1.getRoomNumber() + "호 ");
		}

		Room room;
			System.out.println("예약할 방 번호를 입력해주세요");
			String roomNumber = sc.nextLine();
			char floor = roomNumber.charAt(0);
			char number = roomNumber.charAt(roomNumber.length() - 1);
			room = myHotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49
			
		room.getGuests().add(id);
		r.setAmountPaid(r.getAmountPaid() + (myHotel.getRoomPrices()[0] * diff.getDays()));
		System.out.println(
				"숙박일수 [" + diff.getDays() + "]\n숙박 요금 [" + CustomString.putComma(r.getAmountPaid()) + "]원 입니다.");
		r.setRoom(room);
	}
		
		
//        LocalDate checkIn = null;
//        LocalDate checkOut = null;
//        checkIn = myHotel.getMembers().get(id).getReservation().getDateCheckIn().getCheckDate();
//        checkOut = myHotel.getMembers().get(id).getReservation().getDateCheckOut().getCheckDate();
//        System.out.println(id + "의 체크인: " + checkIn);
//        System.out.println(id + "의 체크아웃: " + checkOut);
//        LocalDate today = LocalDate.now();
//        HotelDate dateCheckInd = null;
//        HotelDate dateCheckOutd = null;
//        
//        
//        while (true) {
//            System.out.println("체크인 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
//            String checkInd = sc.nextLine();
//            if (!checkInd.matches(
//                    "^20(\\d{2})(((0(1|3|5|7|8)|1(0|2))(0[1-9]|[1-2][0-9]|3[0-1]))|((0(4|6|9)|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|(1|2)[0-9]$)))")) {
//                System.out.println("체크인 날짜를 선택해주세요.");
//            } else {
//            	
//            	
//                dateCheckInd = new HotelDate(checkInd);
//                if (dateCheckInd.getCheckDate().isBefore(today)) {
//                    System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
//                } else {
//                    break;
//                }
//            }
//        }
//        
//        while (true) {
//            System.out.println("체크아웃 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
//            String checkOutd = sc.nextLine();
//            if (!checkOutd.matches("^[0-9]{8}+$")) {
//                System.out.println("체크아웃 날짜를 선택해주세요.");
//            } else {
//                dateCheckOutd = new HotelDate(checkOutd);
//                if (dateCheckOutd.getCheckDate().isBefore(today)
//                        || dateCheckOutd.getCheckDate().isBefore(dateCheckInd.getCheckDate())
//                        || dateCheckOutd.getCheckDate().isEqual(dateCheckInd.getCheckDate())) {
//                    System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
//                } else {
//                    break;
//                }
//            }
//        }
//        
//        System.out.println(id + "의 체크인: " + checkIn);
//        System.out.println(id + "의 체크아웃: " + checkOut);
//        myHotel.getMembers().get(id).getReservation().setDateCheckIn(dateCheckInd);
//        myHotel.getMembers().get(id).getReservation().setDateCheckOut(dateCheckOutd);
		/* 투숙객의 체크인, 체크아웃을 변경할 수 있다. */
	

	// 지훈
	// 기본가격 설정 : 방 가격 설정, 부가서비스 메뉴를 보여준다.
	private void setPrice() {
		String menu;

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
					System.out.println(myHotel.getRoomInfos()[room - 1].getRoomName() + "룸을 선택하셨습니다.");
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
				System.out.println("변경할 가격을 입력해주세요");
				myHotel.getRoomPrices()[room - 1] = Integer.parseInt(sc.nextLine()); // 오류 캐치
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
		String[] serviceName = { CustomString.breakfast, CustomString.therapy }; // 한글로 교체 (이런거는 CustomString) CustomString.BreakfastString

		do {
			try {
				System.out.println("가격을 바꾸실 부가서비스를 선택해주세요.");
				System.out.printf("1. %s 2. %s \n", serviceName[0], serviceName[1]);
				;

				service = Integer.parseInt(sc.nextLine());

				if (service >= 1 && service <= 2) {
					System.out.println("[" + serviceName[service - 1] + "]" + "을 선택하셨습니다.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("올바른 값을 입력해주세요.");
			}
		} while (true);

		while (true) {
			switch (service) {
			case 1:
				System.out.println("변경할 가격을 입렵해주세요");
				myHotel.getServicePrices()[0] = Integer.parseInt(sc.nextLine());
				System.out.println("가격이 변경 되었습니다.");
				return;
			case 2:
				System.out.println("변경할 가격을 입렵해주세요");
				myHotel.getServicePrices()[1] = Integer.parseInt(sc.nextLine());
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
		String menu;

		while (true) {
			System.out.println("정보 보기: 원하는 번호를 입력하세요.");
			System.out.println("1. 매출 확인");
			System.out.println("2. 회원 정보 확인");
			System.out.println("3. 투숙 정보 확인");

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
		System.out.println("현재 호텔 매출 - " + myHotel.getSales() + "원 입니다.");
	}

	// 작성 : 우세림
	// 회원 정보 보기
	// 수정 : 윤종석
	private void getMemberInfo() {
		if (myHotel.getMembers().size() == 0) {
			System.out.println("가입된 회원이 없습니다.");
			return;
		}

		for (String key : myHotel.getMembers().keySet()) {
			System.out.println("이름 :" + myHotel.getMembers().get(key).getName() + " 생년월일 : "
					+ myHotel.getMembers().get(key).getBirthday() + " 전화번호 : "
					+ myHotel.getMembers().get(key).getPhoneNumber() + " VIP : "
					+ myHotel.getMembers().get(key).isVipString() + " 호텔 이용 총 금액 : "
					+ myHotel.getMembers().get(key).getRecords().getTotalPaid() + "원");
		}
	}

	// 지훈, 세림
	// 투숙 기록 보기 : 호텔예약전체 기록을 가져와서(어디서 가져오는진 모름) 출력한다.
	/*
	 * 날짜별로 체크인과 체크아웃이 저장이 된다 CheckIn20190315.info, CheckOut20190315.info 나중에 날짜를
	 * 불러오면 그날 체크인한 사람과 체크아웃한 사람이 있고 이 사람이 어떻게 객실을 이용했는지 불러온다
	 */
	private void getRecord() {

		System.out.println("체크인 체크아웃 기록을 확인할 날짜를 선택하세요.");
		String date = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate dateToCheck = LocalDate.parse(date, formatter);

		file = new File(CustomString.PATH_RECORD_DIRECTORY(dateToCheck));

		if (!file.exists()) {
			System.out.println("저장된 기록이 없습니다.");
			return;
		}

		try {
			file = new File(CustomString.PATH_RECORD(dateToCheck, "in"));
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);

			System.out.println("체크인");
			
			String data;
			try {
				while ((data = (String) in.readObject()) != null) {
					System.out.println(data);
				}
			} catch (EOFException e) {
				System.out.println();
			}

			in.close();
			fis.close();

			file = new File(CustomString.PATH_RECORD(dateToCheck, "out"));
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);

			System.out.println("체크아웃");

			try {
				while ((data = (String) in.readObject()) != null) {
					System.out.println(data);
				}
			} catch (EOFException e) {
				System.out.println();
			}


		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void saveHotelCheckOut() {

		List<String> guests = new ArrayList<String>();

		for (int i = 0; i < myHotel.getRooms().size(); i++) {
			for (int j = 0; j < myHotel.getRooms().get(i).size(); j++) {
				for (int k = 0; k < myHotel.getRooms().get(i).get(j).getGuests().size(); k++) {
					guests.add(myHotel.getRooms().get(i).get(j).getGuests().get(k));
				}
			}
		}

		file = new File(CustomString.PATH_RECORD_DIRECTORY(myHotel.getToday()));
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(CustomString.PATH_RECORD(myHotel.getToday(), "out"));

		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			for (String guest : guests) {
				Member member = myHotel.getMembers().get(guest);
				if (member.getReservation() != null) {
					if (member.getReservation().getDateCheckOut().getCheckDate().isEqual(myHotel.getToday())) {
						myHotel.setSales(member.getReservation().getAmountPaid());
						member.getRecords().addReservation(member.getReservation());
						member.getReservation().getRoom().getGuests().remove(member.getId());
						out.writeObject(member.getReservation().checkRecord(member));
						member.setReservation(null);
					}
				}
				if (member.getRecords().getTotalPaid() >= 10000000) {
					member.setVip(true);
				}
			}
		} catch (Exception e) {
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

	private void saveHotelCheckIn() {

		List<String> guests = new ArrayList<String>();

		for (int i = 0; i < myHotel.getRooms().size(); i++) {
			for (int j = 0; j < myHotel.getRooms().get(i).size(); j++) {
				for (int k = 0; k < myHotel.getRooms().get(i).get(j).getGuests().size(); k++) {
					guests.add(myHotel.getRooms().get(i).get(j).getGuests().get(k));
				}
			}
		}

		file = new File(CustomString.PATH_RECORD_DIRECTORY(myHotel.getToday()));
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(CustomString.PATH_RECORD(myHotel.getToday(), "in"));

		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			for (String guest : guests) {
				Member member = myHotel.getMembers().get(guest);
				if (member.getReservation() != null) {
					if (member.getReservation().getDateCheckIn().getCheckDate().isEqual(myHotel.getToday())) {
						member.getRecords().addReservation(member.getReservation());
						out.writeObject(member.getReservation().checkRecord(member));
					}
				}
			}
		} catch (Exception e) {
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
}
