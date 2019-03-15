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

	// ?์ฑ??!
	public HotelManager() {
		this.sc = new Scanner(System.in);
	}

	public void run() {
		loadHotel();
		myHotel.setToday(LocalDate.now());
		// ?ฌ๊ธฐ?? ?๋ ์ฒ๋ฆฌ?๋ ?จ์
		setPrice();
		saveHotel();
	}

	// ์ง??, ?ธ๋ฆผ
	// ?ธํ ?ฌ์ด์ฆ? ?ค์ 
	public Hotel setHotel() {
		while (true) {
			System.out.println("?ธํ?ฌ์ด์ฆ? ?๋ ฅ[?ํ, ์คํ, ???: ");
			String hotelSize = sc.nextLine();

			switch (hotelSize) {
			case "?ํ":
				System.out.println("?ํ ?ธํ?? ?์ฑ?์??.");
				// saveHotel();
				return this.myHotel = new SmallHotel();
			case "์คํ":
				System.out.println("์คํ ?ธํ?? ?์ฑ?์??.");
				// saveHotel();
				return this.myHotel = new MediumHotel();
			case "???":
				System.out.println("??? ?ธํ?? ?์ฑ?์??.");
				// saveHotel();
				return this.myHotel = new LargeHotel();
			default:
				System.out.println("?ํ, ์คํ, ??? ์ค์ ? ํ?์ธ??.");
				break;
			}
		}
	}

	private void loadHotel() {
		file = new File(CustomString.PATH_HOTEL);
		if (!file.exists()) {
			System.out.println("?ธํ ?๋ณด๊ฐ ์กด์ฌ?์? ?์ต?๋ค.");
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
	 * ?ธํ ?๋ณด ??ฅํ๊ธ?
	 * 
	 * ๋ณ๊ฒฝ๋ ?ธํ ?๋ณด๋ฅ? ???
	 * 
	 * ?ด๋๊ฐ ?์ผ๋ฉ? ?๋?ผ๋ก ?ด๋ ?์ฑ ?? ???
	 * 
	 * ?์ฑ?? : ?ค์ข??
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

	// ๊ฐ์ค๊ด๋ฆ? : ?ฌ์๊ฐ?, ๋ถ๊ฐ?๋น??, ์ฒดํฌ?ธ์?์ ๊ด๋ฆฌํ?? ๋ฉ๋ด๋ฅ? ๋ณด์ฌ์ค??.
	private void roomManage() {
		loadHotel();
		String menu = "";

		while (true) {
			System.out.println("๊ฐ์ค๊ด๋ฆ?: ?ํ?? ๋ฒํธ๋ฅ? ?๋ ฅ?์ธ??.");
			System.out.println("1. ?ฌ์๊ฐ? ?๋ณด ?์ธ");
			System.out.println("2. ๋ถ๊ฐ?๋น?? ๋ณ๊ฒ?");
			System.out.println("3. ์ฒดํฌ??, ์ฒดํฌ?์ ๊ด๋ฆ?");

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
				System.out.println("๊ฐ์ค๊ด๋ฆ?: 1,2,3 ์ค์ ? ํ?ด์ฃผ?ธ์");
				break;
			}
		}
	}

	// ?ฌ์๊ฐ? ?๋ณด
	/*
	 * 1. ID๋ฅ? ๊ฐ?ธ์???
     * 2. ID๋ฅ? ?ตํด?? ?์?๋ณด๋ก? ๊ฐ๋ค?์
     * 3. ?์ ?๋ณด?์ ?์ฝ?๋ณด๋ฅ? ๊ฐ?ธ์ค๊ณ?
     * 4. ์ฒดํฌ?? ? ์ง <= ?ค๋ ? ์ง <= ์ฒดํฌ?์ ? ์ง ?ด๋ฐ ?ฌ๋?? ์ฐพ์??
     * 5. ?ด์ฌ?? ?๋ณด๋ง? ๋ณด์ฌ์ฃผ๊ฒ
     * 6. ?์ผ๋ฉ? ?ฌ์๊ฐ? ?์
	 */
	public void getGuest() {
		System.out.println("?ฌ์๊ฐ? ?๋ณด๋ฅ? ?์ธ?ฉ๋??.");
		// ex ?์ 
		System.out.println("?ํ?? ๊ฐ์ค?? ?๋ ฅ?์ธ??. [ex: 201~206, 301~306, 401~403, 501~502]: ");
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

		System.out.println("?ด๋ฆ : " + temp1.getName() + "\n?ธ์?? : " + temp1.getReservation().getNumberPeople()

				+ "\n๋ถ๊ฐ?๋น?? : " + (temp1.getReservation().isBreakfast() ? "์กฐ์" : "?์ ?๋ผ??")

				+ "\n์ด? ?๊ธ : " + temp1.getReservation().getAmountPaid() + "??" + "\n์ฒดํฌ?? : "
				+ temp1.getReservation().getDateCheckIn().getCheckDate() + "\n์ฒดํฌ?์ : " + temp1.getReservation().getDateCheckOut().getCheckDate());

	}

	// ๋ถ๊ฐ?๋น?? ๋ณ๊ฒ?
	/*
	 * 1. ID๋ฅ? ๊ฐ?ธ์???
     * 2. ID๋ฅ? ?ตํด?? ?์?๋ณด๋ก? ๊ฐ๋ค?์
     * 3. ?์ ?๋ณด?์ ?์ฝ?๋ณด๋ฅ? ๊ฐ?ธ์ค๊ณ?
     * 4. ์ฒดํฌ?? ? ์ง <= ?ค๋ ? ์ง <= ์ฒดํฌ?์ ? ์ง ?ด๋ฐ ?ฌ๋?? ์ฐพ์??
     * 5. ?ด์ฌ?์ ?๋น?? ๋ณ๊ฒ?
     * 6. ์กฐ์?? 1๋ฐ๋น 1๋ฒ? = ?ค๋๋ถ?? ์ฒดํฌ?์ ? ์ง๊น์? * ์กฐ์ ๊ฐ๊ฒ? >> amountPaid??
     * 6-1. ?ค๋ ์ฒดํฌ?์?ด๋ฉด ?๋น?? ๋ณ๊ฒ? ?๋๊ฒ?
     * 6-2. ?์  ?๋ผ?ผ๋ ์ทจ์?๋ฉด amountPaid ๊ฐ์
     * 7. ?์ผ๋ฉ? ?ฌ์๊ฐ? ?์
	 */
	private void setService() {
		System.out.println("๋ถ๊ฐ?๋น?ค๋? ๋ณ๊ฒฝํฉ?๋ค.");
		System.out.println("?ํ?? ๊ฐ์ค?? ?๋ ฅ?์ธ??. [ex: 201~206, 301~306, 401~403, 501~502]: ");
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

		System.out.println(roomNumber + "?? ๋ถ๊ฐ?๋น?? ?ํฉ?: ");

		System.out.println("?ด๋ฆ : " + temp1.getName() + "\n?ธ์?? : " + temp1.getReservation().getNumberPeople()

				+ "\n๋ถ๊ฐ?๋น?? : " + (temp1.getReservation().isBreakfast() ? "์กฐ์" : "?์ ?๋ผ??")

				+ "\n์ด? ?๊ธ : " + temp1.getRecords().getAmountPaid() + "??" + "\n์ฒดํฌ?? : "
				+ temp1.getReservation().getDateCheckIn() + "\n์ฒดํฌ?์ : " + temp1.getReservation().getDateCheckOut());
	}

	// ์ฒดํฌ?ธ์?? ?ค์ 
	/*
	 * HotelBooking?์ ?์ฝ?๋ ๊ฑฐ๋ ๊ฑฐ์ ๊ฐ๊ฒ ?๋ฉด ? ๊ฑด??
	 * ??? ์ฒ์?? ?ด๋ค ?์?ผ๋ก ?์ฝ? ์? ๋ฐ๊ฒ(๋ก๊ทธ?ธ์ฒ??) - ๋น๋?๋ฒํธ ์ฒดํฌ๊น์??? ?๊ณ 
	 */
	private void setCheckInOut() {
		System.out.println("์ฒดํฌ?ธ์ฒด?ฌ์?? ?ค์   ๋ฉ์??");
		/* ?ฌ์๊ฐ์ ์ฒดํฌ??, ์ฒดํฌ?์?? ๋ณ๊ฒฝํ  ?? ?๋ค. */
	}

	// ์ง??
	// ๊ธฐ๋ณธ๊ฐ๊ฒ? ?ค์  : ๋ฐ? ๊ฐ๊ฒ? ?ค์ , ๋ถ๊ฐ?๋น?? ๋ฉ๋ด๋ฅ? ๋ณด์ฌ์ค??.
	private void setPrice() {
		String menu = "";

		while (true) {
			System.out.println("๊ธฐ๋ณธ๊ฐ๊ฒ? ?ค์ : ?ํ?? ๋ฒํธ๋ฅ? ?๋ ฅ?์ธ??.");
			System.out.println("1. ๋ฐ? ๊ฐ๊ฒ? ?ค์ ");
			System.out.println("2. ๋ถ๊ฐ?๋น?? ๊ฐ๊ฒ? ?ค์ ");

			menu = sc.nextLine();

			switch (menu) {
			case "1":
				setRoomPrice();
				return;
			case "2":
				setServicePrice();
				return;
			default:
				System.out.println("๊ธฐ๋ณธ๊ฐ๊ฒ? ?ค์ : 1,2 ์ค์ ? ํ?ด์ฃผ?ธ์");
				break;
			}
		}
	}

	// ์ง??
	// ๋ฐ? ๊ฐ๊ฒ? ?ค์ 
	private void setRoomPrice() {
		int room;

		do {
			try {
				System.out.println("๊ฐ๊ฒฉ์ ๋ฐ๊พธ?? ๊ฐ์ค?? ? ํ?ด์ฃผ?ธ์.");
				System.out.printf("1. %s 2. %s 3. %s\n", myHotel.getRoomInfos()[0].getRoomName(),
						myHotel.getRoomInfos()[1].getRoomName(), myHotel.getRoomInfos()[2].getRoomName());
				;

				room = Integer.parseInt(sc.nextLine());

				if (room >= 1 && room <= 3) {
					System.out.println("[" + myHotel.getRoomInfos()[room - 1].getRoomName() + "๋ฃธ์ ? ํ?์จ?ต๋??.");
					break;
				}
			} catch (Exception e) {
				System.out.println("?ฌ๋ฐ๋ฅ? ๊ฐ์ ?๋ ฅ?ด์ฃผ?ธ์.");
			}
		} while (true);

		exit: while (true) {
			switch (room) {
			case 1:
			case 2:
			case 3:
				System.out.println("๋ณ๊ฒฝํ  ๊ฐ๊ฒฉ์ ?๋ ฅ?ด์ฃผ?ธ์");
				myHotel.getRoomPrices()[room - 1] = Integer.parseInt(sc.nextLine()); // ?ค๋ฅ ์บ์น
				System.out.println("๊ฐ๊ฒฉ์ด ๋ณ๊ฒ? ?์?ต๋??.");
				break exit;
			default:
				System.out.println("?๋ชป ?๋ ฅ?์??ต๋??.");
			}

		}
		System.out.println();
	}

	// ์ง??
	// ๋ถ๊ฐ?๋น?? ๊ฐ๊ฒ? ?ค์ 
	private void setServicePrice() {
		int service;
		String[] servicename = { "Breakfast", "Therapy" }; // ?๊?๋ก? ๊ต์ฒด (?ด๋ฐ๊ฑฐ๋ CustomString) CustomString.BreakfastString

		do {
			try {
				System.out.println("๊ฐ๊ฒฉ์ ๋ฐ๊พธ?? ๋ถ๊ฐ?๋น?ค๋? ? ํ?ด์ฃผ?ธ์.");
				System.out.printf("1. %s 2. %s \n", servicename[0], servicename[1]);
				;

				service = Integer.parseInt(sc.nextLine());

				if (service >= 1 && service <= 2) {
					System.out.println("[" + servicename[service - 1] + "]" + "?? ? ํ?์จ?ต๋??.");
					break;
				}
			} catch (Exception e) {
				System.out.println("?ฌ๋ฐ๋ฅ? ๊ฐ์ ?๋ ฅ?ด์ฃผ?ธ์.");
			}
		} while (true);

		while (true) {
			switch (service) {
			case 1:
				System.out.println("๋ณ๊ฒฝํ  ๊ฐ๊ฒฉ์ ?๋ ต?ด์ฃผ?ธ์");
				myHotel.getServicePrices()[0] = Integer.parseInt(sc.nextLine());
				System.out.println("๊ฐ๊ฒฉ์ด ๋ณ๊ฒ? ?์?ต๋??.");
				return;
			case 2:
				System.out.println("๋ณ๊ฒฝํ  ๊ฐ๊ฒฉ์ ?๋ ต?ด์ฃผ?ธ์");
				myHotel.getServicePrices()[1] = Integer.parseInt(sc.nextLine());
				System.out.println("๊ฐ๊ฒฉ์ด ๋ณ๊ฒ? ?์?ต๋??.");
				return;
			default:
				System.out.println("?๋ชป ?๋ ฅ?์??ต๋??.");
				break;
			}
		}
	}

	// ?๋ณด ๋ณด๊ธฐ : ๋งค์ถ?์ธ
	private void getInfo() {
		String menu = "";

		while (true) {
			System.out.println("?๋ณด ๋ณด๊ธฐ: ?ํ?? ๋ฒํธ๋ฅ? ?๋ ฅ?์ธ??.");
			System.out.println("1. ๋งค์ถ ?์ธ");
			System.out.println("2. ?์ ?๋ณด ?์ธ");
			System.out.println("3. ?ฌ์ ?๋ณด ?์ธ");

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
				System.out.println("๊ธฐ๋ณธ๊ฐ๊ฒ? ?ค์ : 1,2,3 ์ค์ ? ํ?ด์ฃผ?ธ์");
				break;
			}
		}
	}

	// ๋งค์ถ ๋ณด๊ธฐ
	private void getSales() {
		System.out.println("?์ฌ ?ธํ ๋งค์ถ - " + myHotel.getSales() + "?? ?๋??.");
	}

	// ?ธ๋ฆผ
	// ?์ ?๋ณด ๋ณด๊ธฐ
	public void getMemberInfo() {
		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println(" ?ด๋ฆ :" + myHotel.getMembers().get(key).getName() + " ?๋?์ผ : "
					+ myHotel.getMembers().get(key).getBirthday() + " ?ํ๋ฒํธ : "
					+ myHotel.getMembers().get(key).getPhoneNumber() + " VIP : true" + " ?ธํ ?ด์ฉ ์ด? ๊ธ์ก : "
					+ myHotel.getMembers().get(key).getRecords().getTotalPaid() + "??");
		}
	}

	// ์ง??, ?ธ๋ฆผ
	// ?ฌ์ ๊ธฐ๋ก ๋ณด๊ธฐ : ?ธํ?์ฝ?์ฒด ๊ธฐ๋ก?? ๊ฐ?ธ์???(?ด๋?? ๊ฐ?ธ์ค?์ง ๋ชจ๋ฆ) ์ถ๋ ฅ?๋ค.
	/*
	 * ? ์ง๋ณ๋ก ์ฒดํฌ?ธ๊ณผ ์ฒดํฌ?์?? ??ฅ์ด ?๋ค
	 * CheckIn20190315.info, CheckOut20190315.info
	 * ?์ค?? ? ์ง๋ฅ? ๋ถ๋ฌ?ค๋ฉด ๊ทธ๋  ์ฒดํฌ?ธํ ?ฌ๋๊ณ? ์ฒดํฌ?์?? ?ฌ๋?? ?๊ณ 
	 * ?? ?ฌ๋?? ?ด๋ป๊ฒ? ๊ฐ์ค?? ?ด์ฉ?๋์ง ๋ถ๋ฌ?จ๋ค
	 */
	public void getRecord() {
		
		/*
		 * ?์ธ?? ? ์ง ?๋ ฅ ex)20190315
		 * FileInputStream fis = new FileInputStream(CustomString.PATH_DIRECTORY + 20190315 + ".info");
		 * ObjectInputStream in = new ObjectInputStream(fis);
		 */

		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();

//			//์ฒดํฌ?์ ?์ธ
//			if(myHotel.getMembers().get(key).getRecords().getDateCheckOut() > new Date()) {
//				
//			}

			System.out.println("์ฒดํฌ??: " + myHotel.getMembers().get(key).getRecords().getDateCheckin() + "์ฒดํฌ?์: "
					+ myHotel.getMembers().get(key).getRecords().getDateCheckOut());
		}
	}
	
	/*
	 * ?ธํ ๋งค๋?๋ฅ? ?คํ?๋ฉด
	 * ?ธํ ๋งค๋?๊ฐ ?ค๋ ? ์ง๋ฅ? ๊ฐ?ธ์จ??
	 * ๋ชจ๋  ๋ฐฉ์ ์ฒดํฌ?ด์ ?ค๋ ์ฒดํฌ?์?? ?ฌ๋?? ?? ๊ฐ?ธ์จ??
	 * Room?์ Guests?์ ?? ?ฌ๋?ค์ ?? ?๋ค
	 * ?? ?๋ฉด?? ?? ?ฌ๋?ค์ amountPaid๋งํผ sales?? ?ํด์ค??
	 * ?? ?ฌ๋?ค์ reservation ?๋ณด๋ฅ? record?? ?ด์์ฃผ๊ณ  reservation? null๋ก? ๋ง๋ค๊ณ?
	 * ?ค๋ ์ฒดํฌ?ธํ?? ?ฌ๋๊ณ? ?ค๋ ์ฒดํฌ?์ ?๋ ?ฌ๋?? ?๋ณด๋ฅ? ?์ผ๋ก? ???
	 */
}