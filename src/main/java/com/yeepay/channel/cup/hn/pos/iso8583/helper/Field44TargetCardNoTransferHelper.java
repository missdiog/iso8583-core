package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.F44_TARGET_CARD_NO;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;

/**
 * Description: 44域:目标转入卡号 转换辅助器
 * 适用于(信用卡还款验证查询,还款确认,还款交易包,消费转账验证查询,消费转账确认)
 * @author Kevin
 * @Createdate 2014年8月14日.
 **/
public class Field44TargetCardNoTransferHelper implements Iso8583FieldTransferHelper {

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
		String targetCardNo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(F44_TARGET_CARD_NO, targetCardNo);
		return (targetCardNo != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(F44_TARGET_CARD_NO));
	}

}
