package com.yeepay.channel.cup.hn.encrypt;

import me.andpay.ti.util.ByteUtil;
import me.andpay.ti.util.HexUtil;

import org.apache.commons.lang3.ArrayUtils;


public class FilterData {
	private byte[] msgLen;//消息长度
	private byte[] msgHead;//消息头
	private byte[] msgCxt;//消息内容
	public byte[] getMsgLen() {
		return msgLen;
	}
	public void setMsgLen(byte[] msgLen) {
		this.msgLen = msgLen;
	}
	public byte[] getMsgHead() {
		return msgHead;
	}
	public void setMsgHead(byte[] msgHead) {
		this.msgHead = msgHead;
	}
	public byte[] getMsgCxt() {
		return msgCxt;
	}
	public void setMsgCxt(byte[] msgCxt) {
		this.msgCxt = msgCxt;
	}
	
	public byte[] parseRequest(){
		byte[] result = new byte[0];
		int len = 0;
		if(msgHead != null){
			len += msgHead.length;
		}
		if(msgCxt != null){
			len += msgCxt.length;
		}
		String lenStr = Integer.toHexString(len);
		byte[] lenBytes = HexUtil.decodeHex(lenStr);
		if(lenBytes.length < 2){
			byte[] zeroByte = new byte[]{0x00};
			lenBytes = ByteUtil.concat(zeroByte, lenBytes);
		}
		result = ArrayUtils.addAll(result, lenBytes);
		result = ArrayUtils.addAll(result, msgHead);
		result = ArrayUtils.addAll(result, msgCxt);
		
		return result;
	}
	
	public static void main(String[] args) {
		String lenStr = Integer.toHexString(160);
		byte[] lenBytes = HexUtil.decodeHex(lenStr);;
		if(lenBytes.length < 2){
			byte[] zeroByte = new byte[]{0x00};
			lenBytes = ByteUtil.concat(zeroByte, lenBytes);
		}
		String str = HexUtil.encodeHex(lenBytes);
		
		System.out.println(str);
	}
	
	public void parseResponse(byte[] bytes){
		if(bytes != null && bytes.length > 2){
			this.msgLen = ByteUtil.subBytes(bytes, 0, 2);
			int cxtLen = 2;
			if(this.msgHead != null && this.msgHead.length > 0){
				cxtLen += msgHead.length; 
			}
			this.msgCxt = ByteUtil.subBytes(bytes, cxtLen);
		}
	}
}
