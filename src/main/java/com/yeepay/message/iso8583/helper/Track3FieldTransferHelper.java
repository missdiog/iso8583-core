package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.TRACK3;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_TRACK3;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 3磁道转换器辅助类
 * 
 * @author alex
 */
public class Track3FieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_TRACK3;
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
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String track3 = txnCtx.getProperty(TRACK3);
		if (track3 == null) {
			return false;
		}

		return Iso8583Operator.setField(isoMsg, getFieldNo(), track3);
	}
}
