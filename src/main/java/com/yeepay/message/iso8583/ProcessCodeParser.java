package com.yeepay.message.iso8583;

import org.jpos.iso.ISOMsg;

/**
 * Description: 处理代码解析器
 * @author Kevin
 * @Createdate 2014年7月23日.
 **/

public interface ProcessCodeParser {
	/**
	 * 解析处理代码
	 * 
	 * @param isoMsg iso8583消息对象
	 * @return 处理代码
	 */
	String parseProcessCode(ISOMsg isoMsg);
	
}
