package com.yeepay.message.iso8583.helper;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * ISO8583常量帮助类
 * 
 * @author alex
 */
public class StringConstantFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * ISO8583域编号
	 */
	private int fieldNo;

	/**
	 * 字符串常量值
	 */
	private String constantValue;

	/**
	 * 属性名称
	 */
	private String propName;

	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return fieldNo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException {
		String value = Iso8583Operator.getFieldString(isoMsg, getFieldNo());

		Iso8583Operator.checkFieldInfo(this.getFieldNo(), constantValue, value);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException {
		String value = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		if (propName != null) {
			txnCtx.setProperty(propName, value);
		}

		return (value != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException {
		if (propName != null) {
			txnCtx.setProperty(propName, constantValue);
		}

		return Iso8583Operator.setField(isoMsg, getFieldNo(), constantValue);
	}

	/**
	 * @param fieldNo
	 *            the fieldNo to set
	 */
	public void setFieldNo(int fieldNo) {
		this.fieldNo = fieldNo;
	}

	/**
	 * @param constantValue
	 *            the constantValue to set
	 */
	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
	}

	/**
	 * @param propName
	 *            the propName to set
	 */
	public void setPropName(String propName) {
		this.propName = propName;
	}
}
