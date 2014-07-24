package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.AMT;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_AMOUNT;

import java.math.BigDecimal;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 交易金额转换器辅助类
 * 
 * @author alex
 */
public class AmountFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * 小数点偏移(2表示报文中交易金额单位为分)
	 */
	private int amtPointOffset = 2;

	/**
	 * 默认授权金额(单位为元)
	 */
	private BigDecimal defaultAuthAmount;

	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_AMOUNT;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		BigDecimal amt = Iso8583Operator.getFieldBigDecimal(isoMsg, getFieldNo());
		if (amt != null) {
			// 返回金额单位为分，还原至元(无小数的外币金额需特殊处理)
			amt = amt.movePointLeft(amtPointOffset);
		}

		BigDecimal txnAmt = txnCtx.getProperty(AMT);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnAmt, amt);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		BigDecimal amt = Iso8583Operator.getFieldBigDecimal(isoMsg, getFieldNo());
		txnCtx.setProperty(AMT, amt);

		return (amt != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		BigDecimal amt = txnCtx.getProperty(AMT);
		if (amt == null) {
			if (defaultAuthAmount == null) {
				return false;
			}

			amt = defaultAuthAmount;
			txnCtx.setProperty(AMT, amt);
		}

		// 金额到分(无小数的外币金额需特殊处理)
		return Iso8583Operator.setField(isoMsg, getFieldNo(), "0", amt.movePointRight(amtPointOffset));
	}

	/**
	 * @param amtPointOffset
	 *            the amtPointOffset to set
	 */
	public void setAmtPointOffset(int amtPointOffset) {
		this.amtPointOffset = amtPointOffset;
	}

	/**
	 * @param defaultAuthAmount
	 *            the defaultAuthAmount to set
	 */
	public void setDefaultAuthAmount(BigDecimal defaultAuthAmount) {
		this.defaultAuthAmount = defaultAuthAmount;
	}
}
