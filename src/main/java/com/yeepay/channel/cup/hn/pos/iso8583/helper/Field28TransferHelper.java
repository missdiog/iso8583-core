package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.TXN_FEE_AMT;

import java.math.BigDecimal;

import me.andpay.ti.base.AppBizException;
import me.andpay.ti.util.SimpleStringBuffer;

import org.jpos.iso.ISOMsg;

import com.yeepay.channel.cup.hn.pos.iso8583.CupHnPosIso8583FieldNos;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

/**
 * Description: 手续费域转换辅助器
 * @author Kevin
 * @Createdate 2014年7月22日.
 **/

public class Field28TransferHelper implements Iso8583FieldTransferHelper {

	public int getFieldNo() {
		return CupHnPosIso8583FieldNos.FIELD_NO_28;
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
		// [手续费符号a1 + 金额n8]
		String txnFeeAmtInfo = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		if (txnFeeAmtInfo == null || txnFeeAmtInfo.length() != 9 ) {
			// 不存在或长度不正确
			return false;
		}
		SimpleStringBuffer strBuff = new SimpleStringBuffer(txnFeeAmtInfo);
		
		// 手续费符号  C:为正, D:为负  a1 (接口报文约定手续费符号为C)
		//String txnfeeSign = strBuff.read(1);

		// 手续费金额 n8 (跳过第一位符号位读取后面剩余8位金额数据)
		String txnfee = strBuff.move(1).read(8);
		
		// 单位为分，还原为元(无小数的外币金额需特殊处理)
		BigDecimal txnfeeAmt = new BigDecimal(txnfee).movePointLeft(2);
		/*
		// 为 D 时 取反
		if ("D".equals(txnfeeSign)) {
			txnfeeAmt = txnfeeAmt.negate();
		}
		*/
		txnCtx.setProperty(TXN_FEE_AMT, txnfeeAmt);
		return (txnfeeAmt != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		return Iso8583Operator.setField(isoMsg, getFieldNo(), "0", txnCtx.getBigDecimalProperty(TXN_FEE_AMT));
	}

}
