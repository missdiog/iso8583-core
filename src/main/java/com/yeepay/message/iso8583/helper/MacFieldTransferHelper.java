package com.yeepay.message.iso8583.helper;

//import static com.yeepay.message.TxnPropNames.MAC_KEY;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_MAC;
import static com.yeepay.message.iso8583.Iso8583StandardFieldNoes.FIELD_NO_RESPONSE_CODE;

import com.yeepay.channel.cup.hn.keydata.InstKey;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583MacHelper;
import com.yeepay.message.iso8583.Iso8583Operator;

import me.andpay.ac.consts.ISO8583RespCodes;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * MAC域辅助类
 * 
 * @author alex
 */
public class MacFieldTransferHelper implements Iso8583FieldTransferHelper {
	/**
	 * MAC域编号
	 */
	private int macFieldNo = FIELD_NO_MAC;

	/**
	 * MAC助手类
	 */
	private Iso8583MacHelper macHelper;

	/**
	 * {@inheritDoc}
	 */
	public int getFieldNo() {
		return macFieldNo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String respCode = Iso8583Operator.getFieldString(isoMsg, FIELD_NO_RESPONSE_CODE);
		if (ISO8583RespCodes.SUCCESS.equals(respCode)) {
			// 成功交易验证MAC
			//String macKey = txnCtx.getProperty(MAC_KEY);
			String macKey = new String((byte[])InstKey.getMacKey(InstKey.YEEPAY_ORG_ID));
			macHelper.verifyMac(isoMsg, macKey);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		byte[] mac = Iso8583Operator.getFieldBinary(isoMsg, getFieldNo());

		return (mac != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		//String macKey = txnCtx.getProperty(MAC_KEY);
		String macKey = new String((byte[])InstKey.getMacKey(InstKey.YEEPAY_ORG_ID)); //机构代码:易宝 48255500
		byte[] mac = macHelper.calcMac(isoMsg, macKey);

		return Iso8583Operator.setField(isoMsg, getFieldNo(), mac);
	}

	/**
	 * @param macFieldNo
	 *            the macFieldNo to set
	 */
	public void setMacFieldNo(int macFieldNo) {
		this.macFieldNo = macFieldNo;
	}

	/**
	 * @param macHelper
	 *            the macHelper to set
	 */
	public void setMacHelper(Iso8583MacHelper macHelper) {
		this.macHelper = macHelper;
	}
}
