package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.*;

import java.math.BigDecimal;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.channel.cup.hn.util.ConstantUtil;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;

/**
 * 水、电缴费转换助手
 * @author LiuDawei
 *
 */
public class Field44UserIDTransferHelper implements Iso8583FieldTransferHelper {

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
		String powerUserId = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		txnCtx.setProperty(USER_ID, powerUserId);
		return (powerUserId != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), txnCtx.getStringProperty(USER_ID));
	}

}
