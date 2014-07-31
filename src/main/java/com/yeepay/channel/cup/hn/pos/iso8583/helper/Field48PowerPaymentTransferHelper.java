package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.MONTH_OWE_BILLS;
import static com.yeepay.message.TxnPropNames.OWE_MONTHS;
import static com.yeepay.message.TxnPropNames.POWER_BILLS_REC_NO;
import static com.yeepay.message.TxnPropNames.TOTAL_PAY_BILLS;
import static com.yeepay.message.TxnPropNames.TOTAL_PENALTY_CONTRACT;

import java.math.BigDecimal;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

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
public class Field48PowerPaymentTransferHelper implements Iso8583FieldTransferHelper {

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
		throw new RuntimeException("Unexpected get, fieldNo=" + getFieldNo());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String value = "1";
		if(txnCtx.getStringProperty(POWER_BILLS_REC_NO) != null){
			BigDecimal oweBills = txnCtx.getBigDecimalProperty(MONTH_OWE_BILLS);//欠费金额
			Integer oweMonths = txnCtx.getIntegerProperty(OWE_MONTHS);//欠费月数
			String powerBillsRecNo = txnCtx.getStringProperty(POWER_BILLS_REC_NO);//应收电费标识号
			BigDecimal totalPayBills = txnCtx.getBigDecimalProperty(TOTAL_PAY_BILLS);//电费金额
			BigDecimal totalPenaltyContract = txnCtx.getBigDecimalProperty(TOTAL_PENALTY_CONTRACT);//违约金
			
			StringBuffer sb = new StringBuffer();
			sb.append(String.format("%-12s", oweBills));//N12
			sb.append(String.format("%-2s", oweMonths));//N2
			sb.append(String.format("%-16s", powerBillsRecNo));//N16
			sb.append(String.format("%-12s", totalPayBills));//N12
			sb.append(String.format("%-12s", totalPenaltyContract));//N12
			value = sb.toString();
		}
		return Iso8583Operator.setField(isoMsg, getFieldNo(), value);
	}

}
