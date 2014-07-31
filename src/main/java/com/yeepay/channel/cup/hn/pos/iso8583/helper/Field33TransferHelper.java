package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.SEND_INST_ID;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.channel.cup.hn.pos.iso8583.CupHnPosIso8583FieldNos;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

/**
 * Description: 发送机构代码域转换辅助器
 * @author Kevin
 * @Createdate 2014年7月22日.
 **/

public class Field33TransferHelper implements Iso8583FieldTransferHelper {

	public int getFieldNo() {
		return CupHnPosIso8583FieldNos.FIELD_NO_33;
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
		String sendInstId = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(SEND_INST_ID, sendInstId);
		return (sendInstId != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String sendInstId = txnCtx.getProperty(SEND_INST_ID);
		if (sendInstId == null && iso8583BitMap.getSendInstitutionId() != null) {
			sendInstId = iso8583BitMap.getSendInstitutionId();
			txnCtx.setProperty(SEND_INST_ID, sendInstId);
		}
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(SEND_INST_ID));
	}
	
}
