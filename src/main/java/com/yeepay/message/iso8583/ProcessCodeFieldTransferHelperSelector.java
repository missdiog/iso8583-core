package com.yeepay.message.iso8583;

import java.util.Map;

import com.yeepay.message.TxnPropNames;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import me.andpay.ti.util.MapUtil;

import com.yeepay.message.TxnContext;

/**
 * Description: 基于处理代码的域辅助选择器实现类
 * @author Kevin
 * @Createdate 2014年7月23日.
 **/

public class ProcessCodeFieldTransferHelperSelector implements Iso8583FieldTransferHelperSelector {
	/**
	 * 默认域辅助类
	 */
	private Iso8583FieldTransferHelper defaultHelper;

	/**
	 * 域辅助映射[交易类型 - 域辅助类]
	 */
	//private Map<String, Iso8583FieldTransferHelper> txnType2Helper;
	/**
	 * 域辅助映射[处理代码 - 域辅助类]
	 */
	private Map<String, Iso8583FieldTransferHelper> processCode2Helper;

	/**
	 * {@inheritDoc}
	 */
	public Iso8583FieldTransferHelper select(TxnContext txnCtx, Iso8583BitMap iso8583BitMap) {
		//String txnType = txnCtx.getProperty(TxnPropNames.TXN_TYPE);
		String processCode = txnCtx.getProperty(TxnPropNames.PROCESS_CODE);

		// 优先根据交易类型选择辅助类
		//Iso8583FieldTransferHelper helper = MapUtil.get(txnType2Helper, txnType);
		
		// 优先根据处理代码选择辅助类
		Iso8583FieldTransferHelper helper = MapUtil.get(processCode2Helper, processCode);
		// 如果找不到则使用默认辅助类
		return (helper != null ? helper : defaultHelper);
	}

	/**
	 * @param defaultHelper
	 *            the defaultHelper to set
	 */
	public void setDefaultHelper(Iso8583FieldTransferHelper defaultHelper) {
		this.defaultHelper = defaultHelper;
	}

	/**
	 * @param processCode2Helper
	 *            the processCode2Helper to set
	 */
	public void setProcessCode2Helper(Map<String, Iso8583FieldTransferHelper> processCode2Helper) {
		this.processCode2Helper = processCode2Helper;
	}

}
