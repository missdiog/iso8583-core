package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.F44_AC_ACCOUNT_NO;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;

/**
 * Description: 44域:电视缴费AC账号 转换辅助器
 * 适用于有线电视缴费相关接口
 * @author Kevin
 * @Createdate 2014年8月14日.
 **/
public class Field44ACAccountNoTransferHelper implements Iso8583FieldTransferHelper {

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
		String acAcctNo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(F44_AC_ACCOUNT_NO, acAcctNo);
		return (acAcctNo != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(F44_AC_ACCOUNT_NO));
	}
	
}
