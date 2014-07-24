package com.yeepay.message.iso8583;

import java.util.Set;

/**
 * ISO8583字段转换接口
 * 
 * @author sea.bao
 */
public interface Iso8583FieldTransfer {
	/**
	 * 启动时检查支持的域编号
	 * @param fieldNoes
	 */
	void checkFieldNoes(Set<Integer> fieldNoes);
}
