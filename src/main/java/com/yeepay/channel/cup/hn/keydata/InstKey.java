package com.yeepay.channel.cup.hn.keydata;

import java.util.HashMap;
import java.util.Map;

import com.yeepay.message.TxnPropNames;

/**
 * 保存机构密钥
 * @author LiuDawei
 *
 */
public class InstKey {
	
	public final static String YEEPAY_ORG_ID = "03085511";  //48255500
	
	/**
	 * 机构密钥map,key=机构号
	 */
	private static Map<String,Map<String,Object>> instKeyMap = new HashMap<String, Map<String,Object>>();
	
	private InstKey(){}
	
	/**
	 * 更新对应机构的所有密钥
	 * @param instCode 机构编号
	 * @param keyMap 密钥map
	 */
	public static void updateAllKeyByInstCode(String instCode, Map<String,Object> keyMap){
		instKeyMap.put(instCode, keyMap);
	} 
	
	/**
	 * 获取机构对应的MAC密钥
	 * @param instCode
	 * @return
	 */
	public static Object getMacKey(String instCode){
		Object macKey = null;
		Map<String,Object> keyMap = instKeyMap.get(instCode);
		if(keyMap != null){
			macKey = keyMap.get(TxnPropNames.MAC_KEY);
		}
		return macKey;
	}
	
	/**
	 * 获取机构对应的PIN密钥
	 * @param instCode
	 * @return
	 */
	public static Object getPinKey(String instCode){
		Object macKey = null;
		Map<String,Object> keyMap = instKeyMap.get(instCode);
		if(keyMap != null){
			macKey = keyMap.get(TxnPropNames.PIN_KEY);
		}
		return macKey;
	}
	
	/**
	 * 获取指定机构下的指定密钥
	 * @param instCode 机构编号
	 * @param keyName 密钥名称
	 * @return
	 */
	public static Object getKeyByName(String instCode, String keyName){
		Object key = null;
		Map<String,Object> keyMap = instKeyMap.get(instCode);
		if(keyMap != null){
			key = keyMap.get(keyName);
		}
		return key;
	}
	
	/**
	 * 更新指定机构下的MAC密钥
	 * @param instCode
	 * @param macKey
	 */
	public static void updateMacKey(String instCode, Object macKey){
		Map<String,Object> keyMap = instKeyMap.get(instCode);
		if(keyMap == null){
			keyMap = new HashMap<String, Object>();
		}
		keyMap.put(TxnPropNames.MAC_KEY, macKey);
		instKeyMap.put(instCode, keyMap);
	}
	
	/**
	 * 更新指定机构下的Pin密钥
	 * @param instCode
	 * @param pinKey
	 */
	public static void updatePinKey(String instCode, Object pinKey){
		Map<String,Object> keyMap = instKeyMap.get(instCode);
		if(keyMap == null){
			keyMap = new HashMap<String, Object>();
		}
		keyMap.put(TxnPropNames.PIN_KEY, pinKey);
		instKeyMap.put(instCode, keyMap);
	}
	
	/**
	 * 更新指定机构下的指定密钥信息
	 * @param instCode 机构编号
	 * @param keyName 密钥名称
	 * @param keyValue 密钥值
	 */
	public static void updateKeyByName(String instCode, String keyName, Object keyValue){
		Map<String,Object> keyMap = instKeyMap.get(instCode);
		if(keyMap == null){
			keyMap = new HashMap<String, Object>();
		}
		keyMap.put(keyName, keyValue);
		instKeyMap.put(instCode, keyMap);
	}
}
