package com.yeepay.message.iso8583;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583BitMapConfig;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;
import com.yeepay.message.iso8583.ProcessCodeTokenizer;

import me.andpay.ti.util.StringUtil;

import org.jpos.iso.ISOMsg;

/**
 * Description: ****
 * @author Kevin
 * @Createdate 2014年7月23日.
 **/

public class ConfigurableProcessCodeParserImpl implements ProcessCodeParser {

	/**
	 * Iso8583位图配置
	 */
	protected Iso8583BitMapConfig iso8583BitMapConfig;

	/**
	 * 检查交易类型映射 (默认为true, 对于无法区分的映射应该使用false)
	 */
	//protected boolean checkTxnTypeMap = true;
	
	/**
	 * 检查处理代码映射 (默认为true, 对于无法区分的映射应该使用false)
	 */
	protected boolean checkProcessCodeMap = true;

	/**
	 * 服务点条件代码作为映射条件(默认为是)
	 */
	protected boolean srvCondCodeAsMapCond = true;

	/**
	 * 交易类型代码作为映射条件(默认为否)
	 */
	//protected boolean txnTypeCodeAsMapCond = false;

	/**
	 * 网络管理代码作为映射条件(默认为否)
	 */
	//protected boolean networkManCodeAsMapCond = false;

	/**
	 * 默认输入条件代码
	 */
	protected String defaultSrvCondCode;

	/**
	 * 默认交易类型码
	 */
	//protected String defaultTxnTypeCode;

	/**
	 * 默认网络管理代码
	 */
	//protected String defaultNetworkManCode;

	/**
	 * 处理代码映射
	 */
	protected Map<String, String> processCodeMap = new HashMap<String, String>();

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		doInit();
	}

	/**
	 * 初始化 processCode 映射
	 */
	protected void doInit() {
		for (String processCode : iso8583BitMapConfig.getIso8583BitMapByProcessCode().keySet()) {
			Iso8583BitMap iso8583BitMap = iso8583BitMapConfig.getIso8583BitMap(processCode);

			// 请求交易类型
			// 消息类型 + 处理码 + [服务点条件代码]
			ProcessCodeTokenizer reqToken = new ProcessCodeTokenizer();

			// 响应交易类型
			// 消息类型 + 处理码 + [服务点条件代码]
			ProcessCodeTokenizer respToken = new ProcessCodeTokenizer();

			// 消息类型
			reqToken.append(iso8583BitMap.getRequestMsgType());
			respToken.append(iso8583BitMap.getResponseMsgType());

			// 处理码 (null会被忽略)
			reqToken.append(iso8583BitMap.getProcessCode());
			respToken.append(iso8583BitMap.getProcessCode());

			if (srvCondCodeAsMapCond) {
				// 服务点条件代码 (null会被忽略)
				reqToken.append(StringUtil.defaultString(iso8583BitMap.getServiceConditionCode(), defaultSrvCondCode));
				respToken.append(StringUtil.defaultString(iso8583BitMap.getServiceConditionCode(), defaultSrvCondCode));
			}
			
			/*
			if (txnTypeCodeAsMapCond) {
				// 交易类型码 (null会被忽略)
				reqToken.append(StringUtil.defaultString(iso8583BitMap.getTxnTypeCode(), defaultTxnTypeCode));
				respToken.append(StringUtil.defaultString(iso8583BitMap.getTxnTypeCode(), defaultTxnTypeCode));
			}

			if (networkManCodeAsMapCond) {
				// 网络管理码 (null会被忽略)
				reqToken.append(StringUtil.defaultString(iso8583BitMap.getNetworkManCode(), defaultNetworkManCode));
				respToken.append(StringUtil.defaultString(iso8583BitMap.getNetworkManCode(), defaultNetworkManCode));
			}
			 */

			String origProCode = processCodeMap.put(reqToken.getToken(), processCode);
			if (origProCode != null && checkProcessCodeMap) {
				throw new RuntimeException(String.format(
						"Duplicated request process code token, token=%s, processCode1=%s, processCode2=%s", reqToken.getToken(),
						processCode, origProCode));
			}

			origProCode = processCodeMap.put(respToken.getToken(), processCode);
			if (origProCode != null && checkProcessCodeMap) {
				throw new RuntimeException(String.format(
						"Duplicated response process code token, token=%s, processCode1=%s, processCode2=%s", respToken.getToken(),
						processCode, origProCode));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String parseProcessCode(ISOMsg isoMsg) {
		ProcessCodeTokenizer token = new ProcessCodeTokenizer();
		// 消息类型 域0
		token.append(Iso8583Operator.getFieldString(isoMsg, Iso8583StandardFieldNoes.FIELD_NO_MSG_TYPE));
		// 处理码 域3
		token.append(Iso8583Operator.getFieldString(isoMsg, Iso8583StandardFieldNoes.FIELD_NO_PROCESS_CODE));

		if (srvCondCodeAsMapCond) {
			// 服务点条件代码 域25
			String srvCondCode = Iso8583Operator.getFieldString(isoMsg, Iso8583StandardFieldNoes.FIELD_NO_SERVICE_CONDITION_CODE);
			token.append(StringUtil.defaultString(srvCondCode, defaultSrvCondCode));
		}
		/*
		if (txnTypeCodeAsMapCond || networkManCodeAsMapCond) {
			String txnInfo = Iso8583Operator.getFieldString(isoMsg, Iso8583StandardFieldNoes.FIELD_NO_ADDI_TXN_INFO);
			if (txnInfo != null) {
				if (txnTypeCodeAsMapCond) {
					// 交易类型码 域60.1 位置0-2
					token.append(txnInfo.length() >= 2 ? txnInfo.substring(0, 2) : defaultTxnTypeCode);
				}
				
				if (networkManCodeAsMapCond) {
					// 网络管理信息码 域60.3 位置8-11
					token.append(txnInfo.length() >= 11 ? txnInfo.substring(8, 11) : defaultNetworkManCode);
				}
			}
		}
		*/
		
		// 消息类型 + 处理码 + [服务点条件代码]
		String processCode = processCodeMap.get(token.getToken());
		if (processCode == null) {
			throw new RuntimeException("Unable parse process code, key=[" + token.getToken() + "].");
		}
		return processCode;
	}

	/**
	 * @param checkProcessCodeMap
	 *            the checkProcessCodeMap to set
	 */
	public void setCheckProcessCodeMap(boolean checkProcessCodeMap) {
		this.checkProcessCodeMap = checkProcessCodeMap;
	}

	/**
	 * @param srvCondCodeAsMapCond
	 *            the srvCondCodeAsMapCond to set
	 */
	public void setSrvCondCodeAsMapCond(boolean srvCondCodeAsMapCond) {
		this.srvCondCodeAsMapCond = srvCondCodeAsMapCond;
	}
	
	/**
	 * @param txnTypeCodeAsMapCond
	 *            the txnTypeCodeAsMapCond to set
	 */
//	public void setTxnTypeCodeAsMapCond(boolean txnTypeCodeAsMapCond) {
//		this.txnTypeCodeAsMapCond = txnTypeCodeAsMapCond;
//	}

	/**
	 * @param networkManCodeAsMapCond
	 *            the networkManCodeAsMapCond to set
	 */
//	public void setNetworkManCodeAsMapCond(boolean networkManCodeAsMapCond) {
//		this.networkManCodeAsMapCond = networkManCodeAsMapCond;
//	}

	/**
	 * @param defaultSrvCondCode
	 *            the defaultSrvCondCode to set
	 */
	public void setDefaultSrvCondCode(String defaultSrvCondCode) {
		this.defaultSrvCondCode = defaultSrvCondCode;
	}

	/**
	 * @param defaultTxnTypeCode
	 *            the defaultTxnTypeCode to set
	 */
//	public void setDefaultTxnTypeCode(String defaultTxnTypeCode) {
//		this.defaultTxnTypeCode = defaultTxnTypeCode;
//	}

	/**
	 * @param defaultNetworkManCode
	 *            the defaultNetworkManCode to set
	 */
//	public void setDefaultNetworkManCode(String defaultNetworkManCode) {
//		this.defaultNetworkManCode = defaultNetworkManCode;
//	}

	/**
	 * @param iso8583BitMapConfig
	 *            the iso8583BitMapConfig to set
	 */
	public void setIso8583BitMapConfig(Iso8583BitMapConfig iso8583BitMapConfig) {
		this.iso8583BitMapConfig = iso8583BitMapConfig;
	}

	/**
	 * @param processCodeMap
	 *            the processCodeMap to set
	 */
	public void setProcessCodeMap(Map<String, String> processCodeMap) {
		this.processCodeMap = processCodeMap;
	}

}
