package kr.or.bit.hotel;

import java.io.Serializable;

public class SuiteRoom extends Room implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public SuiteRoom() {
		super("아구몬", Number.DEFAULT_PEOPLE_SUITE, Number.MAX_PEOPLE, Number.BED_SUITE, Number.BATHROOM_SUITE, true);
	}
}
