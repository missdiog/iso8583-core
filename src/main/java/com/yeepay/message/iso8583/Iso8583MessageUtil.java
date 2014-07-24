package com.yeepay.message.iso8583;

import me.andpay.ti.util.ByteUtil;

/**
 * ISO8583报文工具类
 * 
 * @author alex
 */
public class Iso8583MessageUtil {
	/**
	 * 生成待发送的Iso8583数据
	 * 
	 * @param iso8583Data
	 * @param tpdu
	 * @param txnHead
	 * @return
	 */
	public static byte[] getIso8583DataToSend(byte[] iso8583Data, byte[] tpdu, byte[] txnHead) {
		// 2字节长度位 = TPDU长度 + txnHead长度 + iso8583Data长度
		byte[] dataLen = ByteUtil.parseBytes(tpdu.length + txnHead.length + iso8583Data.length, 2);
		byte[] dataToSend = ByteUtil.concat(dataLen, tpdu, txnHead, iso8583Data);

		return dataToSend;
	}

	/**
	 * 读取对方发送过来的数据
	 * 
	 * @param dataFromBank
	 * @param tpdu
	 * @param txnHead
	 * @return
	 */
	public static byte[] getIso8583DataFromRecv(byte[] dataFromBank, byte[] tpdu, byte[] txnHead) {
		// 两字节长度
		int len = (int) ByteUtil.toLong(ByteUtil.getBytes(dataFromBank, 0, 2));
		if (len != dataFromBank.length - 2) {
			throw new RuntimeException(String.format("Uncomplete resp data, expLen=2+%d, actLen=%d", len,
					dataFromBank.length));
		}

		return ByteUtil.subBytes(dataFromBank, 2 + tpdu.length + txnHead.length);
	}

}
