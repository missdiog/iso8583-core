package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.MSG_TYPE;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 消息类型转换器辅助类
 * 
 * @author alex
 */
public class MessageTypeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return Iso8583StandardFieldNoes.FIELD_NO_MSG_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String msgType = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnMsgType = iso8583BitMap.getResponseMsgType();

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnMsgType, msgType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String msgType = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(MSG_TYPE, msgType);

		return (msgType != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String msgType = txnCtx.getProperty(MSG_TYPE);
		if (msgType == null && iso8583BitMap.getRequestMsgType() != null) {
			msgType = iso8583BitMap.getRequestMsgType();
			txnCtx.setProperty(MSG_TYPE, msgType);
		}

		return Iso8583Operator.setField(isoMsg, getFieldNo(), msgType);
	}
}
