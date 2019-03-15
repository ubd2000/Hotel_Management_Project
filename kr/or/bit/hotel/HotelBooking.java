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
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HotelBooking {
	private boolean loginCheck; // 濡쒓렇좊Т
	private Member memberLoggedIn; // 濡쒓렇몃맂 뚯썝
	private Hotel hotel;
	private Scanner sc;
	private File file;
	private FileInputStream fis;
	private ObjectInputStream in;
	private FileOutputStream fos;
	private ObjectOutputStream out;
	
	public HotelBooking() {
		sc = new Scanner(System.in);
	}
	public void run() {
		loginCheck = false;
		memberLoggedIn = null;
		loadHotel();
		printMenu();
	}

	/*
	 * 명뀛 뺣낫 遺덈윭ㅺ린
	 * 
	 * 꾨줈洹몃옩먯꽌 쒖슜명뀛뺣낫瑜遺덈윭ㅻ뒗 ⑥닔
	 * 
	 * 명뀛 뺣낫 뚯씪놁쑝硫遺덈윭명뀛 뺣낫媛 놁뼱 꾨줈洹몃옩 媛뺤젣 醫낅즺
	 * 
	 * 묒꽦: ㅼ쥌
	 */
	private void loadHotel() {
		file = new File(CustomString.PATH_HOTEL);
		if (!file.exists()) {
			System.out.println("명뀛 뺣낫媛 議댁옱섏 딆뒿덈떎.");
			System.out.println("꾨줈洹몃옩醫낅즺⑸땲");
			System.exit(0);
		}
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			hotel = (Hotel) in.readObject();
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
	 * 명뀛 뺣낫 ν븯湲
	 * 
	 * 蹂寃쎈맂 명뀛 뺣낫瑜
	 * 
	 * 대뜑媛 놁쑝硫먮룞쇰줈 대뜑 앹꽦 
	 * 
	 * 묒꽦: ㅼ쥌
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
			out.writeObject(hotel);
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

	private void getRoomInfo() { // 媛앹떎뺣낫蹂닿린
		
		for (int i = 0; i < hotel.getRoomInfos().length; i++) {
			String kitchen = "";
			if (hotel.getRoomInfos()[i].isKitchen()) {
				kitchen = ";
			} else {
				kitchen = "臾;
			}
			System.out.println("  [諛⑹씠由 : " + hotel.getRoomInfos()[i].getRoomName() + "猷 " + "  [媛寃 : "
					+ CustomString.putComma(hotel.getRoomPrices()[i]) + " " + "  [湲곕낯몄썝] : "
					+ hotel.getRoomInfos()[i].getDefaultNumberPeople() + "紐 " + "  [理쒕몄썝] : "
					+ hotel.getRoomInfos()[i].getMaxNumberPeople() + "紐 " + "  [붿옣 : "
					+ hotel.getRoomInfos()[i].getNumberBathroom() + "媛 " + "  [移⑤] : "
					+ hotel.getRoomInfos()[i].getNumberBed() + "媛 " + "  [二쇰갑] : " + kitchen);
		}
		
	}

	public void reserveRoom() { // 媛앹떎덉빟
		if (memberLoggedIn.getReservation() != null) {
			System.out.println("대 덉빟);
			return;
		}
		Reservation r = new Reservation();
		setDate(r);
		setRoom(r);
		setNumberPeople(r);
		setService(r);
		System.out.println("珥湲덉븸 : " + r.getAmountPaid());
		memberLoggedIn.setReservation(r);
		System.out.println("덉빟꾨즺먯뒿덈떎.");
		
	}

	public void setDate(Reservation r) { // 좎쭨 좏깮
		LocalDate today = LocalDate.now();
		HotelDate dateCheckIn, dateCheckOut;
		while (true) {
			// TODO : 뺢퇋쒗쁽앹쑝濡щ㎎ 쒗븳
			System.out.println("泥댄겕좎쭨瑜낅젰댁＜몄슂. (20190314 媛숈씠 낅젰댁＜몄슂.)");
			String checkIn = sc.nextLine();
			dateCheckIn = new HotelDate(checkIn);
			if (dateCheckIn.getCheckDate().isBefore(today)) {
				System.out.println("좏깮 遺덇ν븳 좎쭨낅땲 ㅼ떆 낅젰댁＜몄슂.");
			} else {
				break;
			}
		}

		while (true) {
			// TODO : 뺢퇋쒗쁽앹쑝濡щ㎎ 쒗븳
			System.out.println("泥댄겕꾩썐 좎쭨瑜낅젰댁＜몄슂. (20190314 媛숈씠 낅젰댁＜몄슂.)");
			String checkOut = sc.nextLine();
			dateCheckOut = new HotelDate(checkOut);
			if (dateCheckOut.getCheckDate().isBefore(today)
					|| dateCheckOut.getCheckDate().isBefore(dateCheckIn.getCheckDate())
					|| dateCheckOut.getCheckDate().isEqual(dateCheckIn.getCheckDate())) {
				System.out.println("좏깮 遺덇ν븳 좎쭨낅땲 ㅼ떆 낅젰댁＜몄슂.");
			} else {
				break;
			}
		}

		System.out.println("泥댄겕좎쭨 : " + dateCheckIn.getCheckDate());
		System.out.println("泥댄겕꾩썐 좎쭨 : " + dateCheckOut.getCheckDate());

		r.setDateCheckIn(dateCheckIn);
		r.setDateCheckOut(dateCheckOut);
	}

	public void setRoom(Reservation r) { // 媛앹떎 좏깮
		Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());
		System.out.println("덉빟 媛媛앹떎 踰덊샇");
		for (int i = 0; i < hotel.getRooms().size(); i++) {
			for (int j = 0; j < hotel.getRooms().get(i).size(); j++) {
				boolean canReserve = true;
				for (String guestId : hotel.getRooms().get(i).get(j).getGuests()) {
					if (hotel.getRooms().get(i).get(j).getGuests().size() == 0) {
						break;
					}
					LocalDate checkIn = hotel.getMembers().get(guestId).getReservation().getDateCheckIn()
							.getCheckDate();
					LocalDate checkOut = hotel.getMembers().get(guestId).getReservation().getDateCheckOut()
							.getCheckDate();
					// TODO : 媛숈 좎쭨щ룄 紐삵븯寃議곌굔 遺
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
					System.out.print((i + 2) + "0" + (j + 1) + "");
				}
			}
			System.out.println();
		}

		System.out.println("덉빟諛踰덊샇瑜낅젰댁＜몄슂");
		String roomNumber = sc.nextLine(); // 202
		r.setRoomnumber(roomNumber);
		// roomNumber濡room媛몄샂
		char floor = roomNumber.charAt(0);
		char number = roomNumber.charAt(roomNumber.length() - 1);
		Room room = hotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49
		for(int i = 0 ; i < hotel.getRooms().size(); i ++) {         //꾩쭅 媛숈諛⑸쾲諛쏄린 섏젙以// 吏꾪샇
			for( int j = 0 ; j < hotel.getRooms().get(i).size(); j++) {
				if(hotel.getRooms().get(i).get(j) != null) {
					System.out.println("대 덉빟諛踰덊샇낅땲");
				}else {
					return;
				}
			}
		}
		room.getGuests().add(memberLoggedIn.getId());
		r.setAmountPaid(r.getAmountPaid() + (hotel.getRoomPrices()[0]*diff.getDays()));
		System.out.println("숇컯쇱닔 [" + diff.getDays() + "]\n 숇컯 붽툑 [" + CustomString.putComma(r.getAmountPaid()) + "]낅땲");
		r.setRoom(room);
		
	}
    /*
     * 蹂寃쎌궗
     * 숇컯쇱닔 留뚰겮 異붽 몄썝鍮꾩슜 異붽
     * 
     * 묒꽦: 뺤쭊
     */
	public void setNumberPeople(Reservation r) { // 몄썝 ㅼ젙
		// r.setRoom(new SuiteRoom());
		int numberPeople;
		int defaultNumberPeople = r.getRoom().getDefaultNumberPeople();
		int maxNumberPeople = r.getRoom().getMaxNumberPeople();
		Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());

		while (true) {
			System.out.println("숇컯몄썝낅젰댁＜몄슂.");
			System.out.printf("좏깮섏떊 諛⑹쓽 湲곕낯 몄썝 %d紐 理쒕 몄썝 %d紐낆엯덈떎.\r\n", defaultNumberPeople, maxNumberPeople);
			System.out.println("몄썝 異붽 50,000먯씠 異붽⑸땲");
			numberPeople = Integer.parseInt(sc.nextLine());
			if (numberPeople > maxNumberPeople) {
				System.out.println("理쒕 몄썝珥덇낵덉뒿덈떎.");
				System.out.println("ㅼ떆 낅젰댁＜몄슂.");
			} else if (this.numberPeople > defaultNumberPeople) {
				System.out.println("異붽 붽툑 [" + CustomString.putComma(((numberPeople - defaultNumberPeople)*50000 )* diff.getDays()) + "]먯엯덈떎.");
				break;
			} else {
				break;
			}
		}
		
		r.setNumberPeople(numberPeople);
		if (numberPeople > defaultNumberPeople) {
			r.setAmountPaid(r.getAmountPaid() + ((numberPeople - defaultNumberPeople)*50000 )* diff.getDays());
		}
	}
	
	/*
	 * 蹂寃쎌궗
	 * 
	 * 遺媛쒕퉬붽툑숇컯 쇱닔留욎떠 쒖떆 ( 몄썝 ы븿 )
	 * 뚮씪쇰뒗 紐뉗씪 숇컯섎뜕 1뚮쭔 諛쏅뒗
	 * 
	 * 묒꽦: 뺤쭊
	 */
	public void setService(Reservation r) { // 遺媛쒕퉬좏깮
		Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());
		long breakfast = diff.getDays() * hotel.getBreakfast();
		System.out.println("遺媛 쒕퉬ㅻ 좏깮댁＜몄슂.");
		System.out.println("議곗떇 50,000 꾩떊 뚮씪쇰뒗 300,000먯엯덈떎.");

		while (true) {
			System.out.println("1. 議곗떇 2. 꾩떊 뚮씪3. 4. 좏깮 );
			String service = sc.nextLine();
			switch (service) {
			case "1":
				System.out.println("議곗떇좏깮섏뀲듬땲");
				r.setBreakfast(true);
				long breakfast = (Number.breakfast*this.numberPeople) * diff.getDays();
				System.out.println("異붽붽툑 : " + CustomString.putComma(breakfast) + "낅땲");
				r.setAmountPaid(r.getAmountPaid() + breakfast);
				return;
			case "2":
				System.out.println("꾩떊 뚮씪쇰 좏깮섏뀲듬땲");
				r.setTherapy(true);
				long therapy = Number.therapy * this.numberPeople ;
				System.out.println("異붽붽툑 : " + CustomString.putComma(therapy)+ "낅땲");
				r.setAmountPaid(r.getAmountPaid() + therapy);
				
				return;
			case "3":
				System.out.println("議곗떇怨꾩떊 뚮씪쇰 좏깮섏뀲듬땲");
				r.setBreakfast(true);
				r.setTherapy(true);
				breakfast = (Number.breakfast*this.numberPeople) * diff.getDays();
				therapy = Number.therapy * this.numberPeople;
				System.out.println("議곗떇 異붽붽툑 : [" + money.putComma(breakfast) + "]낅땲");
				System.out.println("뚮씪異붽붽툑 : [" + money.putComma(therapy)+ "]낅땲");
				r.setAmountPaid(r.getAmountPaid() + breakfast);
				r.setAmountPaid(r.getAmountPaid() + therapy);

				return;
			case "4":
				System.out.println("쒕퉬ㅻ 좏깮섏 딆쑝⑥뒿덈떎.");
				return;
			default:
				System.out.println("섎せ 낅젰섏뀲듬땲");
			}
		}
	}
	/*
	 * 蹂寃쎌궗
	 *  
	 *  덉빟 뺤씤 湲곕뒫덉빟 蹂寃 덉빟 痍⑥냼 湲곕뒫異붽
	 * 
	 * 묒꽦: 뺤쭊
	 */
	
	public void getReservation() { // 덉빟 뺤씤
		System.out.println(memberLoggedIn.getReservation());
		
        System.out.println("                                 );
        System.out.println("         1. 덉빟 蹂寃쏀븯湲);
        System.out.println();
        System.out.println("         2. 덉빟 痍⑥냼섍린");
        System.out.println();
        System.out.println("         3.  뚯븘媛湲);
        System.out.println("                                 );
        String select = sc.nextLine();
		switch (select) {
		case "1":
			System.out.println("덉빟蹂寃쏀빀덈떎.");
			changeReservation();
			break;
		case "2":
			System.out.println("덉빟痍⑥냼⑸땲");
			cancelReservation();
		case "3":
			System.out.println("硫붿씤붾㈃쇰줈 뚯븘媛묐땲");
			break;
		default:
			System.out.println("섎せ 낅젰 섏듬땲");
			getReservation();
			break;
		}
	}

	/*
	 * 덉빟 痍⑥냼 ⑥닔
	 * 
	 * 뚯썝덉빟諛⑹씠 媛吏怨덈뒗 덉빟뚯썝 뺣낫먯꽌 뚯썝 젣 뚯썝덉빟 곹깭 null濡꾪솚
	 * 
	 * 묒꽦: ㅼ쥌
	 */
	public void cancelReservation() { // 덉빟 痍⑥냼
		
		memberLoggedIn.getReservation().getRoom().getGuests().remove(memberLoggedIn);
		memberLoggedIn.setReservation(null);
		System.out.println("湲곗〈 덉빟痍⑥냼섏뿀듬땲");
		
	}

	/*
	 * 덉빟 蹂寃⑥닔
	 * 
	 * 덉빟 痍⑥냼 덉빟섍린 ㅽ뻾
	 * 
	 * 묒꽦: ㅼ쥌
	 */
	public void changeReservation() { // 덉빟 蹂寃
		
		cancelReservation();
		reserveRoom();
		
	}
    /*
     * // 뚯썝 媛//
     * 
     * 뚯썝 媛먮룞 濡쒓렇
     * 
     * 묒꽦: 뺤쭊
     */
 
	public void signUp() { 
		
		String id, name, password, phoneNumber, birthday;
		while(true) {
		System.out.println("뚯썝媛덉감");
		System.out.println("대쫫낅젰二쇱꽭");
		name = sc.nextLine();
		if(name.length() > 10) {
			System.out.println("대쫫덈Т 源곷땲");
		}else if(!name.matches("^[媛-*$")) {
			System.out.println("쒓留낅젰좎닔 덉뒿덈떎. ( 먯쓬 遺덇 )");
		}else {
			break;
		}
	}
		while (true) {
			System.out.println("꾩씠붾 낅젰댁＜몄슂. (4댁긽  10대궡)");
			id = sc.nextLine();
			if (id.length() > 10 || id.length() < 4) {
				JOptionPane.showMessageDialog(null, "ID湲몄씠瑜뺤씤댁＜몄슂.");
				System.out.println("ID湲몄씠媛 щ컮瑜댁 딆뒿덈떎.");
			} else if (!id.matches("\\p{Alnum}+")) {
				JOptionPane.showMessageDialog(null, "ID뺤떇щ컮瑜댁 딆뒿덈떎.");
				System.out.println("곸뼱,レ옄留낅젰댁＜몄슂.");
			} else if (hotel.getMembers().containsKey(id)) {
				System.out.println("숈씪ID媛 議댁옱⑸땲");
				System.out.println("ㅻⅨ 꾩씠붾 낅젰댁＜몄슂.");
			} else {
				break;
			}
		}
		while (true) {
			System.out.println("鍮꾨踰덊샇瑜낅젰댁＜몄슂. (6먯씠10대궡)");
			password = sc.nextLine();
			if (password.length() > 10 || password.length() < 6) {
				JOptionPane.showMessageDialog(null, "鍮꾨踰덊샇湲몄씠瑜뺤씤댁＜몄슂.");
				System.out.println("PWD湲몄씠媛 щ컮瑜댁 딆뒿덈떎.");
			} else if (!password.matches("\\p{Alnum}+")) {
				JOptionPane.showMessageDialog(null, "鍮꾨踰덊샇 뺤떇щ컮瑜댁 딆뒿덈떎.");
				System.out.println("곸뼱,レ옄留낅젰댁＜몄슂.");
			} else {
				break;
			}
		}
		while (true) {
			System.out.println("몃뱶곕쾲몃 낅젰댁＜몄슂. ( - 앸왂)");
			System.out.println("Ex) 01012345678");
			phoneNumber = sc.nextLine();
			if (phoneNumber.length() > 12 || phoneNumber.length() < 11) {
				System.out.println("꾪솕踰덊샇뺤떇섎せ먯뒿덈떎 ( 湲몄씠 )");
			} else if (!phoneNumber.matches("^010[0-9]{8}")) {
				System.out.println("섎せ 낅젰섏듬땲");
			} else {
				break;
			}
		}
		while (true) {
			System.out.println("앸뀈붿씪 8먮━瑜낅젰댁＜몄슂.");
			System.out.println("Ex)96꾩깮 715쇱깮대㈃ >> 19960715");
			birthday = sc.nextLine();
			if (birthday.length() > 9 || birthday.length() <= 7) {
				System.out.println("앸뀈붿씪뺤떇섎せ 먯뒿덈떎 ( 湲몄씠 )");
			} else if (!birthday.matches(
					"^(19[0-9]|200)[0-9](((0(1|3|5|7|8)|1(0|2))(0[1-9]|[1-2][0-9]|3[0-1]))|((0(4|6|9)|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|(1|2)[0-9]$)))")) {
				System.out.println("섎せ뺤떇낅땲 ㅼ떆 낅젰댁＜몄슂.");
			} else {
				break;
			}
		}
		System.out.println("깃났곸쑝濡뚯썝媛낆쓣 섏뀲듬땲!!");
		System.out.println(name + "섏쁺⑸땲^~");
		loginCheck = true;
		memberLoggedIn = new Member(id, name, password, phoneNumber, birthday);
		hotel.getMembers().put(id, memberLoggedIn);
		//menuPrint();
		
		
	}
    /*
     * 濡쒓렇
     * 
     * 쒖뒪ъ떎됱떆 뚯썝 媛뚯썝 id瑜濡쒓렇
     * 
     * 묒꽦: 뺤쭊
     */
	public void login() {
		
		System.out.println("濡쒓렇);
			System.out.println("ID낅젰");
			String id = sc.nextLine();
			System.out.println("鍮꾨踰덊샇 낅젰");
			String pwd = sc.nextLine();
			if (!hotel.getMembers().containsKey(id)) {
				System.out.println("ID瑜뺤씤댁＜몄슂.");
			} else {
				if (!hotel.getMembers().get(id).getPassword().equals(pwd)) {
					System.out.println("鍮꾨踰덊샇瑜뺤씤댁＜몄슂.");
				} else {
					loginCheck = true;
					System.out.println("濡쒓렇깃났!");
				}
			}
		

	}

	/*
	 * 뚯썝 뺣낫 섏젙
	 * 
	 * 濡쒓렇뚯썝蹂몄씤뚯썝 뺣낫瑜섏젙쒕떎.
	 * 
	 * 묒꽦: 뺤쭊
	 */
	public void changeInfo() { 
		String password = null;
		String password2 = null;
		String phone = null;
		exit: while (true) {
            System.out.println("뚯썝 뺣낫 섏젙낅땲");
            System.out.println("                                 );
            System.out.println("         1. 鍮꾨踰덊샇 ъ꽕);
            System.out.println();
            System.out.println("         2. 꾪솕踰덊샇 ъ꽕);
            System.out.println();
            System.out.println("         3. 뚯썝 덊눜");
            System.out.println();
            System.out.println("         4. 뚯븘媛湲);
            System.out.println("                                 );
            String number = sc.nextLine();

			switch (number) {
			case "1":
				System.out.println("鍮꾨踰덊샇 ъ꽕뺤엯덈떎.");
				System.out.println("諛붽씀鍮꾨踰덊샇瑜낅젰댁＜몄슂. (6먯씠10대궡)");
				password = sc.nextLine();
				System.out.println("ㅼ떆 쒕쾲 낅젰댁＜몄슂.");
				password2 = sc.nextLine();
				if (password.equals(password2)) {
					memberLoggedIn.setPassword(password2);
					System.out.println("鍮꾨踰덊샇媛 諛붾뚯뿀듬땲");
				} else if (password.length() > 10 || password.length() < 6) {
					JOptionPane.showMessageDialog(null, "鍮꾨踰덊샇湲몄씠瑜뺤씤댁＜몄슂.");
					System.out.println("PWD湲몄씠媛 щ컮瑜댁 딆뒿덈떎.");
				} else if (!password.matches("\\p{Alnum}+")) {
					JOptionPane.showMessageDialog(null, "鍮꾨踰덊샇 뺤떇щ컮瑜댁 딆뒿덈떎.");
					System.out.println("곸뼱,レ옄留낅젰댁＜몄슂.");
				} else {
					System.out.println("鍮꾨踰덊샇媛 쇱튂섏 딆뒿덈떎.");
					System.out.println("ㅼ떆 쒕룄 二쇱꽭");
				}
				break;

			case "2":
				System.out.println("꾪솕踰덊샇 ㅼ젙낅땲");
				System.out.println("諛붽씀꾪솕踰덊샇瑜낅젰댁＜몄슂.");
				phone = sc.nextLine();
				if (phone.matches("^010[0-9]{8}")) {
					memberLoggedIn.setPhoneNumber(phone);
					System.out.println("諛붽씀몃뱶踰덊샇낅땲");
					System.out.println("諛붾꾪솕踰덊샇 : " + phone);
				} else {
					System.out.println("섎せ몃뱶곕쾲몄엯덈떎.");
					System.out.println("Ex)01012341234 ( - 쒖쇅 )");
				}
				break;
			case "3":
				quit();
				break exit;
			case "4":
				break exit;
			default:
				System.out.println("섎せ硫붾돱 踰덊샇낅땲 ㅼ떆 낅젰댁＜몄슂.");
				break;
			}
		}
	}

	/*
	 * 뚯썝 덊눜
	 * 
	 * 濡쒓렇몃맂 뚯썝뚯썝젣쒕떎.
	 * 
	 * 묒꽦: 뺤쭊
	 */
	public void quit() { 
		
        System.out.println("뚯썝 덊눜瑜섏떆寃좎뒿덇퉴?");
        System.out.println("                                 );
        System.out.println("         1.   );
        System.out.println();
        System.out.println("         2.   꾨땲);
        System.out.println("                                 );
		String select = sc.nextLine();
		switch (select) {
		case "1":
			System.out.println("1. 뚯썝 덊눜 섏듬땲");
			hotel.getMembers().remove(memberLoggedIn.getId(), memberLoggedIn);
			loginCheck = false;
			break;
		case "2":
			System.out.println("痍⑥냼 섏듬땲");
			break;
		default:
			System.out.println("섎せ硫붾돱 踰덊샇낅땲");
			quit();
			break;
		}

	}

	/*
	 * 硫붾돱
	 * 
	 * 鍮꾨줈洹몄씤 , 濡쒓렇뚯썝蹂댁씠硫붾돱媛 ㅻⅤ
	 * 뚯썝 媛낆쓣 섎㈃ 먮룞 濡쒓렇몄씠 ⑥쑝濡濡쒓렇명쉶붾㈃蹂댁씠寃쒕떎.
	 * 
	 * 묒꽦: 뺤쭊
	 */
	public void printMenu() { 
		while (true) {
            System.out.println("2議명뀛ㅼ떊嫄섏쁺⑸땲");
            System.out.println("                                 );
            System.out.println("         1.   媛앹떎 蹂닿린");
            System.out.println();
			if (!loginCheck) {
            System.out.println("         2.   濡쒓렇);
            System.out.println();
            System.out.println("         3.   뚯썝 媛);
            System.out.println();
            System.out.println("         4.   醫낅즺 섍린");
            System.out.println("                                 );
			} else {
                System.out.println("         2. 媛앹떎 덉빟섍린");
                System.out.println();
                System.out.println("         3. 덉빟 뺤씤섍린");
                System.out.println();
                System.out.println("         4. 뚯썝 뺣낫 섏젙");
                System.out.println();
                System.out.println("         5.   醫낅즺 섍린");
                System.out.println("                                 );
                System.out.println(memberLoggedIn.getName() + "諛⑸Ц섏쁺⑸땲");
			}

			String select = sc.nextLine();
			switch (select) {
			case "1":
				System.out.println("꾩옱 媛앹떎 뺣낫꾪솴 낅땲");
				getRoomInfo();
				break;
			case "2":
				if (!loginCheck) {
					login();
				} else {
					System.out.println("媛앹떎 덉빟낅땲");
					reserveRoom();
				}
				break;
			case "3":
				if (!loginCheck) {
					signUp();
				} else {
					getReservation();
				}
				break;
			case "4":
				if (!loginCheck) {
					saveHotel();
					return;
				} else {
					changeInfo();
				}
				break;
			case "5":
				if (!loginCheck) {
					System.out.println("踰덊샇瑜섎せ 낅젰덉뒿덈떎.");
				} else {
					saveHotel();
					return;
				}
				break;
			default:
				System.out.println("踰덊샇瑜섎せ 낅젰덉뒿덈떎.");
				break;

			}

		}
	}
}
