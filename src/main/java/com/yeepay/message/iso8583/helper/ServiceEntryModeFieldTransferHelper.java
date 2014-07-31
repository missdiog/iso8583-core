package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.SRV_ENTRY_MODE;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_SERVICE_ENTRY_MODE;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

/**
 * 服务输入模式转换器辅助类
 * @author alex
 */
public class ServiceEntryModeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_SERVICE_ENTRY_MODE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String srvEntryMode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnSrvEntryMode = txnCtx.getProperty(SRV_ENTRY_MODE);
		Iso8583Operator.checkFieldInfo(getFieldNo(), txnSrvEntryMode, srvEntryMode);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String srvEntryMode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(SRV_ENTRY_MODE, srvEntryMode);
		return (srvEntryMode != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String srvEntryMode = txnCtx.getProperty(SRV_ENTRY_MODE);
		if (srvEntryMode == null && iso8583BitMap.getServiceEntryMode() != null) {
			srvEntryMode = iso8583BitMap.getServiceEntryMode();
			txnCtx.setProperty(SRV_ENTRY_MODE, srvEntryMode);
		}
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(SRV_ENTRY_MODE));
	}
}
