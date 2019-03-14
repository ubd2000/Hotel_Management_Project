package kr.or.bit.hotel;

import java.io.Serializable;

public class Reservation implements Serializable {
	private Room room;
	private int numberPeople;

	public int getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(int numberPeople) {
		this.numberPeople = numberPeople;
	}

	private HotelDate dateCheckIn;
	private HotelDate dateCheckOut;
	private boolean breakfast;
	private boolean therapy;
	private long amountPaid;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public HotelDate getDateCheckIn() {
		return dateCheckIn;
	}

	public void setDateCheckIn(HotelDate dateCheckIn) {
		this.dateCheckIn = dateCheckIn;
	}

	public HotelDate getDateCheckOut() {
		return dateCheckOut;
	}

	public void setDateCheckOut(HotelDate dateCheckOut) {
		this.dateCheckOut = dateCheckOut;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public boolean isTherapy() {
		return therapy;
	}

	public void setTherapy(boolean therapy) {
		this.therapy = therapy;
	}

	public long getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(long l) {
		this.amountPaid = l;
	}

}