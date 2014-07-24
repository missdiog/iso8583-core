package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.PAN;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_PAN;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 主账号域转换器辅助类
 * 
 * @author alex
 */
public class PANFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_PAN;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String pan = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnPan = txnCtx.getProperty(PAN);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnPan, pan);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String pan = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(PAN, pan);

		return (pan != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(PAN));
	}
}
