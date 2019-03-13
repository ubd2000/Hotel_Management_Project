package kr.or.bit.hotel;

public class SuiteRoom extends Room {
	public SuiteRoom(int price) {
		super("아구몬", price, Number.DEFAULT_PEOPLE_SUITE, Number.MAX_PEOPLE, Number.BED_SUITE, Number.BATHROOM_SUITE, true);
	}
}
