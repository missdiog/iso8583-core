package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.ADDI_AUTH_RESP;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_ADDI_AUTH_RESP;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 附加响应数据转换器辅助类
 * 
 * @author alex
 */
public class AdditionalAuthResponseFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_ADDI_AUTH_RESP;
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
		String acqIssuerId = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(ADDI_AUTH_RESP, acqIssuerId);

		return (acqIssuerId != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return false;
	}
}
