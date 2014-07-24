package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AUTH_TRACE_NO;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_AUTH_TRACE_NO;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 授权机构跟踪号转换器辅助类
 * 
 * @author alex
 */
public class AuthTraceNoFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_AUTH_TRACE_NO;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authTraceNo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnAuthTraceNo = txnCtx.getProperty(AUTH_TRACE_NO);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnAuthTraceNo, authTraceNo);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authTraceNo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(AUTH_TRACE_NO, authTraceNo);

		return (authTraceNo != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(AUTH_TRACE_NO));
	}
}
