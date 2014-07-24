package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.MERCHANT_TYPE;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.channel.cup.hn.pos.iso8583.CupHnPosIso8583FieldNos;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

/**
 * Description: 商户类型域转换辅助器
 * @author Kevin
 * @Createdate 2014年7月22日.
 **/

public class Field18TransferHelper implements Iso8583FieldTransferHelper {

	public int getFieldNo() {
		return CupHnPosIso8583FieldNos.FIELD_NO_18;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String merchantType = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String txnMerchantType = txnCtx.getProperty(MERCHANT_TYPE);
		Iso8583Operator.checkFieldInfo(getFieldNo(), txnMerchantType, merchantType);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String merchantType = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(MERCHANT_TYPE, merchantType);
		return (merchantType != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(MERCHANT_TYPE));
	}

}
