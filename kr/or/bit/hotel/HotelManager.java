package kr.or.bit.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelManager {
	private Hotel myHotel;
	private Scanner sc;
	private File file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	// ?�성??!
	public HotelManager() {
		this.sc = new Scanner(System.in);
	}

	public void run() {
		loadHotel();
		myHotel.setToday(LocalDate.now());
		// ?�기?? ?�동 처리?�는 ?�수
		setPrice();
		saveHotel();
	}

	// 지??, ?�림
	// ?�텔 ?�이�? ?�정
	public Hotel setHotel() {
		while (true) {
			System.out.println("?�텔?�이�? ?�력[?�형, 중형, ?�??: ");
			String hotelSize = sc.nextLine();

			switch (hotelSize) {
			case "?�형":
				System.out.println("?�형 ?�텔?? ?�성?�었??.");
				// saveHotel();
				return this.myHotel = new SmallHotel();
			case "중형":
				System.out.println("중형 ?�텔?? ?�성?�었??.");
				// saveHotel();
				return this.myHotel = new MediumHotel();
			case "?�??":
				System.out.println("?�?? ?�텔?? ?�성?�었??.");
				// saveHotel();
				return this.myHotel = new LargeHotel();
			default:
				System.out.println("?�형, 중형, ?�?? 중에 ?�택?�세??.");
				break;
			}
		}
	}

	private void loadHotel() {
		file = new File(CustomString.PATH_HOTEL);
		if (!file.exists()) {
			System.out.println("?�텔 ?�보가 존재?��? ?�습?�다.");
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
	 * ?�텔 ?�보 ?�?�하�?
	 * 
	 * 변경된 ?�텔 ?�보�? ?�??
	 * 
	 * ?�더가 ?�으�? ?�동?�로 ?�더 ?�성 ?? ?�??
	 * 
	 * ?�성?? : ?�종??
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

	// 객실관�? : ?�숙�?, 부가?�비??, 체크?�아?�을 관리하?? 메뉴�? 보여준??.
	private void roomManage() {
		loadHotel();
		String menu = "";

		while (true) {
			System.out.println("객실관�?: ?�하?? 번호�? ?�력?�세??.");
			System.out.println("1. ?�숙�? ?�보 ?�인");
			System.out.println("2. 부가?�비?? 변�?");
			System.out.println("3. 체크??, 체크?�웃 관�?");

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
				System.out.println("객실관�?: 1,2,3 중에 ?�택?�주?�요");
				break;
			}
		}
	}

	// ?�숙�? ?�보
	/*
	 * 1. ID�? 가?��???
     * 2. ID�? ?�해?? ?�원?�보�? 간다?�에
     * 3. ?�원 ?�보?�서 ?�약?�보�? 가?�오�?
     * 4. 체크?? ?�짜 <= ?�늘 ?�짜 <= 체크?�웃 ?�짜 ?�런 ?�람?? 찾아??
     * 5. ?�사?? ?�보�? 보여주게
     * 6. ?�으�? ?�숙�? ?�음
	 */
	public void getGuest() {
		System.out.println("?�숙�? ?�보�? ?�인?�니??.");
		// ex ?�정
		System.out.println("?�하?? 객실?? ?�력?�세??. [ex: 201~206, 301~306, 401~403, 501~502]: ");
		String roomNumber = sc.nextLine();

		char floor = roomNumber.charAt(0);
		char number = roomNumber.charAt(roomNumber.length() - 1);
		Room room = myHotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49

		List<String> temp = new ArrayList<String>();

		for (int i = 0; i < room.getGuests().size(); i++) {
			temp.add(room.getGuests().get(i));
		}

		Member temp1 = new Member();

		Iterator<String> it = myHotel.getMembers().keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = it.next();
			if (temp.get(i).equals(myHotel.getMembers().get(key).getId())) {
				temp1 = myHotel.getMembers().get(key);
			}
			i++;

//			System.out.println("temp: "+ temp);
//			System.out.println("myHotel.getMembers().get(key).getId(): "+myHotel.getMembers().get(key).getId());

		}

		System.out.println("?�름 : " + temp1.getName() + "\n?�원?? : " + temp1.getReservation().getNumberPeople()

				+ "\n부가?�비?? : " + (temp1.getReservation().isBreakfast() ? "조식" : "?�신?�라??")

				+ "\n�? ?�금 : " + temp1.getReservation().getAmountPaid() + "??" + "\n체크?? : "
				+ temp1.getReservation().getDateCheckIn().getCheckDate() + "\n체크?�웃 : " + temp1.getReservation().getDateCheckOut().getCheckDate());

	}

	// 부가?�비?? 변�?
	/*
	 * 1. ID�? 가?��???
     * 2. ID�? ?�해?? ?�원?�보�? 간다?�에
     * 3. ?�원 ?�보?�서 ?�약?�보�? 가?�오�?
     * 4. 체크?? ?�짜 <= ?�늘 ?�짜 <= 체크?�웃 ?�짜 ?�런 ?�람?? 찾아??
     * 5. ?�사?�의 ?�비?? 변�?
     * 6. 조식?? 1박당 1�? = ?�늘부?? 체크?�웃 ?�짜까�? * 조식 가�? >> amountPaid??
     * 6-1. ?�늘 체크?�웃?�면 ?�비?? 변�? ?�되�?
     * 6-2. ?�신 ?�라?�는 취소?�면 amountPaid 감소
     * 7. ?�으�? ?�숙�? ?�음
	 */
	private void setService() {
		System.out.println("부가?�비?��? 변경합?�다.");
		System.out.println("?�하?? 객실?? ?�력?�세??. [ex: 201~206, 301~306, 401~403, 501~502]: ");
		String roomNumber = sc.nextLine();

		char floor = roomNumber.charAt(0);
		char number = roomNumber.charAt(roomNumber.length() - 1);
		Room room = myHotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49

		List<String> temp = new ArrayList<String>();

		for (int i = 0; i < room.getGuests().size(); i++) {
			temp.add(room.getGuests().get(i));
		}

		Member temp1 = new Member();

		Iterator<String> it = myHotel.getMembers().keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = it.next();
			if (temp.get(i).equals(myHotel.getMembers().get(key).getId())) {
				temp1 = myHotel.getMembers().get(key);
			}
			i++;

			System.out.println("temp: " + temp);
			System.out.println("myHotel.getMembers().get(key).getId(): " + myHotel.getMembers().get(key).getId());
		}

		System.out.println(roomNumber + "?? 부가?�비?? ?�황?�: ");

		System.out.println("?�름 : " + temp1.getName() + "\n?�원?? : " + temp1.getReservation().getNumberPeople()

				+ "\n부가?�비?? : " + (temp1.getReservation().isBreakfast() ? "조식" : "?�신?�라??")

				+ "\n�? ?�금 : " + temp1.getRecords().getAmountPaid() + "??" + "\n체크?? : "
				+ temp1.getReservation().getDateCheckIn() + "\n체크?�웃 : " + temp1.getReservation().getDateCheckOut());
	}

	// 체크?�아?? ?�정
	/*
	 * HotelBooking?�서 ?�약?�는 거랑 거의 같게 ?�면 ?�건??
	 * ?�?? 처음?? ?�떤 ?�원?�로 ?�약?��? 받게(로그?�처??) - 비�?번호 체크까�??? ?�고
	 */
	private void setCheckInOut() {
		System.out.println("체크?�체?�아?? ?�정  메서??");
		/* ?�숙객의 체크??, 체크?�웃?? 변경할 ?? ?�다. */
	}

	// 지??
	// 기본가�? ?�정 : �? 가�? ?�정, 부가?�비?? 메뉴�? 보여준??.
	private void setPrice() {
		String menu = "";

		while (true) {
			System.out.println("기본가�? ?�정: ?�하?? 번호�? ?�력?�세??.");
			System.out.println("1. �? 가�? ?�정");
			System.out.println("2. 부가?�비?? 가�? ?�정");

			menu = sc.nextLine();

			switch (menu) {
			case "1":
				setRoomPrice();
				return;
			case "2":
				setServicePrice();
				return;
			default:
				System.out.println("기본가�? ?�정: 1,2 중에 ?�택?�주?�요");
				break;
			}
		}
	}

	// 지??
	// �? 가�? ?�정
	private void setRoomPrice() {
		int room;

		do {
			try {
				System.out.println("가격을 바꾸?? 객실?? ?�택?�주?�요.");
				System.out.printf("1. %s 2. %s 3. %s\n", myHotel.getRoomInfos()[0].getRoomName(),
						myHotel.getRoomInfos()[1].getRoomName(), myHotel.getRoomInfos()[2].getRoomName());
				;

				room = Integer.parseInt(sc.nextLine());

				if (room >= 1 && room <= 3) {
					System.out.println("[" + myHotel.getRoomInfos()[room - 1].getRoomName() + "룸을 ?�택?�셨?�니??.");
					break;
				}
			} catch (Exception e) {
				System.out.println("?�바�? 값을 ?�력?�주?�요.");
			}
		} while (true);

		exit: while (true) {
			switch (room) {
			case 1:
			case 2:
			case 3:
				System.out.println("변경할 가격을 ?�력?�주?�요");
				myHotel.getRoomPrices()[room - 1] = Integer.parseInt(sc.nextLine()); // ?�류 캐치
				System.out.println("가격이 변�? ?�었?�니??.");
				break exit;
			default:
				System.out.println("?�못 ?�력?��??�니??.");
			}

		}
		System.out.println();
	}

	// 지??
	// 부가?�비?? 가�? ?�정
	private void setServicePrice() {
		int service;
		String[] servicename = { "Breakfast", "Therapy" }; // ?��?�? 교체 (?�런거는 CustomString) CustomString.BreakfastString

		do {
			try {
				System.out.println("가격을 바꾸?? 부가?�비?��? ?�택?�주?�요.");
				System.out.printf("1. %s 2. %s \n", servicename[0], servicename[1]);
				;

				service = Integer.parseInt(sc.nextLine());

				if (service >= 1 && service <= 2) {
					System.out.println("[" + servicename[service - 1] + "]" + "?? ?�택?�셨?�니??.");
					break;
				}
			} catch (Exception e) {
				System.out.println("?�바�? 값을 ?�력?�주?�요.");
			}
		} while (true);

		while (true) {
			switch (service) {
			case 1:
				System.out.println("변경할 가격을 ?�렵?�주?�요");
				myHotel.getServicePrices()[0] = Integer.parseInt(sc.nextLine());
				System.out.println("가격이 변�? ?�었?�니??.");
				return;
			case 2:
				System.out.println("변경할 가격을 ?�렵?�주?�요");
				myHotel.getServicePrices()[1] = Integer.parseInt(sc.nextLine());
				System.out.println("가격이 변�? ?�었?�니??.");
				return;
			default:
				System.out.println("?�못 ?�력?��??�니??.");
				break;
			}
		}
	}

	// ?�보 보기 : 매출?�인
	private void getInfo() {
		String menu = "";

		while (true) {
			System.out.println("?�보 보기: ?�하?? 번호�? ?�력?�세??.");
			System.out.println("1. 매출 ?�인");
			System.out.println("2. ?�원 ?�보 ?�인");
			System.out.println("3. ?�숙 ?�보 ?�인");

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
				System.out.println("기본가�? ?�정: 1,2,3 중에 ?�택?�주?�요");
				break;
			}
		}
	}

	// 매출 보기
	private void getSales() {
		System.out.println("?�재 ?�텔 매출 - " + myHotel.getSales() + "?? ?�니??.");
	}

	// ?�림
	// ?�원 ?�보 보기
	public void getMemberInfo() {
		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println(" ?�름 :" + myHotel.getMembers().get(key).getName() + " ?�년?�일 : "
					+ myHotel.getMembers().get(key).getBirthday() + " ?�화번호 : "
					+ myHotel.getMembers().get(key).getPhoneNumber() + " VIP : true" + " ?�텔 ?�용 �? 금액 : "
					+ myHotel.getMembers().get(key).getRecords().getTotalPaid() + "??");
		}
	}

	// 지??, ?�림
	// ?�숙 기록 보기 : ?�텔?�약?�체 기록?? 가?��???(?�디?? 가?�오?�진 모름) 출력?�다.
	/*
	 * ?�짜별로 체크?�과 체크?�웃?? ?�?�이 ?�다
	 * CheckIn20190315.info, CheckOut20190315.info
	 * ?�중?? ?�짜�? 불러?�면 그날 체크?�한 ?�람�? 체크?�웃?? ?�람?? ?�고
	 * ?? ?�람?? ?�떻�? 객실?? ?�용?�는지 불러?�다
	 */
	public void getRecord() {
		
		/*
		 * ?�인?? ?�짜 ?�력 ex)20190315
		 * FileInputStream fis = new FileInputStream(CustomString.PATH_DIRECTORY + 20190315 + ".info");
		 * ObjectInputStream in = new ObjectInputStream(fis);
		 */

		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();

//			//체크?�웃 ?�인
//			if(myHotel.getMembers().get(key).getRecords().getDateCheckOut() > new Date()) {
//				
//			}

			System.out.println("체크??: " + myHotel.getMembers().get(key).getRecords().getDateCheckin() + "체크?�웃: "
					+ myHotel.getMembers().get(key).getRecords().getDateCheckOut());
		}
	}
	
	/*
	 * ?�텔 매니?��? ?�행?�면
	 * ?�텔 매니?�가 ?�늘 ?�짜�? 가?�온??
	 * 모든 방을 체크?�서 ?�늘 체크?�웃?? ?�람?? ?? 가?�온??
	 * Room?�서 Guests?�서 ?? ?�람?�을 ??��?�다
	 * ??��?�면?? ?? ?�람?�의 amountPaid만큼 sales?? ?�해준??
	 * ?? ?�람?�의 reservation ?�보�? record?? ?�아주고 reservation?� null�? 만들�?
	 * ?�늘 체크?�하?? ?�람�? ?�늘 체크?�웃 ?�는 ?�람?? ?�보�? ?�일�? ?�??
	 */
}