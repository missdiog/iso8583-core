package com.yeepay.channel.cup.hn.encrypt.model;

import me.andpay.ti.util.ByteUtil;

public class MacResponse {
	private String respCode;//返回码
	private String errCode;//错误码
	private byte[] mac;//mac
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public byte[] getMac() {
		return mac;
	}
	public void setMac(byte[] mac) {
		this.mac = mac;
	}
	
	public void parseBytes(byte[] bytes){
		if(bytes != null && bytes.length >= 4){
			this.setRespCode(new String(ByteUtil.getBytes(bytes, 0, 2)));
			this.setErrCode(new String(ByteUtil.getBytes(bytes, 2, 2)));
			if(bytes.length > 4){
				this.setMac(ByteUtil.subBytes(bytes, 4));
			}
		}
	}
}
