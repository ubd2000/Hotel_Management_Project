package kr.or.bit.hotel;

import java.io.Serializable;

public class ExecutiveRoom extends Room implements Serializable {
	public ExecutiveRoom() {
		super("우세림", Number.DEFAULT_PEOPLE_EXECUTIVE, Number.MAX_PEOPLE, Number.BED_EXECUTIVE, Number.BATHROOM_EXECUTIVE, true);
	}
}
