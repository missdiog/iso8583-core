package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.F44_MOBILE_PHONE;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;

/**
 * Description: 44域:运营商手机号  转换辅助器
 * 适用于手机话费充值
 * @author Kevin
 * @Createdate 2014年8月14日.
 **/
public class Field44MobilePhoneTransferHelper implements
		Iso8583FieldTransferHelper {
	
	public int getFieldNo() {
		return Iso8583StandardFieldNoes.FIELD_NO_ADDI_AUTH_RESP;
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
		String mobilePhone = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(F44_MOBILE_PHONE, mobilePhone);
		return (mobilePhone != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(F44_MOBILE_PHONE));
	}

}
