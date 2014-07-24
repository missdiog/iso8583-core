package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AUTH_CODE;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_AUTH_CODE;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 授权码转换器辅助类
 * 
 * @author alex
 */
public class AuthCodeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_AUTH_CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnAuthCode = txnCtx.getProperty(AUTH_CODE);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnAuthCode, authCode);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(AUTH_CODE, authCode);

		return (authCode != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(AUTH_CODE));
	}
}
