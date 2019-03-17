package kr.or.bit.hotel;

import java.io.Serializable;

public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	private Room room;
	private int numberPeople;
	private HotelDate dateCheckIn;
	private HotelDate dateCheckOut;
	private boolean breakfast;
	private boolean therapy;
	private long amountPaid;

	public String checkReservation() {
		return "###예약확인### \n방 이름=" + room.getRoomName() + " 룸," + " " + room.getRoomNumber() + "호" + "\n예약 인원 : "
				+ numberPeople + "명, \n체크인 날짜 : " + dateCheckIn.getCheckDate()

				+ "\n체크아웃 날짜 : " + getDateCheckOut().getCheckDate() + "\n조식 : " + getBreakfastString()
				+ "\n전신 테라피 : " + getTherapyString() + "\n총 요금 : " + CustomString.putComma(amountPaid)
				+ "원\n----------------------";
	}

	public String checkRecord(Member member) {
		return "이름 : " + member.getName() + " 객실 : " + room.getRoomName() + " 객실 번호 : " + room.getRoomNumber()
				+ " 지불 금액 : " + CustomString.putComma(amountPaid) + " 체크인 날짜 : " + dateCheckIn.getDateString() + " 체크아웃 날짜 : "
				+ dateCheckOut.getDateString();
	}

	public int getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(int numberPeople) {
		this.numberPeople = numberPeople;
	}

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

	public String getBreakfastString() {
		return (breakfast) ? "O" : "X";
	}

	public String getTherapyString() {
		return (therapy) ? "O" : "X";
	}

}