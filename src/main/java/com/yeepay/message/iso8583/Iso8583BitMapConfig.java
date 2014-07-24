package com.yeepay.message.iso8583;

import java.util.Map;

/**
 * Iso8583位图配置定义类
 * 
 * @author sea.bao
 */
public class Iso8583BitMapConfig {
	/**
	 * 按照处理代码的Iso8583位图定义映射
	 */
	private Map<String, Iso8583BitMap> iso8583BitMapByProcessCode;

	/**
	 * @param processType
	 * @return
	 */
	public Iso8583BitMap getIso8583BitMap(String processCode) {
		Iso8583BitMap iso8583BitMap = iso8583BitMapByProcessCode.get(processCode);
		if (iso8583BitMap == null) {
			throw new RuntimeException("Not found the iso8583BitMap for processCode=[" + processCode + "].");
		}

		return iso8583BitMap;
	}

	/**
	 * @return the iso8583BitMapByProcessCode
	 */
	public Map<String, Iso8583BitMap> getIso8583BitMapByProcessCode() {
		return iso8583BitMapByProcessCode;
	}

	/**
	 * @param iso8583BitMapByProcessCode
	 *            the iso8583BitMapByProcessCode to set
	 */
	public void setIso8583BitMapByProcessCode(Map<String, Iso8583BitMap> iso8583BitMapByProcessCode) {
		this.iso8583BitMapByProcessCode = iso8583BitMapByProcessCode;
	}
}
