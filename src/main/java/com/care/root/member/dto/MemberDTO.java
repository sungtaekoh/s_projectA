package com.care.root.member.dto;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pw;
	private String addr;
	private Date limitTime;
	private String sessionId;
	
	
	public Date getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
