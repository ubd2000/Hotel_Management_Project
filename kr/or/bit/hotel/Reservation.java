package kr.or.bit.hotel;

public class Reservation {
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
	private Service service;
	private int amountPaid;
	
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
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	
}