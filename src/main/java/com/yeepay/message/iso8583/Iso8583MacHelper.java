package com.yeepay.message.iso8583;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

/**
 * ISO8583 MAC助手类
 * 
 * @author alex
 */
public interface Iso8583MacHelper {
	/**
	 * 计算MAC
	 * 
	 * @param isoMsg
	 * @param macKey
	 * @return
	 * @throws AppBizException
	 */
	public byte[] calcMac(ISOMsg isoMsg, String macKey) throws AppBizException;

	/**
	 * 验证MAC
	 * 
	 * @param isoMsg
	 * @param macKey
	 * @throws AppBizException
	 */
	public void verifyMac(ISOMsg isoMsg, String macKey) throws AppBizException;
}
