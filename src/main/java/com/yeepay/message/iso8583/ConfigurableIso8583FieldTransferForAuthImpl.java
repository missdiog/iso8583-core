package com.yeepay.message.iso8583;

import com.yeepay.message.TxnContext;
import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * 授权方的Iso8583域转换器抽象实现类
 * @author sea.bao
 */
public class ConfigurableIso8583FieldTransferForAuthImpl extends AbstractConfigurableIso8583FieldTransfer implements
		Iso8583FieldTransferForAuth {
	/**
	 * {@inheritDoc}
	 */
	public void transferFromAuthResponse(TxnContext txnCtx, int fieldNo, ISOMsg isoMsg, Iso8583BitMap iso8583BitMap)
			throws AppBizException {
		Iso8583FieldTransferHelper helper = getHelper(fieldNo);

		if (iso8583BitMap.isCheckResponseField(fieldNo)) {
			// 需要检查授权方应答域信息
			helper.checkFieldInfo(isoMsg, txnCtx, iso8583BitMap);

		} else {
			boolean existed = helper.getFieldInfo(isoMsg, txnCtx, iso8583BitMap);
			if (existed == false && iso8583BitMap.isRequiredResponseField(fieldNo)) {
				// 必须要获得域信息
				throw new RuntimeException("Miss the required field in authResponse, fieldNo=[" + fieldNo + "].");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void transferToAuthRequest(ISOMsg isoMsg, int fieldNo, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException {
		Iso8583FieldTransferHelper helper = getHelper(fieldNo);

		boolean existed = helper.setFieldInfo(isoMsg, txnCtx, iso8583BitMap);
		if (existed == false && iso8583BitMap.isRequiredRequestField(fieldNo)) {
			// 必须要设置的域信息
			throw new RuntimeException("Miss the data for the required field in authRequest, fieldNo=[" + fieldNo
					+ "].");
		}
	}
	
}
