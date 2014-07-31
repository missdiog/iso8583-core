package com.yeepay.message.iso8583;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

/**
 * Iso8583位图定义类
 * 
 * @author sea.bao
 */
public class Iso8583BitMap {
	/**
	 * 分隔符
	 */
	public static final String SEP = ",";

	/**
	 * 必须后缀
	 */
	public static final String SUFFIX_REQUIRED = "*";

	/**
	 * 检查后缀
	 */
	public static final String SUFFIX_CHECK = "=";

	/**
	 * 请求位图矩阵
	 */
	private Integer[] requestBitMapArray;

	/**
	 * 应答位图矩阵
	 */
	private Integer[] responseBitMapArray;

	/**
	 * 检查应答域编号集
	 */
	private Set<Integer> checkResponseFieldNoes = new HashSet<Integer>();

	/**
	 * 必须的应答域编号集
	 */
	private Set<Integer> requiredResponseFieldNoes = new HashSet<Integer>();

	/**
	 * 检查请求域编号集
	 */
	private Set<Integer> checkRequestFieldNoes = new HashSet<Integer>();

	/**
	 * 必须的请求域编号集
	 */
	private Set<Integer> requiredRequestFieldNoes = new HashSet<Integer>();

	/**
	 * 请求位图串
	 */
	private String requestBitMap;

	/**
	 * 应答位图串
	 */
	private String responseBitMap;

	/**
	 * 请求消息类型
	 */
	private String requestMsgType;

	/**
	 * 应答消息类型
	 */
	private String responseMsgType;

	/**
	 * 应答消息处理码
	 */
	private String processCode;

	/**
	 * 服务点输入方式
	 */
	private String serviceEntryMode;
	
	/**
	 * 服务点条件码
	 */
	private String serviceConditionCode;
	
	/**
	 * 交易类型码
	 */
	private String txnTypeCode;

	/**
	 * 网络管理信息码
	 */
	private String networkManCode;
	
	/**
	 * 发送机构代码
	 */
	private String sendInstitutionId;
	
	/**
	 * 商户类型
	 */
	private String merchantType;

	/**
	 * 解析表达式，获取请求和响应位图
	 */
	@PostConstruct
	public void init() {
		requestBitMapArray = parseBitMap(requestBitMap, true);
		responseBitMapArray = parseBitMap(responseBitMap, false);
	}

	/**
	 * 解析位图
	 * 
	 * @param bitMap
	 * @param reqFlag
	 * @return
	 */
	private Integer[] parseBitMap(String bitMap, boolean reqFlag) {
		StringTokenizer st = new StringTokenizer(bitMap, SEP);
		List<Integer> bitMapList = new ArrayList<Integer>();

		while (st.hasMoreTokens()) {
			String sfn = st.nextToken().trim();
			boolean checkFlag = false;
			boolean requiredFlag = false;

			if (sfn.endsWith(SUFFIX_CHECK)) {
				checkFlag = true;
				sfn = sfn.substring(0, sfn.length() - 1);
			} else if (sfn.endsWith(SUFFIX_REQUIRED)) {
				requiredFlag = true;
				sfn = sfn.substring(0, sfn.length() - 1);
			}

			Integer fieldNo = Integer.valueOf(sfn, 10);
			bitMapList.add(fieldNo);

			if (checkFlag) {
				if (reqFlag) {
					checkRequestFieldNoes.add(fieldNo);
				} else {
					checkResponseFieldNoes.add(fieldNo);
				}
			}

			if (requiredFlag) {
				if (reqFlag) {
					requiredRequestFieldNoes.add(fieldNo);
				} else {
					requiredResponseFieldNoes.add(fieldNo);
				}
			}
		}

		return bitMapList.toArray(new Integer[bitMapList.size()]);
	}

	/**
	 * 请求域是否必须存在
	 * 
	 * @param fieldNo
	 * @return
	 */
	public boolean isRequiredRequestField(Integer fieldNo) {
		return this.requiredRequestFieldNoes.contains(fieldNo);
	}

	/**
	 * 请求域是否需要检查
	 * 
	 * @param fieldNo
	 * @return
	 */
	public boolean isCheckRequestField(Integer fieldNo) {
		return this.checkRequestFieldNoes.contains(fieldNo);
	}

	/**
	 * 响应域是否必须存在
	 * 
	 * @param fieldNo
	 * @return
	 */
	public boolean isRequiredResponseField(Integer fieldNo) {
		return this.requiredResponseFieldNoes.contains(fieldNo);
	}

	/**
	 * 响应域是否需要检查
	 * 
	 * @param fieldNo
	 * @return
	 */
	public boolean isCheckResponseField(Integer fieldNo) {
		return this.checkResponseFieldNoes.contains(fieldNo);
	}

	/**
	 * @return the requestBitMapArray
	 */
	public Integer[] getRequestBitMapArray() {
		return requestBitMapArray;
	}

	/**
	 * @return the responseBitMapArray
	 */
	public Integer[] getResponseBitMapArray() {
		return responseBitMapArray;
	}

	/**
	 * @return the requestBitMap
	 */
	public String getRequestBitMap() {
		return requestBitMap;
	}

	/**
	 * @param requestBitMap
	 *            the requestBitMap to set
	 */
	public void setRequestBitMap(String requestBitMap) {
		this.requestBitMap = requestBitMap;
	}

	/**
	 * @return the responseBitMap
	 */
	public String getResponseBitMap() {
		return responseBitMap;
	}

	/**
	 * @param responseBitMap
	 *            the responseBitMap to set
	 */
	public void setResponseBitMap(String responseBitMap) {
		this.responseBitMap = responseBitMap;
	}

	/**
	 * @return the requestMsgType
	 */
	public String getRequestMsgType() {
		return requestMsgType;
	}

	/**
	 * @param requestMsgType
	 *            the requestMsgType to set
	 */
	public void setRequestMsgType(String requestMsgType) {
		this.requestMsgType = requestMsgType;
	}

	/**
	 * @return the responseMsgType
	 */
	public String getResponseMsgType() {
		return responseMsgType;
	}

	/**
	 * @param responseMsgType
	 *            the responseMsgType to set
	 */
	public void setResponseMsgType(String responseMsgType) {
		this.responseMsgType = responseMsgType;
	}

	/**
	 * @return the processCode
	 */
	public String getProcessCode() {
		return processCode;
	}

	/**
	 * @param processCode
	 *            the processCode to set
	 */
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	/**
	 * @return the serviceEntryMode
	 */
	public String getServiceEntryMode() {
		return serviceEntryMode;
	}

	/**
	 * @param serviceEntryMode
	 *            the serviceEntryMode to set
	 */
	public void setServiceEntryMode(String serviceEntryMode) {
		this.serviceEntryMode = serviceEntryMode;
	}

	/**
	 * @return the serviceConditionCode
	 */
	public String getServiceConditionCode() {
		return serviceConditionCode;
	}

	/**
	 * @param serviceConditionCode
	 *            the serviceConditionCode to set
	 */
	public void setServiceConditionCode(String serviceConditionCode) {
		this.serviceConditionCode = serviceConditionCode;
	}

	/**
	 * @return the txnTypeCode
	 */
	public String getTxnTypeCode() {
		return txnTypeCode;
	}

	/**
	 * @param txnTypeCode
	 *            the txnTypeCode to set
	 */
	public void setTxnTypeCode(String txnTypeCode) {
		this.txnTypeCode = txnTypeCode;
	}
	
	/**
	 * @return the networkManCode
	 */
	public String getNetworkManCode() {
		return networkManCode;
	}

	/**
	 * @param networkManCode
	 *            the networkManCode to set
	 */
	public void setNetworkManCode(String networkManCode) {
		this.networkManCode = networkManCode;
	}
	
	/**
	 * @return the sendInstitutionId
	 */
	public String getSendInstitutionId() {
		return sendInstitutionId;
	}

	/**
	 * @param sendInstitutionId
	 *            the sendInstitutionId to set
	 */
	public void setSendInstitutionId(String sendInstitutionId) {
		this.sendInstitutionId = sendInstitutionId;
	}

	/**
	 * @return the merchantType
	 */
	public String getMerchantType() {
		return merchantType;
	}

	/**
	 * @param merchantType
	 *            the merchantType to set
	 */
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
}
