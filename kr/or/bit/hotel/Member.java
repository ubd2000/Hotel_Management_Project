package kr.or.bit.hotel;

import java.io.Serializable;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Reservation reservation;
	private Record records;
	private String id;
	private String name;
	private String password;
	private String phoneNumber;
	private String birthday;
	private boolean vip;

	public Member(String id, String name, String password, String phoneNumber, String birthday) {
		this.reservation = null;
		this.records = new Record();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.vip = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name + "  " + id + "  " + phoneNumber + "  " + birthday + "  " + records.getTotalPaid();
	}
	
	public String isVipString() {
		String isVip = "";
		if (vip) {
			isVip = "O";
		} else {
			isVip = "X";
		}
		
		return isVip;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Record getRecords() {
		return records;
	}

	public void setRecords(Record records) {
		this.records = records;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
