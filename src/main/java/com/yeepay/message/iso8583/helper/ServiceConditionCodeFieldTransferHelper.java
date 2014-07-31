package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.SRV_COND_CODE;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_SERVICE_CONDITION_CODE;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

/**
 * 服务点条件代码转换器辅助类
 * @author alex
 */
public class ServiceConditionCodeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_SERVICE_CONDITION_CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String srvCondCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnSrvCondCode = txnCtx.getProperty(SRV_COND_CODE);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnSrvCondCode, srvCondCode);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String srvCondCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(SRV_COND_CODE, srvCondCode);

		return (srvCondCode != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String srvCondCode = txnCtx.getProperty(SRV_COND_CODE);
		if (srvCondCode == null && iso8583BitMap.getServiceConditionCode() != null) {
			srvCondCode = iso8583BitMap.getServiceConditionCode();
			txnCtx.setProperty(SRV_COND_CODE, srvCondCode);
		}
		//return Iso8583Operator.setField(isoMsg, getFieldNo(), srvCondCode);
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(SRV_COND_CODE));
	}
}
