package kr.or.bit.hotel;

import java.time.LocalDate;
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
	private int[] servicePrices;
	private LocalDate today;

	public Hotel() {
		this.sales = 0;
		roomInfos = new Room[] { new DeluxeRoom(), new ExecutiveRoom(), new SuiteRoom() };
		members = new HashMap<String, Member>();
		servicePrices = new int[] { Number.breakfast, Number.therapy };
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

	public List<List<Room>> getRooms() {
		return rooms;
	}

	public int[] getServicePrices() {
		return servicePrices;
	}

	public void setServicePrices(int[] servicePrices) {
		this.servicePrices = servicePrices;
	}

	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}
}

