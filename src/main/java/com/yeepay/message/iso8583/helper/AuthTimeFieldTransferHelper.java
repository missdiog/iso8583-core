package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AUTH_TIME;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_TXN_DATE;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_TXN_TIME;

import java.util.Calendar;
import java.util.Date;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;
import me.andpay.ti.util.StringUtil;

import org.jpos.iso.ISOMsg;

/**
 * 授权交易时间转换器辅助类
 * 
 * @author alex
 */
public class AuthTimeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_TXN_TIME;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		Date authTime = parseTxnTime(isoMsg);
		Date txnAuthTime = txnCtx.getProperty(AUTH_TIME);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnAuthTime, authTime);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		Date authTime = parseTxnTime(isoMsg);
		txnCtx.setProperty(AUTH_TIME, authTime);
		return (authTime != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		Date txnTime = txnCtx.getProperty(AUTH_TIME);
		if (txnTime == null) {
			return false;
		}

		String txnTimeStr = StringUtil.format("MMddHHmmss", txnTime);
		Iso8583Operator.setField(isoMsg, FIELD_NO_TXN_TIME, txnTimeStr.substring(4));
		Iso8583Operator.setField(isoMsg, FIELD_NO_TXN_DATE, txnTimeStr.substring(0, 4));

		return true;
	}

	/**
	 * 解析授权交易时间
	 * 
	 * @param isoMsg
	 * @return
	 */
	protected Date parseTxnTime(ISOMsg isoMsg) {
		String authTxnDate = Iso8583Operator.getFieldString(isoMsg, FIELD_NO_TXN_DATE);
		String authTxnTime = Iso8583Operator.getFieldString(isoMsg, FIELD_NO_TXN_TIME);

		if (authTxnTime == null || authTxnDate == null) {
			return null;
		}

		try {
			Calendar calendar = Calendar.getInstance();

			int year = calendar.get(Calendar.YEAR);
			if ("1231".equals(authTxnDate) && calendar.get(Calendar.MONTH) == 0) {
				// 跨年的话，年份减一
				year--;
			}

			return StringUtil.parseToDate("yyyyMMddHHmmss", year + authTxnDate + authTxnTime);
		} catch (Exception e) {
			throw new RuntimeException("Fail to parse iso8583 txnDate=[" + authTxnDate + "], txnTime=[" + authTxnTime
					+ "].", e);
		}
	}
}
