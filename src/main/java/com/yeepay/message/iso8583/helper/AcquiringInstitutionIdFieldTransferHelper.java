package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.ACQ_INST_ID;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_ACQ_INST_ID;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 受理机构标示码转换器辅助类
 * 
 * @author alex
 */
public class AcquiringInstitutionIdFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_ACQ_INST_ID;
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
		txnCtx.setProperty(ACQ_INST_ID, acqIssuerId);

		return (acqIssuerId != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(ACQ_INST_ID));
	}
}
