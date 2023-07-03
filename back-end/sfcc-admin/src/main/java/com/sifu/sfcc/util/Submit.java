package com.sifu.sfcc.util;

public class Submit {

	private String ecName;
	
	private String apId;
	
	private String secretKey;
	
	private String mobiles;
	
	private String content;
	
	private String sign;
	
	private String addSerial;

	private String mac;
	
	public String getEcName() {
		return ecName;
	}

	public void setEcName(String ecName) {
		this.ecName = ecName;
	}

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAddSerial() {
		return addSerial;
	}

	public void setAddSerial(String addSerial) {
		this.addSerial = addSerial;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
	public String toString() {
		return "Submit [ecName=" + ecName + ", apId=" + apId + ", secretKey=" + secretKey + ", mobiles=" + mobiles
				+ ", content=" + content + ", sign=" + sign + ", addSerial=" + addSerial + ", mac=" + mac + "]";
	}

	
	
}
