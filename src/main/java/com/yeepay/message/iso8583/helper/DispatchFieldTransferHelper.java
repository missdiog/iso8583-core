package com.yeepay.message.iso8583.helper;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelperSelector;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * Iso8583域信息转换器辅助类分发器
 * 
 * @author alex
 */
public class DispatchFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * 字段编号
	 */
	private int fieldNo;

	/**
	 * 字段辅助类选择器
	 */
	private Iso8583FieldTransferHelperSelector helperSelector;

	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return fieldNo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		Iso8583FieldTransferHelper helper = helperSelector.select(txnCtx, iso8583BitMap);
		if (helper == null) {
			throw new RuntimeException("Check iso8583 field=[" + fieldNo + "] error, fieldTransferHelper not found.");
		}

		helper.checkFieldInfo(isoMsg, txnCtx, iso8583BitMap);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		Iso8583FieldTransferHelper helper = helperSelector.select(txnCtx, iso8583BitMap);
		if (helper == null) {
			throw new RuntimeException("Get iso8583 field=[" + fieldNo + "] error, fieldTransferHelper not found.");
		}

		return helper.getFieldInfo(isoMsg, txnCtx, iso8583BitMap);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		Iso8583FieldTransferHelper helper = helperSelector.select(txnCtx, iso8583BitMap);
		if (helper == null) {
			throw new RuntimeException("Set iso8583 field=[" + fieldNo + "] error, fieldTransferHelper not found.");
		}

		return helper.setFieldInfo(isoMsg, txnCtx, iso8583BitMap);
	}

	/**
	 * @param fieldNo
	 *            the fieldNo to set
	 */
	public void setFieldNo(int fieldNo) {
		this.fieldNo = fieldNo;
	}

	/**
	 * @param helperSelector
	 *            the helperSelector to set
	 */
	public void setHelperSelector(Iso8583FieldTransferHelperSelector helperSelector) {
		this.helperSelector = helperSelector;
	}
}
