package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.IC_DATA;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * IC卡数据转换器辅助类
 * 
 * @author alex
 */
public class ICDataFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return Iso8583StandardFieldNoes.FIELD_NO_IC_DATA;
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
		byte[] icCardData = Iso8583Operator.getFieldBinary(isoMsg, getFieldNo());
		txnCtx.setProperty(IC_DATA, icCardData);

		return (icCardData != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getByteArrayProperty(IC_DATA));
	}
}
