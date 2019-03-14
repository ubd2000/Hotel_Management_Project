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
	@Override
	public String toString() {
		return "＊예약확인＊ // 방 정보=[" + room + "], 예약 인원=[" + numberPeople + "], 체크인 날짜=[" + dateCheckIn
				+ "], 체크아웃 날짜=[" + dateCheckOut + "], 조식=[" + breakfast + "], 테라피=[" + therapy
				+ "], 총 요금=[" + amountPaid + "]";
	}

}