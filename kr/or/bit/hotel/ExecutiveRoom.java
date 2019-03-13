package kr.or.bit.hotel;

public class ExecutiveRoom extends Room {
	public ExecutiveRoom(int price) {
		super("우세림", price, Number.DEFAULT_PEOPLE_EXECUTIVE, Number.MAX_PEOPLE, Number.BED_EXECUTIVE, Number.BATHROOM_EXECUTIVE, true);
	}
}
