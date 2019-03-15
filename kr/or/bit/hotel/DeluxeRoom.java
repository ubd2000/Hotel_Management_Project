package kr.or.bit.hotel;

import java.io.Serializable;

public class DeluxeRoom extends Room implements Serializable {
	private static final long serialVersionUID = 1L;

	public DeluxeRoom() {
		super("권순조", Number.DEFAULT_PEOPLE_DELUXE, Number.MAX_PEOPLE, Number.BED_DELUXE, Number.BATHROOM_DELUXE, false);
	}
}
