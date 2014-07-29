package com.yeepay.message.iso8583;

import java.math.BigDecimal;
import java.util.Date;

import me.andpay.ti.util.StringUtil;

import org.jpos.iso.ISOComponent;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 * ISO8583字段操作工具类
 * 
 * @author sea.bao
 */
public class Iso8583Operator {
	/**
	 * 设置字符串域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @param value
	 * @return
	 */
	public static boolean setField(ISOMsg isoMsg, int fieldNo, String value) {
		if (value == null) {
			return false;
		}

		try {
			isoMsg.set(fieldNo, value);
		} catch (ISOException e) {
			throw new RuntimeException("Fail to set iso8583 field=[" + fieldNo + "], value=[" + value + "].", e);
		}

		return true;
	}

	/**
	 * 设置数字域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @param pattern
	 * @param value
	 * @return
	 */
	public static boolean setField(ISOMsg isoMsg, int fieldNo, String pattern, BigDecimal value) {
		if (value == null) {
			return false;
		}

		String sValue = StringUtil.formatNumber(pattern, value);
		setField(isoMsg, fieldNo, sValue);

		return true;
	}

	/**
	 * 设置二进制域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @param value
	 * @return
	 */
	public static boolean setField(ISOMsg isoMsg, int fieldNo, byte[] value) {
		if (value == null) {
			return false;
		}

		try {
			isoMsg.set(fieldNo, value);
		} catch (ISOException e) {
			throw new RuntimeException("Fail to set iso8583 field=[" + fieldNo + "], value=[" + value + "].", e);
		}

		return true;
	}

	/**
	 * 设置TLV域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @param tlvList
	 * @return
	 */
	/*
	public static boolean setField(ISOMsg isoMsg, int fieldNo, TLVList tlvList) {
		if (tlvList == null) {
			return false;
		}
		try {
			isoMsg.set(fieldNo, tlvList.pack());
		} catch (ISOException e) {
			throw new RuntimeException("Fail to set iso8583 field=[" + fieldNo + "], value=[" + tlvList + "].", e);
		}

		return true;
	}
	*/
	
	/**
	 * 获取字符串域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @return
	 */
	public static String getFieldString(ISOMsg isoMsg, int fieldNo) {
		// 空白字符返回null
		return StringUtil.defaultStringIfBlank(isoMsg.getString(fieldNo), null);
	}

	/**
	 * 获取数字域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @return
	 */
	public static BigDecimal getFieldBigDecimal(ISOMsg isoMsg, int fieldNo) {
		String value = getFieldString(isoMsg, fieldNo);
		return (value != null ? new BigDecimal(value.trim()) : null);
	}

	/**
	 * 获取二进制域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @return
	 */
	public static byte[] getFieldBinary(ISOMsg isoMsg, int fieldNo) {
		try {
			ISOComponent com = isoMsg.getComponent(fieldNo);
			return (com != null ? com.getBytes() : null);
		} catch (ISOException e) {
			throw new RuntimeException("Fail to get iso8583 field=[" + fieldNo + "].", e);
		}
	}

	/**
	 * 获取TLV域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @return
	 */
	/*
	public static TLVList getFieldTLV(ISOMsg isoMsg, int fieldNo) {
		try {
			ISOComponent com = isoMsg.getComponent(fieldNo);
			if (com != null) {
				TLVList tlvList = new TLVList();
				tlvList.unpack(com.getBytes());
				return tlvList;
			}

			return null;
		} catch (ISOException e) {
			throw new RuntimeException("Fail to get iso8583 field=[" + fieldNo + "].", e);
		}
	}
	*/
	/**
	 * 获取域值
	 * 
	 * @param isoMsg
	 * @param fieldNo
	 * @return
	 */
	public static Object getField(ISOMsg isoMsg, int fieldNo) {
		try {
			ISOComponent com = isoMsg.getComponent(fieldNo);
			return (com != null ? com.getValue() : null);
		} catch (ISOException e) {
			throw new RuntimeException("Fail to get iso8583 field=[" + fieldNo + "].", e);
		}
	}

	/**
	 * 检查字符串字段信息
	 * 
	 * @param fieldNo
	 * @param txnBodyValue
	 * @param iso8583FieldValue
	 */
	public static void checkFieldInfo(int fieldNo, String txnBodyValue, String iso8583FieldValue) {
		boolean isEqual = false;
		if (txnBodyValue == null || iso8583FieldValue == null) {
			isEqual = (txnBodyValue == iso8583FieldValue);

		} else {
			isEqual = txnBodyValue.equals(iso8583FieldValue);
		}

		if (isEqual == false) {
			throw new RuntimeException(String.format("Check iso8583 field=[%s] error, txnBody=[%s], isoMsg=[%s]",
					fieldNo, txnBodyValue, iso8583FieldValue));
		}
	}

	/**
	 * 检查数字字段信息
	 * 
	 * @param fieldNo
	 * @param txnBodyValue
	 * @param iso8583FieldValue
	 */
	public static void checkFieldInfo(int fieldNo, BigDecimal txnBodyValue, BigDecimal iso8583FieldValue) {
		boolean isEqual = false;
		if (txnBodyValue == null || iso8583FieldValue == null) {
			isEqual = (txnBodyValue == iso8583FieldValue);

		} else {
			isEqual = (txnBodyValue.compareTo(iso8583FieldValue) == 0);
		}

		if (isEqual == false) {
			throw new RuntimeException(String.format("Check iso8583 field=[%s] error, txnBody=[%s], isoMsg=[%s]",
					fieldNo, txnBodyValue, iso8583FieldValue));
		}
	}

	/**
	 * 检查日期字段信息
	 * 
	 * @param fieldNo
	 * @param txnBodyValue
	 * @param iso8583FieldValue
	 */
	public static void checkFieldInfo(int fieldNo, Date txnBodyValue, Date iso8583FieldValue) {
		boolean isEqual = false;
		if (txnBodyValue == null || iso8583FieldValue == null) {
			isEqual = (txnBodyValue == iso8583FieldValue);

		} else {
			isEqual = (txnBodyValue.compareTo(iso8583FieldValue) == 0);
		}

		if (isEqual == false) {
			throw new RuntimeException(String.format("Check iso8583 field=[%s] error, txnBody=[%s], isoMsg=[%s]",
					fieldNo, txnBodyValue, iso8583FieldValue));
		}
	}
}
