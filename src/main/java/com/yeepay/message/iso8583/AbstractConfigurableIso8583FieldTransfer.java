package com.yeepay.message.iso8583;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

/**
 * 抽象的可配置的Iso8583转换器类
 * 
 * @author sea.bao
 */
public abstract class AbstractConfigurableIso8583FieldTransfer {
	/**
	 * Iso8583域转换器辅助器集
	 */
	protected Set<Iso8583FieldTransferHelper> helpers;

	/**
	 * Iso8583域转换器辅助器集
	 */
	protected Map<Integer, Iso8583FieldTransferHelper> helperMap = new HashMap<Integer, Iso8583FieldTransferHelper>();

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		for (Iterator<Iso8583FieldTransferHelper> it = helpers.iterator(); it.hasNext();) {
			Iso8583FieldTransferHelper helper = it.next();
			Iso8583FieldTransferHelper oHelper = helperMap.get(helper.getFieldNo());
			if (oHelper != null) {
				throw new RuntimeException(String.format(
						"Conflicting iso8583FieldTransferHelper, fieldNo=[%s], class1=[%s], class2=[%s].",
						helper.getFieldNo(), helper.getClass().getName(), oHelper.getClass().getName()));
			}

			helperMap.put(helper.getFieldNo(), helper);
		}
	}

	/**
	 * 检查字段编号集
	 * 
	 * @param fieldNoes
	 */
	public void checkFieldNoes(Set<Integer> fieldNoes) {
		for (Iterator<Integer> it = fieldNoes.iterator(); it.hasNext();) {
			Integer fieldNo = it.next();
			if (helperMap.containsKey(fieldNo) == false) {
				throw new RuntimeException("Not found the Iso8583FieldTransferHelper for fieldNo=[" + fieldNo + "].");
			}
		}
	}

	/**
	 * 获取助手类
	 * 
	 * @param fieldNo
	 * @return
	 */
	protected Iso8583FieldTransferHelper getHelper(int fieldNo) {
		Iso8583FieldTransferHelper helper = helperMap.get(fieldNo);
		if (helper == null) {
			throw new RuntimeException("Not found the Iso8583FieldTransferHelper for fieldNo=[" + fieldNo + "].");
		}

		return helper;
	}

	/**
	 * @return the helpers
	 */
	public Set<Iso8583FieldTransferHelper> getHelpers() {
		return helpers;
	}

	/**
	 * @param helpers
	 *            the helpers to set
	 */
	public void setHelpers(Set<Iso8583FieldTransferHelper> helpers) {
		this.helpers = helpers;
	}
}
