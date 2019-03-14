package kr.or.bit.hotel;

import java.util.ArrayList;
import java.util.List;

public class LargeHotel extends Hotel {
	/*
	 * 대형 호텔
	 * 
	 * 디럭스 룸 30만원
	 * 이그제큐티브 룸 60만원
	 * 스위트 룸 100만원
	 */
	@Override
	public void setHotelRoomPrices() {
		setRoomPrices(Number.LARGE_ROOM_PRICE);
	}

	/*
	 * 대형 호텔
	 * 7층(1층은 로비)
	 * 
	 * 2층 디럭스 룸 8개
	 * 3층 디럭스 룸 8개
	 * 4층 디럭스 룸 8개
	 * 5층 디럭스 룸 4개 이그제큐티브 룸 2개
	 * 6층 이그제큐티브 룸 4개
	 * 7층 스위트 룸 3개
	 */
	@Override
	public void setHotelRooms() {
		List<List<Room>> rooms = new ArrayList<List<Room>>();
		for (int i = 0; i < 6; i++) {
			rooms.add(new ArrayList<Room>());
		}
		
		List<Room> floor2 = rooms.get(0);
		List<Room> floor3 = rooms.get(1);
		List<Room> floor4 = rooms.get(2);
		List<Room> floor5 = rooms.get(3);
		List<Room> floor6 = rooms.get(4);
		List<Room> floor7 = rooms.get(5);
		

		for (int i = 0; i < 8; i++) {
			floor2.add(new DeluxeRoom());
			floor3.add(new DeluxeRoom());
			floor4.add(new DeluxeRoom());
		}
		
		for (int i = 0; i < 6; i++) {
			if (i < 4) {
				floor5.add(new DeluxeRoom());
			} else {
				floor5.add(new ExecutiveRoom());
			}
			
		}

		for (int i = 0; i < 4; i++) {
			floor6.add(new ExecutiveRoom());
		}
		
		for (int i = 0; i < 3; i++) {
			floor7.add(new SuiteRoom());
		}
		
		this.setRooms(rooms);
	}
}
