package kr.or.bit.hotel;

import java.util.ArrayList;
import java.util.List;

public class Record {
	private List<Reservation> reservations;
	private int totalPaid;
	
	public Record() {
		reservations = new ArrayList<Reservation>();
		totalPaid = 0;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public int getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(int totalPaid) {
		this.totalPaid = totalPaid;
	}
}
