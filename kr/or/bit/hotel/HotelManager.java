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

	// ?ì„±??!
	public HotelManager() {
		this.sc = new Scanner(System.in);
	}

	public void run() {
		loadHotel();
		myHotel.setToday(LocalDate.now());
		// ?¬ê¸°?? ?ë™ ì²˜ë¦¬?˜ëŠ” ?¨ìˆ˜
		setPrice();
		saveHotel();
	}

	// ì§€??, ?¸ë¦¼
	// ?¸í…” ?¬ì´ì¦? ?¤ì •
	public Hotel setHotel() {
		while (true) {
			System.out.println("?¸í…”?¬ì´ì¦? ?…ë ¥[?Œí˜•, ì¤‘í˜•, ?€??: ");
			String hotelSize = sc.nextLine();

			switch (hotelSize) {
			case "?Œí˜•":
				System.out.println("?Œí˜• ?¸í…”?? ?ì„±?˜ì—ˆ??.");
				// saveHotel();
				return this.myHotel = new SmallHotel();
			case "ì¤‘í˜•":
				System.out.println("ì¤‘í˜• ?¸í…”?? ?ì„±?˜ì—ˆ??.");
				// saveHotel();
				return this.myHotel = new MediumHotel();
			case "?€??":
				System.out.println("?€?? ?¸í…”?? ?ì„±?˜ì—ˆ??.");
				// saveHotel();
				return this.myHotel = new LargeHotel();
			default:
				System.out.println("?Œí˜•, ì¤‘í˜•, ?€?? ì¤‘ì— ? íƒ?˜ì„¸??.");
				break;
			}
		}
	}

	private void loadHotel() {
		file = new File(CustomString.PATH_HOTEL);
		if (!file.exists()) {
			System.out.println("?¸í…” ?•ë³´ê°€ ì¡´ì¬?˜ì? ?ŠìŠµ?ˆë‹¤.");
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
	 * ?¸í…” ?•ë³´ ?€?¥í•˜ê¸?
	 * 
	 * ë³€ê²½ëœ ?¸í…” ?•ë³´ë¥? ?€??
	 * 
	 * ?´ë”ê°€ ?†ìœ¼ë©? ?ë™?¼ë¡œ ?´ë” ?ì„± ?? ?€??
	 * 
	 * ?‘ì„±?? : ?¤ì¢…??
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

	// ê°ì‹¤ê´€ë¦? : ?¬ìˆ™ê°?, ë¶€ê°€?œë¹„??, ì²´í¬?¸ì•„?ƒì„ ê´€ë¦¬í•˜?? ë©”ë‰´ë¥? ë³´ì—¬ì¤€??.
	private void roomManage() {
		loadHotel();
		String menu = "";

		while (true) {
			System.out.println("ê°ì‹¤ê´€ë¦?: ?í•˜?? ë²ˆí˜¸ë¥? ?…ë ¥?˜ì„¸??.");
			System.out.println("1. ?¬ìˆ™ê°? ?•ë³´ ?•ì¸");
			System.out.println("2. ë¶€ê°€?œë¹„?? ë³€ê²?");
			System.out.println("3. ì²´í¬??, ì²´í¬?„ì›ƒ ê´€ë¦?");

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
				System.out.println("ê°ì‹¤ê´€ë¦?: 1,2,3 ì¤‘ì— ? íƒ?´ì£¼?¸ìš”");
				break;
			}
		}
	}

	// ?¬ìˆ™ê°? ?•ë³´
	/*
	 * 1. IDë¥? ê°€?¸ì???
     * 2. IDë¥? ?µí•´?? ?Œì›?•ë³´ë¡? ê°„ë‹¤?Œì—
     * 3. ?Œì› ?•ë³´?ì„œ ?ˆì•½?•ë³´ë¥? ê°€?¸ì˜¤ê³?
     * 4. ì²´í¬?? ? ì§œ <= ?¤ëŠ˜ ? ì§œ <= ì²´í¬?„ì›ƒ ? ì§œ ?´ëŸ° ?¬ëŒ?? ì°¾ì•„??
     * 5. ?´ì‚¬?? ?•ë³´ë§? ë³´ì—¬ì£¼ê²Œ
     * 6. ?†ìœ¼ë©? ?¬ìˆ™ê°? ?†ìŒ
	 */
	public void getGuest() {
		System.out.println("?¬ìˆ™ê°? ?•ë³´ë¥? ?•ì¸?©ë‹ˆ??.");
		// ex ?˜ì •
		System.out.println("?í•˜?? ê°ì‹¤?? ?…ë ¥?˜ì„¸??. [ex: 201~206, 301~306, 401~403, 501~502]: ");
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

		System.out.println("?´ë¦„ : " + temp1.getName() + "\n?¸ì›?? : " + temp1.getReservation().getNumberPeople()

				+ "\në¶€ê°€?œë¹„?? : " + (temp1.getReservation().isBreakfast() ? "ì¡°ì‹" : "?„ì‹ ?Œë¼??")

				+ "\nì´? ?”ê¸ˆ : " + temp1.getReservation().getAmountPaid() + "??" + "\nì²´í¬?? : "
				+ temp1.getReservation().getDateCheckIn().getCheckDate() + "\nì²´í¬?„ì›ƒ : " + temp1.getReservation().getDateCheckOut().getCheckDate());

	}

	// ë¶€ê°€?œë¹„?? ë³€ê²?
	/*
	 * 1. IDë¥? ê°€?¸ì???
     * 2. IDë¥? ?µí•´?? ?Œì›?•ë³´ë¡? ê°„ë‹¤?Œì—
     * 3. ?Œì› ?•ë³´?ì„œ ?ˆì•½?•ë³´ë¥? ê°€?¸ì˜¤ê³?
     * 4. ì²´í¬?? ? ì§œ <= ?¤ëŠ˜ ? ì§œ <= ì²´í¬?„ì›ƒ ? ì§œ ?´ëŸ° ?¬ëŒ?? ì°¾ì•„??
     * 5. ?´ì‚¬?Œì˜ ?œë¹„?? ë³€ê²?
     * 6. ì¡°ì‹?? 1ë°•ë‹¹ 1ë²? = ?¤ëŠ˜ë¶€?? ì²´í¬?„ì›ƒ ? ì§œê¹Œì? * ì¡°ì‹ ê°€ê²? >> amountPaid??
     * 6-1. ?¤ëŠ˜ ì²´í¬?„ì›ƒ?´ë©´ ?œë¹„?? ë³€ê²? ?ˆë˜ê²?
     * 6-2. ?„ì‹  ?Œë¼?¼ëŠ” ì·¨ì†Œ?˜ë©´ amountPaid ê°ì†Œ
     * 7. ?†ìœ¼ë©? ?¬ìˆ™ê°? ?†ìŒ
	 */
	private void setService() {
		System.out.println("ë¶€ê°€?œë¹„?¤ë? ë³€ê²½í•©?ˆë‹¤.");
		System.out.println("?í•˜?? ê°ì‹¤?? ?…ë ¥?˜ì„¸??. [ex: 201~206, 301~306, 401~403, 501~502]: ");
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

		System.out.println(roomNumber + "?? ë¶€ê°€?œë¹„?? ?í™©?€: ");

		System.out.println("?´ë¦„ : " + temp1.getName() + "\n?¸ì›?? : " + temp1.getReservation().getNumberPeople()

				+ "\në¶€ê°€?œë¹„?? : " + (temp1.getReservation().isBreakfast() ? "ì¡°ì‹" : "?„ì‹ ?Œë¼??")

				+ "\nì´? ?”ê¸ˆ : " + temp1.getRecords().getAmountPaid() + "??" + "\nì²´í¬?? : "
				+ temp1.getReservation().getDateCheckIn() + "\nì²´í¬?„ì›ƒ : " + temp1.getReservation().getDateCheckOut());
	}

	// ì²´í¬?¸ì•„?? ?¤ì •
	/*
	 * HotelBooking?ì„œ ?ˆì•½?˜ëŠ” ê±°ë‘ ê±°ì˜ ê°™ê²Œ ?˜ë©´ ? ê±´??
	 * ?€?? ì²˜ìŒ?? ?´ë–¤ ?Œì›?¼ë¡œ ?ˆì•½? ì? ë°›ê²Œ(ë¡œê·¸?¸ì²˜??) - ë¹„ë?ë²ˆí˜¸ ì²´í¬ê¹Œì??? ?ê³ 
	 */
	private void setCheckInOut() {
		System.out.println("ì²´í¬?¸ì²´?¬ì•„?? ?¤ì •  ë©”ì„œ??");
		/* ?¬ìˆ™ê°ì˜ ì²´í¬??, ì²´í¬?„ì›ƒ?? ë³€ê²½í•  ?? ?ˆë‹¤. */
	}

	// ì§€??
	// ê¸°ë³¸ê°€ê²? ?¤ì • : ë°? ê°€ê²? ?¤ì •, ë¶€ê°€?œë¹„?? ë©”ë‰´ë¥? ë³´ì—¬ì¤€??.
	private void setPrice() {
		String menu = "";

		while (true) {
			System.out.println("ê¸°ë³¸ê°€ê²? ?¤ì •: ?í•˜?? ë²ˆí˜¸ë¥? ?…ë ¥?˜ì„¸??.");
			System.out.println("1. ë°? ê°€ê²? ?¤ì •");
			System.out.println("2. ë¶€ê°€?œë¹„?? ê°€ê²? ?¤ì •");

			menu = sc.nextLine();

			switch (menu) {
			case "1":
				setRoomPrice();
				return;
			case "2":
				setServicePrice();
				return;
			default:
				System.out.println("ê¸°ë³¸ê°€ê²? ?¤ì •: 1,2 ì¤‘ì— ? íƒ?´ì£¼?¸ìš”");
				break;
			}
		}
	}

	// ì§€??
	// ë°? ê°€ê²? ?¤ì •
	private void setRoomPrice() {
		int room;

		do {
			try {
				System.out.println("ê°€ê²©ì„ ë°”ê¾¸?? ê°ì‹¤?? ? íƒ?´ì£¼?¸ìš”.");
				System.out.printf("1. %s 2. %s 3. %s\n", myHotel.getRoomInfos()[0].getRoomName(),
						myHotel.getRoomInfos()[1].getRoomName(), myHotel.getRoomInfos()[2].getRoomName());
				;

				room = Integer.parseInt(sc.nextLine());

				if (room >= 1 && room <= 3) {
					System.out.println("[" + myHotel.getRoomInfos()[room - 1].getRoomName() + "ë£¸ì„ ? íƒ?˜ì…¨?µë‹ˆ??.");
					break;
				}
			} catch (Exception e) {
				System.out.println("?¬ë°”ë¥? ê°’ì„ ?…ë ¥?´ì£¼?¸ìš”.");
			}
		} while (true);

		exit: while (true) {
			switch (room) {
			case 1:
			case 2:
			case 3:
				System.out.println("ë³€ê²½í•  ê°€ê²©ì„ ?…ë ¥?´ì£¼?¸ìš”");
				myHotel.getRoomPrices()[room - 1] = Integer.parseInt(sc.nextLine()); // ?¤ë¥˜ ìºì¹˜
				System.out.println("ê°€ê²©ì´ ë³€ê²? ?˜ì—ˆ?µë‹ˆ??.");
				break exit;
			default:
				System.out.println("?˜ëª» ?…ë ¥?˜ì??µë‹ˆ??.");
			}

		}
		System.out.println();
	}

	// ì§€??
	// ë¶€ê°€?œë¹„?? ê°€ê²? ?¤ì •
	private void setServicePrice() {
		int service;
		String[] servicename = { "Breakfast", "Therapy" }; // ?œê?ë¡? êµì²´ (?´ëŸ°ê±°ëŠ” CustomString) CustomString.BreakfastString

		do {
			try {
				System.out.println("ê°€ê²©ì„ ë°”ê¾¸?? ë¶€ê°€?œë¹„?¤ë? ? íƒ?´ì£¼?¸ìš”.");
				System.out.printf("1. %s 2. %s \n", servicename[0], servicename[1]);
				;

				service = Integer.parseInt(sc.nextLine());

				if (service >= 1 && service <= 2) {
					System.out.println("[" + servicename[service - 1] + "]" + "?? ? íƒ?˜ì…¨?µë‹ˆ??.");
					break;
				}
			} catch (Exception e) {
				System.out.println("?¬ë°”ë¥? ê°’ì„ ?…ë ¥?´ì£¼?¸ìš”.");
			}
		} while (true);

		while (true) {
			switch (service) {
			case 1:
				System.out.println("ë³€ê²½í•  ê°€ê²©ì„ ?…ë µ?´ì£¼?¸ìš”");
				myHotel.getServicePrices()[0] = Integer.parseInt(sc.nextLine());
				System.out.println("ê°€ê²©ì´ ë³€ê²? ?˜ì—ˆ?µë‹ˆ??.");
				return;
			case 2:
				System.out.println("ë³€ê²½í•  ê°€ê²©ì„ ?…ë µ?´ì£¼?¸ìš”");
				myHotel.getServicePrices()[1] = Integer.parseInt(sc.nextLine());
				System.out.println("ê°€ê²©ì´ ë³€ê²? ?˜ì—ˆ?µë‹ˆ??.");
				return;
			default:
				System.out.println("?˜ëª» ?…ë ¥?˜ì??µë‹ˆ??.");
				break;
			}
		}
	}

	// ?•ë³´ ë³´ê¸° : ë§¤ì¶œ?•ì¸
	private void getInfo() {
		String menu = "";

		while (true) {
			System.out.println("?•ë³´ ë³´ê¸°: ?í•˜?? ë²ˆí˜¸ë¥? ?…ë ¥?˜ì„¸??.");
			System.out.println("1. ë§¤ì¶œ ?•ì¸");
			System.out.println("2. ?Œì› ?•ë³´ ?•ì¸");
			System.out.println("3. ?¬ìˆ™ ?•ë³´ ?•ì¸");

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
				System.out.println("ê¸°ë³¸ê°€ê²? ?¤ì •: 1,2,3 ì¤‘ì— ? íƒ?´ì£¼?¸ìš”");
				break;
			}
		}
	}

	// ë§¤ì¶œ ë³´ê¸°
	private void getSales() {
		System.out.println("?„ì¬ ?¸í…” ë§¤ì¶œ - " + myHotel.getSales() + "?? ?…ë‹ˆ??.");
	}

	// ?¸ë¦¼
	// ?Œì› ?•ë³´ ë³´ê¸°
	public void getMemberInfo() {
		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			System.out.println(" ?´ë¦„ :" + myHotel.getMembers().get(key).getName() + " ?ë…„?”ì¼ : "
					+ myHotel.getMembers().get(key).getBirthday() + " ?„í™”ë²ˆí˜¸ : "
					+ myHotel.getMembers().get(key).getPhoneNumber() + " VIP : true" + " ?¸í…” ?´ìš© ì´? ê¸ˆì•¡ : "
					+ myHotel.getMembers().get(key).getRecords().getTotalPaid() + "??");
		}
	}

	// ì§€??, ?¸ë¦¼
	// ?¬ìˆ™ ê¸°ë¡ ë³´ê¸° : ?¸í…”?ˆì•½?„ì²´ ê¸°ë¡?? ê°€?¸ì???(?´ë””?? ê°€?¸ì˜¤?”ì§„ ëª¨ë¦„) ì¶œë ¥?œë‹¤.
	/*
	 * ? ì§œë³„ë¡œ ì²´í¬?¸ê³¼ ì²´í¬?„ì›ƒ?? ?€?¥ì´ ?œë‹¤
	 * CheckIn20190315.info, CheckOut20190315.info
	 * ?˜ì¤‘?? ? ì§œë¥? ë¶ˆëŸ¬?¤ë©´ ê·¸ë‚  ì²´í¬?¸í•œ ?¬ëŒê³? ì²´í¬?„ì›ƒ?? ?¬ëŒ?? ?ˆê³ 
	 * ?? ?¬ëŒ?? ?´ë–»ê²? ê°ì‹¤?? ?´ìš©?ˆëŠ”ì§€ ë¶ˆëŸ¬?¨ë‹¤
	 */
	public void getRecord() {
		
		/*
		 * ?•ì¸?? ? ì§œ ?…ë ¥ ex)20190315
		 * FileInputStream fis = new FileInputStream(CustomString.PATH_DIRECTORY + 20190315 + ".info");
		 * ObjectInputStream in = new ObjectInputStream(fis);
		 */

		Iterator<String> it = myHotel.getMembers().keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();

//			//ì²´í¬?„ì›ƒ ?•ì¸
//			if(myHotel.getMembers().get(key).getRecords().getDateCheckOut() > new Date()) {
//				
//			}

			System.out.println("ì²´í¬??: " + myHotel.getMembers().get(key).getRecords().getDateCheckin() + "ì²´í¬?„ì›ƒ: "
					+ myHotel.getMembers().get(key).getRecords().getDateCheckOut());
		}
	}
	
	/*
	 * ?¸í…” ë§¤ë‹ˆ?€ë¥? ?¤í–‰?˜ë©´
	 * ?¸í…” ë§¤ë‹ˆ?€ê°€ ?¤ëŠ˜ ? ì§œë¥? ê°€?¸ì˜¨??
	 * ëª¨ë“  ë°©ì„ ì²´í¬?´ì„œ ?¤ëŠ˜ ì²´í¬?„ì›ƒ?? ?¬ëŒ?? ?? ê°€?¸ì˜¨??
	 * Room?ì„œ Guests?ì„œ ?? ?¬ëŒ?¤ì„ ?? œ?œë‹¤
	 * ?? œ?˜ë©´?? ?? ?¬ëŒ?¤ì˜ amountPaidë§Œí¼ sales?? ?”í•´ì¤€??
	 * ?? ?¬ëŒ?¤ì˜ reservation ?•ë³´ë¥? record?? ?´ì•„ì£¼ê³  reservation?€ nullë¡? ë§Œë“¤ê³?
	 * ?¤ëŠ˜ ì²´í¬?¸í•˜?? ?¬ëŒê³? ?¤ëŠ˜ ì²´í¬?„ì›ƒ ?˜ëŠ” ?¬ëŒ?? ?•ë³´ë¥? ?Œì¼ë¡? ?€??
	 */
}