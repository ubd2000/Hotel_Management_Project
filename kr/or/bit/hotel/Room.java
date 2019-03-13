package kr.or.bit.hotel;

public class Room {
	private String roomName;
	private int defaultNumberPeople;
	private int maxNumberPeople;
	private int numberBed;
	private int numberBathroom;
	private boolean kitchen;
	private Member guest;
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
}
