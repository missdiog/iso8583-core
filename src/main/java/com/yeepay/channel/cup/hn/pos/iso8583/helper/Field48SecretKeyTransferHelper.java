package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;
import com.yeepay.message.TxnPropNames;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;


/**
 * 密钥数据转换助手
 * @author LiuDawei
 *
 */
public class Field48SecretKeyTransferHelper implements Iso8583FieldTransferHelper {

	public int getFieldNo() {
		return Iso8583StandardFieldNoes.FIELD_NO_ATTACH_DATA;
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
		String attachData = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		if(attachData != null){
			if(attachData.length() != 35){
				throw new RuntimeException("SecretKey length Error, length=" + attachData.length());
			}else{
				String macKey = attachData.substring(3, 19);
				String pinKey = attachData.substring(19);
				txnCtx.setProperty(TxnPropNames.MAC_KEY, macKey.getBytes());
				txnCtx.setProperty(TxnPropNames.PIN_KEY, pinKey.getBytes());
			}
		}
		return (attachData != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		throw new RuntimeException("Unexpected set, fieldNo=" + getFieldNo());
	}

}
