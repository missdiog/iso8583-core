package com.yeepay.message.iso8583;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ISO8583转换器抽象基类
 */
public abstract class AbstractIso8583Transfer {
	/**
	 * Iso8583位图配置
	 */
	protected Iso8583BitMapConfig iso8583BitMapConfig;

	/**
	 * 处理代码解析器
	 */
	protected ProcessCodeParser processCodeParser;

	/**
	 * 获取字段编号集
	 * @return
	 */
	protected Set<Integer> getFieldNoes() {
		Set<Integer> fieldNoes = new HashSet<Integer>();

		for (Iterator<Iso8583BitMap> it = iso8583BitMapConfig.getIso8583BitMapByProcessCode().values().iterator(); 
				it.hasNext();) {
			Iso8583BitMap bitMap = it.next();
			Integer[] bitMapArray = bitMap.getRequestBitMapArray();
			for (int i = 0; i < bitMapArray.length; i++) {
				fieldNoes.add(bitMapArray[i]);
			}

			bitMapArray = bitMap.getResponseBitMapArray();
			for (int i = 0; i < bitMapArray.length; i++) {
				fieldNoes.add(bitMapArray[i]);
			}
		}
		return fieldNoes;
	}

	/**
	 * @return the iso8583BitMapConfig
	 */
	public Iso8583BitMapConfig getIso8583BitMapConfig() {
		return iso8583BitMapConfig;
	}

	/**
	 * @param iso8583BitMapConfig
	 *            the iso8583BitMapConfig to set
	 */
	public void setIso8583BitMapConfig(Iso8583BitMapConfig iso8583BitMapConfig) {
		this.iso8583BitMapConfig = iso8583BitMapConfig;
	}
	
	/**
	 * @return the processCodeParser
	 */
	public ProcessCodeParser getProcessCodeParser() {
		return processCodeParser;
	}

	/**
	 * @param processCodeParser
	 *            the processCodeParser to set
	 */
	public void setProcessCodeParser(ProcessCodeParser processCodeParser) {
		this.processCodeParser = processCodeParser;
	}

}
