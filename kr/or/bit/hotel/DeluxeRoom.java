package kr.or.bit.hotel;

public class DeluxeRoom extends Room {
	public DeluxeRoom(int price) {
		super("권순조", price, Number.DEFAULT_PEOPLE_DELUXE, Number.MAX_PEOPLE, Number.BED_DELUXE, Number.BATHROOM_DELUXE, false);
	}
}
