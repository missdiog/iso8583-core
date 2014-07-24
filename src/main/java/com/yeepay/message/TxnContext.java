package com.yeepay.message;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.andpay.ti.util.BeanUtil;

/**
 * 交易数据上下文对象
 * 
 * @author alex
 */
public class TxnContext {
	/**
	 * 记录修改的属性名
	 */
	private boolean recordModifiedProps = false;

	/**
	 * 修改过的属性
	 */
	private Set<String> modifiedKeys = new HashSet<String>();

	/**
	 * 数据集[属性名 : 属性值]
	 */
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	/**
	 * 设置属性值(计入修改属性列表)
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public Object setProperty(String propName, Object propValue) {
		if (recordModifiedProps) {
			modifiedKeys.add(propName);
		}
		return dataMap.put(propName, propValue);
	}

	/**
	 * 获取属性值
	 * 
	 * @param propName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProperty(String propName) {
		return (T) dataMap.get(propName);
	}

	/**
	 * 获得字符串属性值
	 * 
	 * @param propName
	 * @return
	 */
	public String getStringProperty(String propName) {
		return (String) dataMap.get(propName);
	}

	/**
	 * 获得数字属性值
	 * 
	 * @param propName
	 * @return
	 */
	public BigDecimal getBigDecimalProperty(String propName) {
		return (BigDecimal) dataMap.get(propName);
	}

	/**
	 * 获得整数属性值
	 * 
	 * @param propName
	 * @return
	 */
	public Integer getIntegerProperty(String propName) {
		return (Integer) dataMap.get(propName);
	}

	/**
	 * 获得日期属性值
	 * 
	 * @param propName
	 * @return
	 */
	public Date getDateProperty(String propName) {
		return (Date) dataMap.get(propName);
	}

	/**
	 * 获得字节数组属性值
	 * 
	 * @param propName
	 * @return
	 */
	public byte[] getByteArrayProperty(String propName) {
		return (byte[]) dataMap.get(propName);
	}

	/**
	 * 后续的属性设置操作均会被记录到修改内容中
	 */
	public void recordSubsequentModification() {
		recordModifiedProps = true;
	}

	/**
	 * 是否记录修改的属性
	 * 
	 * @return
	 */
	public boolean isRecordModifiedProps() {
		return recordModifiedProps;
	}

	/**
	 * 获取包含修改过的属性的交易传输上下文
	 * 
	 * @return
	 */
	public TxnContext getModifiedProperties() {
		Map<String, Object> modDataMap = new HashMap<String, Object>();
		for (String key : modifiedKeys) {
			modDataMap.put(key, dataMap.get(key));
		}

		TxnContext txnCtx = new TxnContext();
		txnCtx.dataMap = modDataMap;

		return txnCtx;
	}

	/**
	 * 重置修改的内容
	 * 
	 * @return
	 */
	public Set<String> resetModifiedKeys() {
		Set<String> lastModifiedKeys = modifiedKeys;
		this.modifiedKeys = new HashSet<String>();

		return lastModifiedKeys;
	}

	/**
	 * 从source对象中读取属性值
	 * 
	 * @param source
	 *            源数据
	 * @param ignoredProps
	 *            忽略的属性名
	 */
	public void readObject(Object source, String... ignoredProps) {
		Map<String, Object> objData = BeanUtil.asMap(source, ignoredProps);
		if (objData != null) {
			for (Map.Entry<String, Object> entry : objData.entrySet()) {
				setProperty(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * 将内部数据复制到target对象同名属性中
	 * 
	 * @param target
	 *            目标对象
	 * @param ignoredProps
	 *            忽略的属性名
	 */
	public void writeObject(Object target, String... ignoredProps) {
		writeObject(target, false, ignoredProps);
	}

	/**
	 * 将内部数据复制到target对象同名属性中
	 * 
	 * @param target
	 *            目标对象
	 * @param modifiedPropOnly
	 *            是否只复制变更过的属性
	 * @param ignoredProps
	 *            忽略的属性名
	 */
	public void writeObject(Object target, boolean modifiedPropOnly, String... ignoredProps) {
		Map<String, Object> dataMap = this.dataMap;
		if (modifiedPropOnly) {
			dataMap = getModifiedProperties().dataMap;
		}

		BeanUtil.copyProperties(dataMap, target, ignoredProps);
	}
}
