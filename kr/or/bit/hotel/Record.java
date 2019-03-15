package kr.or.bit.hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Record implements Serializable {
	private List<Reservation> reservations;
	private long totalPaid;
	
	public Record() {
		reservations = new ArrayList<Reservation>();
		totalPaid = 0;
	}
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		totalPaid += reservation.getAmountPaid();
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public long getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(long totalPaid) {
		this.totalPaid = totalPaid;
	}
}
