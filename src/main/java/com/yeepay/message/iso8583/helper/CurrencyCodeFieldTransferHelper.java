package com.yeepay.message.iso8583.helper;

import static com.yeepay.message.TxnPropNames.CUR;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_CURRENCY_CODE;

import java.util.HashMap;
import java.util.Map;

import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import me.andpay.ac.consts.CurrencyCodes;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 货币代码转换器辅助类
 * 
 * @author alex
 */
public class CurrencyCodeFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * 币别映射
	 */
	private Map<String, String> curMap;

	/**
	 * 构造函数
	 */
	public CurrencyCodeFieldTransferHelper() {
		// 默认映射
		curMap = new HashMap<String, String>();
		curMap.put(CurrencyCodes.CNY, "156");
	}

	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return FIELD_NO_CURRENCY_CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String bankCurCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String curCode = revertCurrencyCode(bankCurCode);
		String txnCurCode = txnCtx.getProperty(CUR);

		Iso8583Operator.checkFieldInfo(getFieldNo(), txnCurCode, curCode);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String bankCurCode = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String curCode = revertCurrencyCode(bankCurCode);
		txnCtx.setProperty(CUR, curCode);

		return (curCode != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String curCode = txnCtx.getProperty(CUR);
		return Iso8583Operator.setField(isoMsg, getFieldNo(), curMap.get(curCode));
	}

	/**
	 * 反向查找币别
	 * 
	 * @param bankCurCode
	 * @return
	 */
	private String revertCurrencyCode(String bankCurCode) {
		for (Map.Entry<String, String> curEntry : curMap.entrySet()) {
			if (curEntry.getValue().equals(bankCurCode)) {
				return curEntry.getKey();
			}
		}

		return null;
	}

	/**
	 * @param curMap
	 *            the curMap to set
	 */
	public void setCurMap(Map<String, String> curMap) {
		this.curMap = curMap;
	}
}
