package com.yeepay.message.convertor;

import com.yeepay.message.TxnContext;

/**
 * 交易消息转换服务接口类
 * @author alex
 */
public interface MessageConvertor<REQ, RESP> {
	/**
	 * 根据交易请求数据上下文组包得到交易消息报文
	 * 
	 * @param txnCtx
	 * @return
	 */
	REQ pack(TxnContext txnCtx);
	
	/**
	 * 根据交易应答消息报文解包得到交易数据上下文
	 * 
	 * @param txnMsg
	 * @return
	 */
	void unpack(RESP txnMsg, TxnContext txnCtx);
}
