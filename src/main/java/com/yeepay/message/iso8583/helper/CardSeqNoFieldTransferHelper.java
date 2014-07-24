package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.CARD_SEQ_NO;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 卡序列号转换器辅助类
 * 
 * @author alex
 */
public class CardSeqNoFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return Iso8583StandardFieldNoes.FIELD_NO_CARD_SEQ_NO;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String cardSeqNo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnCardSeqNo = txnCtx.getProperty(CARD_SEQ_NO);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnCardSeqNo, cardSeqNo);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String cardSeqNo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(CARD_SEQ_NO, cardSeqNo);

		return (cardSeqNo != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(CARD_SEQ_NO));
	}
}
