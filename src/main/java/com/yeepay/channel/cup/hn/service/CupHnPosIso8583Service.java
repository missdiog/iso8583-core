package com.yeepay.channel.cup.hn.service;

import me.andpay.ti.base.AppBizException;

import com.yeepay.message.TxnContext;

/**
 * Description: ****
 * @author Kevin
 * @Createdate 2014年7月25日.
 **/
public interface CupHnPosIso8583Service {
	
	/**
	 * 8583报文封装(TxnContext to ISO8583)
	 * @param TxnContext 上下文对象
	 * @return ISO 8583 Message
	 */
	public byte[] processCtx2IsoMsg(TxnContext ctx) throws AppBizException;
	
	/**
	 * 8583报文解析(ISO8583 to TxnContext)
	 * @param ISO 8583 Message
	 * @return TxnContext 上下文对象
	 */
	public TxnContext processIsoMsg2Ctx(byte[] isoMsg) throws AppBizException;
	
	/**
	 * 8583报文解析(ISO8583 to TxnContext)
	 * @param iso8583Msg
	 * @param txnContext 上下文
	 * @return
	 */
	public TxnContext processIsoMsg2Ctx(byte[] isoMsg, TxnContext ctx) throws AppBizException;
	
}
