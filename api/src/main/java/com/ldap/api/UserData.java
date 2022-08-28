package com.ldap.api;


public class UserData {
	private String cn;
	private String sn;
	private int uid;
	
	
	
	
	public void setCn(String cn) {
		this.cn = cn;
	}


	public void setSn(String sn) {
		this.sn = sn;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public UserData() {
		
	}


	
	public UserData(String cn, String sn, int uid) {
		super();
		this.cn = cn;
		this.sn = sn;
		this.uid = uid;
	}

	
	public String getCn() {
		System.out.println("this is inside cn: ");
		return cn;
	}
	public String getSn() {
		return sn;
	}
	public int getUid() {
		return uid;
	}
}
