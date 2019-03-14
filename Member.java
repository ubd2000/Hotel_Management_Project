package kr.or.bit.hotel;
import java.util.ArrayList;

/**
 * @author os
 *
 */
public class Member {
	private Reservation Reservation;
	private ArrayList<Record[]> Record;
	private String id; // 추가
	private String password;
	private String name;
	private String birthday;
	private String phoneNumber;
	
	public Member() {
		Record = new ArrayList<Record[]>();
		Reservation = new Reservation();
	}
	public Member(String name, String password , String phoneNumber , String birthday) {
		this.name = name;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
