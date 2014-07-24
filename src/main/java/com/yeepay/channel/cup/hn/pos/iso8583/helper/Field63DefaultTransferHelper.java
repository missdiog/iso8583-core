package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static me.andpay.ac.consts.CardAssoces.AMERICAN_EXPRESS;
import static me.andpay.ac.consts.CardAssoces.CHINA_UNION_PAY;
import static me.andpay.ac.consts.CardAssoces.DINERS_CLUB;
import static me.andpay.ac.consts.CardAssoces.JCB;
import static me.andpay.ac.consts.CardAssoces.MASTER_CARD;
import static me.andpay.ac.consts.CardAssoces.VISA;

import java.util.HashMap;
import java.util.Map;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.channel.cup.hn.pos.iso8583.CupHnPosIso8583FieldNos;
import com.yeepay.message.TxnContext;
import com.yeepay.message.TxnPropNames;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

/**
 * 自定义域: 国际信用卡公司代码
 * @author alex
 */
public class Field63DefaultTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * 国际信用卡公司代码 <-> 发卡组织
	 */
	private final static Map<String, String> FCC_2_CA = new HashMap<String, String>();
	static {
		FCC_2_CA.put("CUP", CHINA_UNION_PAY);
		FCC_2_CA.put("VIS", VISA);
		FCC_2_CA.put("MCC", MASTER_CARD);
		FCC_2_CA.put("JCB", JCB);
		FCC_2_CA.put("DCC", DINERS_CLUB);
		FCC_2_CA.put("AMX", AMERICAN_EXPRESS);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return CupHnPosIso8583FieldNos.FIELD_NO_63;
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
		String fcc = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		String cardAssoc = FCC_2_CA.get(fcc);
		if (cardAssoc != null) {
			txnCtx.setProperty(TxnPropNames.CARD_ASSOC, cardAssoc);
		}
		return (fcc != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String cardAssoc = txnCtx.getStringProperty(TxnPropNames.CARD_ASSOC);
		// 国际信用卡公司代码an3
		String fcc = null;
		for (Map.Entry<String, String> entry : FCC_2_CA.entrySet()) {
			if (entry.getValue().equals(cardAssoc)) {
				fcc = entry.getKey();
				break;
			}
		}
		return Iso8583Operator.setField(isoMsg, getFieldNo(), fcc);
	}
}
