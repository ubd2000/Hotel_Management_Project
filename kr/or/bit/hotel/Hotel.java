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
	private int[] roomPrices;
	private Calendar today;

	public Hotel() {
		this.sales = 0;
		roomInfos = new Room[] { new DeluxeRoom(), new ExecutiveRoom(), new SuiteRoom() };
		members = new HashMap<String, Member>();
		today = Calendar.getInstance();
		setHotelRoomPrices();
		setHotelRooms();
	}

	public abstract void setHotelRoomPrices();

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

	public Room[] getRoomInfos() {
		return roomInfos;
	}

	public void setRoomInfos(Room[] roomInfos) {
		this.roomInfos = roomInfos;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public Map<String, Member> getMembers() {
		return members;
	}

	public void setMembers(Map<String, Member> members) {
		this.members = members;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	public List<List<Room>> getRooms() {
		return rooms;
	}
}

