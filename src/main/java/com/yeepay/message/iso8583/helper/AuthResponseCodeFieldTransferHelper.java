package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AUTH_RESP_CODE;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_RESPONSE_CODE;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 授权机构返回码转换器辅助类
 * 
 * @author alex
 */
public class AuthResponseCodeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_RESPONSE_CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authRespCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnAuthRespCode = txnCtx.getProperty(AUTH_RESP_CODE);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnAuthRespCode, authRespCode);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authRespCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(AUTH_RESP_CODE, authRespCode);

		return (authRespCode != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(AUTH_RESP_CODE));
	}
}
