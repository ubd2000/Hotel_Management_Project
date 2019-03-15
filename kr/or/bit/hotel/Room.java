package kr.or.bit.hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String roomName;
	private String roomNumber;
	private int defaultNumberPeople;
	private int maxNumberPeople;
	private int numberBed;
	private int numberBathroom;
	private boolean kitchen;
	private List<String> guests;
	private boolean isBooked;
	
	public Room(String roomName, int defaultNumberPeople, int maxNumberPeople, int numberBed,
			int numberBathroom, boolean kitchen) {
		this.roomName = roomName;
		this.defaultNumberPeople = defaultNumberPeople;
		this.maxNumberPeople = maxNumberPeople;
		this.numberBed = numberBed;
		this.numberBathroom = numberBathroom;
		this.guests = new ArrayList<String>();
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

	public List<String> getGuests() {
		return guests;
	}

	public void setGuests(List<String> guests) {
		this.guests = guests;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
