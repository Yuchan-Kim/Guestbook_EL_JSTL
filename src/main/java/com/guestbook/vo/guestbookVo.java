package com.guestbook.vo;

public class guestbookVo {
	
	//Fields
	private int personId;
	private String name;
	private String password;
	private String comments;
	
	//Constructor
	public guestbookVo(int personId, String name, String password, String comments) {
		this.personId = personId;
		this.name = name;
		this.password = password;
		this.comments = comments;
	}
	
	public guestbookVo(String name, String password, String comments) {
		
		this.name = name;
		this.password = password;
		this.comments = comments;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "guestbookVo [personId=" + personId + ", name=" + name + ", password=" + password + ", comments="
				+ comments + "]";
	}
	
	
	
	
	
	
	
}
