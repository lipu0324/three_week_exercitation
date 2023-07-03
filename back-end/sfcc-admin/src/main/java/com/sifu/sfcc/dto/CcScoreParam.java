package com.sifu.sfcc.dto;

import java.io.Serializable;

public class CcScoreParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String phone;
	
	private String sessionId;
	
	private String code;
	
	private String isPerson;
	
	private String score;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIsPerson() {
		return isPerson;
	}
	public void setIsPerson(String isPerson) {
		this.isPerson = isPerson;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "CcScoreParam [phone=" + phone + ", sessionId=" + sessionId + ", code=" + code + ", isPerson=" + isPerson
				+ ", score=" + score + "]";
	}

    

}