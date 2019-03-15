package kr.or.bit.hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MediumHotel extends Hotel implements Serializable {
	
	/*
	 * 중형 호텔
	 * 
	 * 디럭스 룸 30만원
	 * 이그제큐티브 룸 50만원
	 * 스위트 룸 100만원
	 */
	@Override
	public void setHotelRoomPrices() {
		setRoomPrices(Number.MEDIUM_ROOM_PRICE);
	}

	/*
	 * 중형 호텔
	 * 5층(1층은 로비)
	 * 
	 * 2층 디럭스 룸 6개
	 * 3층 디럭스 룸 6개
	 * 4층 이그제큐티브 룸 3개
	 * 5층 스위트 룸 2개
	 */
	@Override
	public void setHotelRooms() {
		List<List<Room>> rooms = new ArrayList<List<Room>>();
		for (int i = 0; i < 4; i++) {
			rooms.add(new ArrayList<Room>());
		}
		
		List<Room> floor2 = rooms.get(0);
		List<Room> floor3 = rooms.get(1);
		List<Room> floor4 = rooms.get(2);
		List<Room> floor5 = rooms.get(3);
		

		for (int i = 0; i < 6; i++) {
			floor2.add(new DeluxeRoom());
			floor3.add(new DeluxeRoom());
		}
		
		for (int i = 0; i < 3; i++) {
			floor4.add(new ExecutiveRoom());
		}

		for (int i = 0; i < 2; i++) {
			floor5.add(new SuiteRoom());
		}
		this.setRooms(rooms);
	}
}
