package com.bit2020.emaillist02.vo;

public class EmaillistVo {
	private long no;
	private String firstName;
	private String lastName;
	private String email;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fisrtName) {
		this.firstName = fisrtName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "EmaillistVo [no=" + no + ", fisrtName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}
	
	
}
