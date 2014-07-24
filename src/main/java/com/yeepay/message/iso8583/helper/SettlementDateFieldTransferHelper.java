package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AUTH_SETTLE_DATE;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_SETTLEMENT_DATE;

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
 * 清算日期转换器辅助类
 * 
 * @author alex
 */
public class SettlementDateFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_SETTLEMENT_DATE;
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
		// MMDD
		String authSettleDateMMDD = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		if (authSettleDateMMDD != null) {
			Date authSettleDate = StringUtil.parseToDate("yyyyMMdd", Calendar.getInstance().get(Calendar.YEAR)
					+ authSettleDateMMDD);
			txnCtx.setProperty(AUTH_SETTLE_DATE, authSettleDate);
		}

		return (authSettleDateMMDD != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return false;
	}
}
