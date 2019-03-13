package kr.or.bit.hotel;

import java.util.ArrayList;
import java.util.List;

public class SmallHotel extends Hotel {

	/*
	 * 소형 호텔
	 * 
	 * 디럭스 룸 10만원 
	 * 이그제큐티브 룸 20만원 
	 * 스위트 룸 50만원
	 */
	@Override
	public void setRoomPrices() {
		setRoomPrices(new int[] { 100000, 200000, 500000 });
	}

	/*
	 * 소형 호텔 3층 (1층은 로비)
	 * 
	 * 2층 디럭스 룸 4개 3층 이그제큐티브 1개 스위트 1개
	 */
	@Override
	public void setHotelRooms() {
		int[] roomPrices = this.getRoomPrices();
		List<Room> floor2 = new ArrayList<Room>();
		List<Room> floor3 = new ArrayList<Room>();

		for (int i = 0; i < 4; i++) {
			floor2.add(new DeluxeRoom(roomPrices[0]));
		}

		floor3.add(new ExecutiveRoom(roomPrices[1]));
		floor3.add(new SuiteRoom(roomPrices[2]));
		
		List<List<Room>> rooms = new ArrayList<List<Room>>();
		rooms.add(floor2);
		rooms.add(floor3);
		this.setRooms(rooms);
	}
}
