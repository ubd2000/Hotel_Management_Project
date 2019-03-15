package kr.or.bit.hotel;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<List<Room>> rooms;
	private Room[] roomInfos;
	private long sales;
	private Map<String, Member> members;
	private int[] roomPrices;
	private int breakfast;
	private int therapy;
	private int[] servicePrices;
	private LocalDate today;

	public Hotel() {
		this.sales = 0;
		roomInfos = new Room[] { new DeluxeRoom(), new ExecutiveRoom(), new SuiteRoom() };
		members = new HashMap<String, Member>();
		today = LocalDate.now();
		breakfast = Number.breakfast;
		therapy = Number.therapy;
		servicePrices = new int[] { Number.breakfast, Number.therapy };
		setHotelRoomPrices();
		setHotelRooms();
	}

	public abstract void setHotelRoomPrices();

	public abstract void setHotelRooms();

	public int getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(int breakfast) {
		this.breakfast = breakfast;
	}

	public int getTherapy() {
		return therapy;
	}

	public void setTherapy(int therapy) {
		this.therapy = therapy;
	}

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

	public long getSales() {
		return sales;
	}

	public void setSales(long sales) {
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

	public List<List<Room>> getRooms() {
		return rooms;
	}
}
