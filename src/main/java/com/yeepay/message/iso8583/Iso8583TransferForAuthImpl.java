package com.yeepay.message.iso8583;

import static com.yeepay.message.TxnPropNames.PROCESS_CODE;

import javax.annotation.PostConstruct;

import com.yeepay.message.TxnContext;
import me.andpay.ti.base.AppBizException;
import me.andpay.ti.util.ObjectUtil;

import org.jpos.iso.ISOMsg;

/**
 * 授权方的Iso8583转换器实现类
 * 
 * @author sea.bao
 */
public class Iso8583TransferForAuthImpl extends AbstractIso8583Transfer implements Iso8583TransferForAuth {
	/**
	 * 通过交易应答解析处理代码(默认为是，否则直接使用交易上下文的处理代码)
	 */
	private boolean parseProcessCodeByResponse = true;

	/**
	 * 检查解析后的处理代码与原处理代码是否一致(默认为否)
	 */
	private boolean compareParsedProcessCode = false;

	/**
	 * iso8583域转换器
	 */
	private Iso8583FieldTransferForAuth iso8583FieldTransferForAuth;

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		iso8583FieldTransferForAuth.checkFieldNoes(getFieldNoes());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ISOMsg transferToAuthRequest(TxnContext txnCtx) throws AppBizException {
		String reqProcessCode = txnCtx.getProperty(PROCESS_CODE);
		Iso8583BitMap iso8583BitMap = iso8583BitMapConfig.getIso8583BitMap(reqProcessCode);

		ISOMsg isoMsg = new ISOMsg();
		if (iso8583BitMap.getRequestMsgType() != null) {
			// 设置消息类型
			Iso8583Operator.setField(isoMsg, Iso8583StandardFieldNoes.FIELD_NO_MSG_TYPE,
					iso8583BitMap.getRequestMsgType());
		}

		if (iso8583BitMap.getProcessCode() != null) {
			// 设置处理码
			Iso8583Operator.setField(isoMsg, Iso8583StandardFieldNoes.FIELD_NO_PROCESS_CODE,
					iso8583BitMap.getProcessCode());
		}
		Integer[] bitMap = iso8583BitMap.getRequestBitMapArray();
		for (int i = 0; i < bitMap.length; i++) {
			iso8583FieldTransferForAuth.transferToAuthRequest(isoMsg, bitMap[i], txnCtx, iso8583BitMap);
		}
		return isoMsg;
	}

	/**
	 * {@inheritDoc}
	 */
	public void transferFromAuthResponse(TxnContext txnCtx, ISOMsg isoMsg) throws AppBizException {
		String respProcessCode = null;
		if (parseProcessCodeByResponse) {
			// 根据应答报文解析得到交易类型
			respProcessCode = processCodeParser.parseProcessCode(isoMsg);

			if (compareParsedProcessCode) {
				String reqProcessCode = txnCtx.getProperty(PROCESS_CODE);
				if (ObjectUtil.isNotEqual(reqProcessCode, respProcessCode)) {
					// 请求和应答的交易类型不一致
					throw new RuntimeException(String.format("Diff Process Code, req=%s, resp=%s", reqProcessCode, respProcessCode));
				}
			}
			txnCtx.setProperty(PROCESS_CODE, respProcessCode);
		} else {
			// 直接使用当前交易类型
			respProcessCode = txnCtx.getProperty(PROCESS_CODE);
		}
		Iso8583BitMap iso8583BitMap = iso8583BitMapConfig.getIso8583BitMap(respProcessCode);

		Integer[] bitMap = iso8583BitMap.getResponseBitMapArray();
		for (int i = 0; i < bitMap.length; i++) {
			iso8583FieldTransferForAuth.transferFromAuthResponse(txnCtx, bitMap[i], isoMsg, iso8583BitMap);
		}
	}

	/**
	 * @param parseProcessCodeByResponse
	 *            the parseProcessCodeByResponse to set
	 */
	public void setParseProcessCodeByResponse(boolean parseProcessCodeByResponse) {
		this.parseProcessCodeByResponse = parseProcessCodeByResponse;
	}

	/**
	 * @param compareParsedProcessCode
	 *            the compareParsedProcessCode to set
	 */
	public void setCompareParsedProcessCode(boolean compareParsedProcessCode) {
		this.compareParsedProcessCode = compareParsedProcessCode;
	}

	/**
	 * @return the iso8583FieldTransferForAuth
	 */
	public Iso8583FieldTransferForAuth getIso8583FieldTransferForAuth() {
		return iso8583FieldTransferForAuth;
	}

	/**
	 * @param iso8583FieldTransferForAuth
	 *            the iso8583FieldTransferForAuth to set
	 */
	public void setIso8583FieldTransferForAuth(Iso8583FieldTransferForAuth iso8583FieldTransferForAuth) {
		this.iso8583FieldTransferForAuth = iso8583FieldTransferForAuth;
	}
}
