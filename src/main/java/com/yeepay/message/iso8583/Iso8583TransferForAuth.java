package com.yeepay.message.iso8583;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;

/**
 * 授权方的Iso8583转换器定义接口
 * @author sea.bao
 */
public interface Iso8583TransferForAuth {
	/**
	 * 将向授权方的请求转换成Iso8583的请求
	 * 
	 * @param txnCtx
	 * @return Iso8583对象
	 * @throws AppBizException
	 */
	ISOMsg transferToAuthRequest(TxnContext txnCtx) throws AppBizException;

	/**
	 * 将授权方的Iso8583应答转换成应答
	 * 
	 * @param txnCtx
	 * @param isoMsg
	 * @throws AppBizException
	 */
	void transferFromAuthResponse(TxnContext txnCtx, ISOMsg isoMsg) throws AppBizException;
}
