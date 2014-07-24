package com.yeepay.message.iso8583;

import com.yeepay.message.TxnContext;

/**
 * Iso8583域信息转换辅助器选择器接口
 * @author alex
 */
public interface Iso8583FieldTransferHelperSelector {
	/**
	 * 根据交易上下文与位图配置获取辅助类
	 * 
	 * @param txnCtx
	 * @param iso8583BitMap
	 * @return
	 */
	Iso8583FieldTransferHelper select(TxnContext txnCtx, Iso8583BitMap iso8583BitMap);
}
