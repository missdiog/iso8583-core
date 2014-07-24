package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AUTH_REF;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_AUTH_REF;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 授权机构系统参考号转换器辅助类
 * 
 * @author alex
 */
public class AuthRefFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_AUTH_REF;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authRef = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnAuthRef = txnCtx.getProperty(AUTH_REF);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnAuthRef, authRef);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authRef = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(AUTH_REF, authRef);

		return (authRef != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(AUTH_REF));
	}
}
