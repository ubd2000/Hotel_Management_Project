package kr.or.bit.hotel;

import java.util.List;

public class Room {
	private String roomName;
	private int defaultNumberPeople;
	private int maxNumberPeople;
	private int numberBed;
	private int numberBathroom;
	private boolean kitchen;
	private List<Member> guest;
	private boolean isBooked;
	
	public Room(String roomName, int defaultNumberPeople, int maxNumberPeople, int numberBed,
			int numberBathroom, boolean kitchen) {
		this.roomName = roomName;
		this.defaultNumberPeople = defaultNumberPeople;
		this.maxNumberPeople = maxNumberPeople;
		this.numberBed = numberBed;
		this.numberBathroom = numberBathroom;
		this.kitchen = kitchen;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getDefaultNumberPeople() {
		return defaultNumberPeople;
	}

	public void setDefaultNumberPeople(int defaultNumberPeople) {
		this.defaultNumberPeople = defaultNumberPeople;
	}

	public int getMaxNumberPeople() {
		return maxNumberPeople;
	}

	public void setMaxNumberPeople(int maxNumberPeople) {
		this.maxNumberPeople = maxNumberPeople;
	}

	public int getNumberBed() {
		return numberBed;
	}

	public void setNumberBed(int numberBed) {
		this.numberBed = numberBed;
	}

	public int getNumberBathroom() {
		return numberBathroom;
	}

	public void setNumberBathroom(int numberBathroom) {
		this.numberBathroom = numberBathroom;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public List<Member> getGuest() {
		return guest;
	}

	public void setGuest(List<Member> guest) {
		this.guest = guest;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
}