package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_TXN_DATE;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 授权交易日期转换器辅助类
 * 
 * @author alex
 */
public class AuthDateFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_TXN_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		throw new RuntimeException("Unexpected check, fieldNo=" + getFieldNo());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String authDate = Iso8583Operator.getFieldString(isoMsg, getFieldNo());

		// 由AuthTimeFieldTransferHelper负责设置
		return (authDate != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		// 由AuthTimeFieldTransferHelper负责设置
		return getFieldInfo(isoMsg, txnCtx, iso8583BitMap);
	}
}
