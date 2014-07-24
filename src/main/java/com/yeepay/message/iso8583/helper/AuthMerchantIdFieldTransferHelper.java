package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AUTH_MERCH_ID;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_MERCHANT_ID;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 授权商户编号转换器辅助类
 * 
 * @author alex
 */
public class AuthMerchantIdFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_MERCHANT_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authMerchId = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnAuthMerchId = txnCtx.getProperty(AUTH_MERCH_ID);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnAuthMerchId, authMerchId);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authMerchId = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(AUTH_MERCH_ID, authMerchId);

		return (authMerchId != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(AUTH_MERCH_ID));
	}
}
