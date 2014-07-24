package com.yeepay.message.iso8583;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;

/**
 * 和授权方的iso8583域转换器定义接口
 * @author sea.bao
 */
public interface Iso8583FieldTransferForAuth extends Iso8583FieldTransfer {
	/**
	 * 将向授权方机构的请求转换成Iso8583域请求信息
	 * 
	 * @param isoMsg
	 *            iso8583消息对象
	 * @param fieldNo
	 *            域编号
	 * @param txnCtx
	 *            交易上下文
	 * @param iso8583BitMap
	 *            iso8583位图配置
	 */
	void transferToAuthRequest(ISOMsg isoMsg, int fieldNo, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException;

	/**
	 * 将授权机构的应答Iso8583域信息转换成应答
	 * 
	 * @param txnCtx
	 *            交易上下文
	 * @param fieldNo
	 *            域编号
	 * @param isoMsg
	 *            iso8583消息对象
	 * @param iso8583BitMap
	 *            iso8583位图配置
	 */
	void transferFromAuthResponse(TxnContext txnCtx, int fieldNo, ISOMsg isoMsg, Iso8583BitMap iso8583BitMap)
			throws AppBizException;
}
