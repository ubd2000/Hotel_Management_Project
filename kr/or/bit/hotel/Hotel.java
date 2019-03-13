package kr.or.bit.hotel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Hotel {
	private List<List<Room>> rooms;
	private Room[] roomInfos;
	private int sales;
	private Map<String, Member> members;
	private List<Service> services;
	private int[] roomPrices;
	private Calendar today;

	public Hotel() {
		this.sales = 0;
		roomInfos = new Room[] { new DeluxeRoom(), new ExecutiveRoom(), new SuiteRoom() };
		members = new HashMap<String, Member>();
		services = new ArrayList<Service>();
		today = Calendar.getInstance();
		setRoomPrices();
		setHotelRooms();
	}

	public abstract void setRoomPrices();

	public abstract void setHotelRooms();

	public void setRooms(List<List<Room>> rooms) {
		this.rooms = rooms;
	}

	public void setRoomPrices(int[] roomPrices) {
		this.roomPrices = roomPrices;
	}

	public int[] getRoomPrices() {
		return roomPrices;
	}
}

