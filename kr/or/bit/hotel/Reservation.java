package kr.or.bit.hotel;

import java.io.Serializable;

public class Reservation implements Serializable {
	private Room room;
	private int numberPeople;
	private String breakFast;
	private String Therapy;
	private String roomnumber;
	private HotelDate dateCheckIn;
	private HotelDate dateCheckOut;
	private boolean breakfast;
	private boolean therapy;
	private long amountPaid;
	private int dayprice;
	private CustomString money = new CustomString();
	 
	public Reservation() {
		this.breakFast = "X";
		this.Therapy = "X";
		this.roomnumber = "";
	}
	
	//Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());
	//diff.getDays() << 날짜비교 정수값 반환
	
	public String getRoomnumber() {
		return roomnumber;
	}



	
	

	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
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
		if(this.breakfast == true) {
			this.breakFast = "O";
		}
	}

	public boolean isTherapy() {
		return therapy;
	}

	public void setTherapy(boolean therapy) {
		this.therapy = therapy;
		if(this.therapy == true) {
			this.Therapy = "O";
		}
		
		}


	public long getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(long l) {
		this.amountPaid = l;
	}

	@Override
	public String toString() {
		return "###예약확인### \n방 이름=[" + room.getRoomName() + "]룸," +" " +getRoomnumber()+ "호"+ "\n예약 인원=[" + numberPeople + "], \n체크인 날짜=[" + dateCheckIn.getCheckDate()
				
				+ "], \n체크아웃날짜=[" + getDateCheckOut().getCheckDate() + "], \n조식=[" + breakFast + "], \n전신 테라피=[" + Therapy
				+ "], \n총 요금 =[" + money.putComma(amountPaid) + "]원\n----------------------";
	}

	
}