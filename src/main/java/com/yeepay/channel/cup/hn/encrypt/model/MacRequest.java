package com.yeepay.channel.cup.hn.encrypt.model;

import org.apache.commons.lang3.ArrayUtils;

public class MacRequest {
	private String cmdCode;//命令码
	private String sekIndex;//SEK索引
	private byte[] macKey;//mac密钥密文
	private byte[] len;//数据长度
	private byte[] data;//数据
	
	public String getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}

	public String getSekIndex() {
		return sekIndex;
	}

	public void setSekIndex(String sekIndex) {
		this.sekIndex = sekIndex;
	}

	public byte[] getMacKey() {
		return macKey;
	}

	public void setMacKey(byte[] macKey) {
		this.macKey = macKey;
	}

	public byte[] getLen() {
		return len;
	}

	public void setLen(byte[] len) {
		this.len = len;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] allData(){
		byte[] allBytes = new byte[0];
		if(getCmdCode() != null){
			allBytes = ArrayUtils.addAll(allBytes, getCmdCode().getBytes());
		}
		if(getSekIndex() != null){
			allBytes = ArrayUtils.addAll(allBytes, getSekIndex().getBytes());
		}
		if(getMacKey() != null){
			allBytes = ArrayUtils.addAll(allBytes, getMacKey());
		}
		if(getData() != null){
			String len = String.format("%03d", getData().length);
			allBytes = ArrayUtils.addAll(allBytes, len.getBytes());
			allBytes = ArrayUtils.addAll(allBytes, getData());
		}
		return allBytes;
	}
}
