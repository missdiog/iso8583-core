package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.BALANCE;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_BALANCE;

import java.math.BigDecimal;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ti.base.AppBizException;
import me.andpay.ti.util.SimpleStringBuffer;

import org.jpos.iso.ISOMsg;

/**
 * 账户余额转换器辅助类
 * 
 * @author alex
 */
public class BalanceFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_BALANCE;
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
		// [账户类型n2 + 余额类型n2 + 货币代码n3 + 余额符号a1 + 余额n12] * (1 or 2)
		String balanceInfo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		if (balanceInfo == null || (balanceInfo.length() != 20 && balanceInfo.length() != 40)) {
			// 不存在或长度不正确
			return false;
		}

		SimpleStringBuffer strBuff = new SimpleStringBuffer(balanceInfo);
		if (strBuff.length() == 40) {
			// 长度为40时，前20为账面余额信息，后20为可用余额信息，查询只需可用余额信息
			// 长度为20时，为可用余额信息
			strBuff.setReadPosition(20);
		}

		// 余额正负 C:为正，D:为负 a1
		String balanceSign = strBuff.move(7).read(1);

		// 余额金额 n12
		String balance = strBuff.read(12);

		// 单位为分，还原为元(无小数的外币金额需特殊处理)
		BigDecimal balanceAmt = new BigDecimal(balance).movePointLeft(2);
		if ("D".equals(balanceSign)) {
			balanceAmt = balanceAmt.negate();
		}

		txnCtx.setProperty(BALANCE, balanceAmt);

		return (balanceAmt != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return false;
	}
}
