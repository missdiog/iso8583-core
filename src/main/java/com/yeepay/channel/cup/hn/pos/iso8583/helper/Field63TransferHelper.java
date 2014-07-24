package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.POWER_UNIT_CODE;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.channel.cup.hn.pos.iso8583.CupHnPosIso8583FieldNos;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

/**
 * 自定义域: (有线电视缴费:缴费帐户所在地区; 电力缴费:供电单位编码)
 * @author Kevin
 * @Createdate 2014年7月22日.
 **/
public class Field63TransferHelper implements Iso8583FieldTransferHelper {

	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return CupHnPosIso8583FieldNos.FIELD_NO_63;
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
		String unitCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(POWER_UNIT_CODE, unitCode);
		return (unitCode != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(POWER_UNIT_CODE));
	}

}
