package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.PROCESS_CODE;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 处理码转换器辅助类
 * 
 * @author alex
 */
public class ProcessCodeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return Iso8583StandardFieldNoes.FIELD_NO_PROCESS_CODE;
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
		String processCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(PROCESS_CODE, processCode);

		return (processCode != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String processCode = txnCtx.getProperty(PROCESS_CODE);
		if (processCode == null && iso8583BitMap.getProcessCode() != null) {
			processCode = iso8583BitMap.getProcessCode();
			txnCtx.setProperty(PROCESS_CODE, processCode);
		}

		return Iso8583Operator.setField(isoMsg, getFieldNo(), processCode);
	}
}
