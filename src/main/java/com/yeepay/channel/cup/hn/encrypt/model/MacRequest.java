package com.yeepay.channel.cup.hn.encrypt.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class MacRequest {
	private String cmdCode;//命令码
	private String macAlgorithm;//MAC算法
	private String sekIndex;//SEK索引
	private String makPre;//mak密钥密文前缀 X/Y/Z
	private byte[] mak;//mak密钥密文
	private byte[] data;//数据
	public String getCmdCode() {
		return cmdCode;
	}
	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}
	public String getMacAlgorithm() {
		return macAlgorithm;
	}
	public void setMacAlgorithm(String macAlgorithm) {
		this.macAlgorithm = macAlgorithm;
	}
	public String getSekIndex() {
		return sekIndex;
	}
	public void setSekIndex(String sekIndex) {
		this.sekIndex = sekIndex;
	}
	public String getMakPre() {
		return makPre;
	}
	public void setMakPre(String makPre) {
		this.makPre = makPre;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public byte[] getMak() {
		return mak;
	}
	public void setMak(byte[] mak) {
		this.mak = mak;
	}
	public byte[] allData(){
		byte[] allBytes = new byte[0];
		if(getCmdCode() != null){
			allBytes = ArrayUtils.addAll(allBytes, getCmdCode().getBytes());
		}
		if(getMacAlgorithm() != null){
			allBytes = ArrayUtils.addAll(allBytes, getMacAlgorithm().getBytes());
		}
		if(getSekIndex() != null){
			allBytes = ArrayUtils.addAll(allBytes, getSekIndex().getBytes());
		}
		if(getMakPre() != null && getMak() != null){
			allBytes = ArrayUtils.addAll(allBytes, getMakPre().getBytes());
			allBytes = ArrayUtils.addAll(allBytes, getMak());
		}
		if(getData() != null){
			String len = String.format("%04d", getData().length);
			allBytes = ArrayUtils.addAll(allBytes, len.getBytes());
			allBytes = ArrayUtils.addAll(allBytes, getData());
		}
		return allBytes;
	}
}
