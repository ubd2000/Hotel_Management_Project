package kr.or.bit.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
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

<<<<<<< .merge_file_a04784
	public static void main(String[] args) {
		HotelManager myHotel = new HotelManager();
		myHotel.getInfo();
	}

=======
	// 생성자!
>>>>>>> .merge_file_a01436
	public HotelManager() {
		this.sc = new Scanner(System.in);
	}

	public void run() {
		loadHotel();
		autoCheckOut();
		printMenu();
	}

	// 호텔 사이즈 설정
	public Hotel setHotel() {
		while (true) {
			System.out.println("호텔사이즈 입력[소형, 중형, 대형]: ");
			String hotelSize = sc.nextLine();

			switch (hotelSize) {
			case "소형":
				System.out.println("소형 호텔이 생성되었다.");
				saveHotel();
				return this.myHotel = new SmallHotel();
			case "중형":
				System.out.println("중형 호텔이 생성되었다.");
				saveHotel();
				return this.myHotel = new MediumHotel();
			case "대형":
				System.out.println("대형 호텔이 생성되었다.");
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

	public void printMenu() {
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
				return;
			case "3":
				setCheckInOut();
				return;
			default:
				System.out.println("객실관리: 1,2,3 중에 선택해주세요");
				break;
			}
		}
	}

	// 투숙객 정보
	/*
<<<<<<< .merge_file_a04784
	 * 1. ID를 가져와서
     * 2. ID를 통해서 회원정보로 간다음에
     * 3. 회원 정보에서 예약정보를 가져오고
     * 4. 체크인 날짜 <= 오늘 날짜 <= 체크아웃 날짜 이런 사람을 찾아서
     * 5. 이사람 정보만 보여주게
     * 6. 없으면 투숙객 없음
	 */
	public void getGuest() {
		System.out.println("투숙객 정보를 확인합니다.");
		System.out.println("원하는 객실을 입력하세요. [ex: 201~206 501~502]: ");
=======
	 * 1. ID를 가져와서 
	 * 2. ID를 통해서 회원정보로 간다음에 
	 * 3. 회원 정보에서 예약정보를 가져오고 
	 * 4. 체크인 날짜 <= 오늘 날짜 <= 체크아웃 날짜 이런 사람을 찾아서 
	 * 5. 이사람 정보만 보여주게 
	 * 6. 없으면 투숙객 없음
	 */
	public void getGuest() {
		System.out.println("투숙객 정보를 확인합니다.");
		// ex 수정
		System.out.println("원하는 객실을 입력하세요. [ex: 201~206, 301~306, 401~403, 501~502]: ");
>>>>>>> .merge_file_a01436
		String roomNumber = sc.nextLine();

		char floor = roomNumber.charAt(0);
		char number = roomNumber.charAt(roomNumber.length() - 1);
		Room room = myHotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49

<<<<<<< .merge_file_a04784
		//room.getGuests().g
		
=======
		if (room.getGuests().size() == 0) {
			System.out.println("투숙 중인 고객이 없습니다.");
			return;
		}

>>>>>>> .merge_file_a01436
		List<String> temp = new ArrayList<String>();

		for (int i = 0; i < room.getGuests().size(); i++) {
			temp.add(room.getGuests().get(i));
		}

		Member temp1 = new Member(null, null, null, null, null);

		Iterator<String> it = myHotel.getMembers().keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = it.next();
			if (temp.get(i).equals(myHotel.getMembers().get(key).getId())) {
				temp1 = myHotel.getMembers().get(key);
			}
			i++;
<<<<<<< .merge_file_a04784
		}
=======

			//			System.out.println("temp: "+ temp);
			//			System.out.println("myHotel.getMembers().get(key).getId(): "+myHotel.getMembers().get(key).getId());
>>>>>>> .merge_file_a01436

		String breakfast = "";
		if (temp1.getReservation().isBreakfast() == true) {
			breakfast = "조식o";
		} else {
			breakfast = "조식x";
		}

<<<<<<< .merge_file_a04784
		String therapy = "";
		if (temp1.getReservation().isTherapy() == true) {
			therapy = "전신테라피o";
		} else {
			therapy = "전신테라피x";
		}

		System.out.println("이름 : " + temp1.getName() + "\n인원수 : " + temp1.getReservation().getNumberPeople()
				+ "\n부가서비스 : " + breakfast + "/" + therapy + "\n총 요금 : " + temp1.getRecords().getAmountPaid() + "원"
				+ "\n체크인 : " + temp1.getReservation().getDateCheckIn() + "\n체크아웃 : "
				+ temp1.getReservation().getDateCheckOut());

	}

	// 부가서비스 변경
	/*
	 * 1. ID를 가져와서
     * 2. ID를 통해서 회원정보로 간다음에
     * 3. 회원 정보에서 예약정보를 가져오고
     * 4. 체크인 날짜 <= 오늘 날짜 <= 체크아웃 날짜 이런 사람을 찾아서
     * 5. 이사람의 서비스 변경
     * 6. 조식이 1박당 1번 = 오늘부터 체크아웃 날짜까지 * 조식 가격 >> amountPaid에
     * 6-1. 오늘 체크아웃이면 서비스 변경 안되게
     * 6-2. 전신 테라피는 취소하면 amountPaid 감소
     * 7. 없으면 투숙객 없음
=======
		System.out.println("이름 : " + temp1.getName() + "\n인원수 : " + temp1.getReservation().getNumberPeople()

				+ "\n부가서비스 : " + (temp1.getReservation().isBreakfast() ? "조식" : "전신테라피")

				+ "\n총 요금 : " + CustomString.putComma(temp1.getReservation().getAmountPaid()) + "원" + "\n체크인 : "
				+ temp1.getReservation().getDateCheckIn().getCheckDate() + "\n체크아웃 : "
				+ temp1.getReservation().getDateCheckOut().getCheckDate());

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
>>>>>>> .merge_file_a01436
	 */
	private void setService() {
		// 객실 선택
		System.out.println("부가서비스를 변경합니다.");
		System.out.println("원하는 객실을 입력하세요. [ex: 201~206, 301~306, 401~403, 501~502]: ");
		String roomNumber = sc.nextLine();

		char floor = roomNumber.charAt(0);
		char number = roomNumber.charAt(roomNumber.length() - 1);
		Room room = myHotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49
<<<<<<< .merge_file_a04784

		List<String> temp = new ArrayList<String>();

		for (int i = 0; i < room.getGuests().size(); i++) {
			temp.add(room.getGuests().get(i));
		}
=======
>>>>>>> .merge_file_a01436

		Member guest = null;
		LocalDate checkIn = null;
		LocalDate checkOut = null;

		for (String id : room.getGuests()) {
			checkIn = myHotel.getMembers().get(id).getReservation().getDateCheckIn().getCheckDate();
			checkOut = myHotel.getMembers().get(id).getReservation().getDateCheckOut().getCheckDate();
			if ((myHotel.getToday().isAfter(checkIn) || myHotel.getToday().isEqual(checkIn))
					&& myHotel.getToday().isBefore(checkOut)) { // (체크인 <= 오늘 < 체크아웃)
				guest = myHotel.getMembers().get(id);
				break;
			}
<<<<<<< .merge_file_a04784
			i++;

			System.out.println("temp: " + temp);
			System.out.println("myHotel.getMembers().get(key).getId(): " + myHotel.getMembers().get(key).getId());

		}

		// 조식, 테라피 확인
		String service = "";
		if (temp1.getReservation().isBreakfast() == true) {
			service += " 조식";
		}
		if (temp1.getReservation().isTherapy() == true) {
			service += " 테라피";
		}

		System.out.println(roomNumber + "의 부가서비스 : " + service);

		// 부가서비스 입력
		System.out.println("서비스를 추가하세요: [ex 조식, 전신테라피]");
		String roomService = sc.nextLine();
		
		if(roomService.equals("조식")) {
			
		}
		if(roomService.equals("전신테라피")) {
			
		}
		
		
		
		
	}

	// 체크인아웃 설정
	/*
	 * HotelBooking에서 예약하는 거랑 거의 같게 하면 될건데
	 * 대신 처음에 어떤 회원으로 예약할지 받게(로그인처럼) - 비밀번호 체크까지는 됐고
=======
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
			if (breakfast == "O") {
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
			if (therapy == "O") {
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
>>>>>>> .merge_file_a01436
	 */
	private void setCheckInOut() {
		System.out.println("체크인체크아웃 설정  메서드");
		/* 투숙객의 체크인, 체크아웃을 변경할 수 있다. */
	}

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

	// 부가서비스 가격 설정
	private void setServicePrice() {
		int service;
		String[] servicename = { "Breakfast", "Therapy" }; // 한글로 교체 (이런거는 CustomString) CustomString.BreakfastString

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
		String menu = "";

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

	// 회원 정보 보기
	public void getMemberInfo() {
		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println(" 이름 :" + myHotel.getMembers().get(key).getName() + " 생년월일 : "
					+ myHotel.getMembers().get(key).getBirthday() + " 전화번호 : "
					+ myHotel.getMembers().get(key).getPhoneNumber() + " VIP : true" + " 호텔 이용 총 금액 : "
					+ myHotel.getMembers().get(key).getRecords().getTotalPaid() + "원");
		}
	}

	// 투숙 기록 보기 : 호텔예약전체 기록을 가져와서(어디서 가져오는진 모름) 출력한다.
	/*
	 * 날짜별로 체크인과 체크아웃이 저장이 된다 CheckIn20190315.info, CheckOut20190315.info 나중에 날짜를
	 * 불러오면 그날 체크인한 사람과 체크아웃한 사람이 있고 이 사람이 어떻게 객실을 이용했는지 불러온다
	 */
	public void getRecord() {

		/*
		 * 확인할 날짜 입력 ex)20190315 FileInputStream fis = new FileInputStream(CustomString.PATH_DIRECTORY + 20190315 + ".info");
		 * ObjectInputStream in = new ObjectInputStream(fis);
		 */

		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();

<<<<<<< .merge_file_a04784
=======
			//			//체크아웃 확인
			//			if(myHotel.getMembers().get(key).getRecords().getDateCheckOut() > new Date()) {
			//				
			//			}

>>>>>>> .merge_file_a01436
			System.out.println("체크인: " + myHotel.getMembers().get(key).getRecords().getDateCheckin() + "체크아웃: "
					+ myHotel.getMembers().get(key).getRecords().getDateCheckOut());
		}
	}

	/*
	 * 호텔 매니저를 실행하면 호텔 매니저가 오늘 날짜를 가져온다 
	 * 모든 방을 체크해서 오늘 체크아웃인 사람을 다 가져온다 
	 * Room에서 Guests에서 이 사람들을 삭제한다 
	 * 삭제하면서 이 사람들의 amountPaid만큼 sales을 더해준다 
	 * 이 사람들의 reservation 정보를 record에 담아주고 reservation은 null로 만들고 
	 * 오늘 체크인하는 사람과 오늘 체크아웃 하는 사람의 정보를 파일로
	 * 저장
	 */
	public void autoCheckOut() {
		myHotel.setToday(LocalDate.now());
		List<String> ids = null;

		file = new File(CustomString.PATH_RECORD_DIRECTORY(myHotel.getToday()));
		if (!file.exists()) {
			file.mkdirs();
		}
		
		file = new File(CustomString.PATH_RECORD(myHotel.getToday(), "out"));
		
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			
			for (int i = 0; i < myHotel.getRooms().size(); i++) {
				for (int j = 0; j < myHotel.getRooms().get(i).size(); j++) {
					ids = myHotel.getRooms().get(i).get(j).getGuests();
				}
			}

			for (String id : ids) {
				Reservation reservationCheckOut = myHotel.getMembers().get(id).getReservation();
				if (reservationCheckOut.getDateCheckOut().getCheckDate().isEqual(myHotel.getToday())) {
					myHotel.setSales(myHotel.getSales() + reservationCheckOut.getAmountPaid()); // sales에 더하기
					myHotel.getMembers().get(id).getRecords().addReservation(reservationCheckOut); // Record에 reservation 기록
					myHotel.getMembers().get(id).getReservation().getRoom().getGuests().remove(id); // 방에서 ID 삭제
					out.writeObject(reservationCheckOut); // 체크아웃 정보 저장
					myHotel.getMembers().get(id).setReservation(null); // 예약 정보 삭제
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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