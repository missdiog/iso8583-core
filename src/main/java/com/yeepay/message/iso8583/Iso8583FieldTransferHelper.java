package com.yeepay.message.iso8583;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.message.TxnContext;

/**
 * Iso8583域信息转换器辅助接口
 * 
 * @author sea.bao
 */
public interface Iso8583FieldTransferHelper {
	/**
	 * 获得辅助类对应的域编号
	 * 
	 * @return 对应的域编号
	 */
	public int getFieldNo();

	/**
	 * 获得域信息
	 * 
	 * @param isoMsg
	 * @param txnCtx
	 * @param iso8583BitMap
	 * @return true表示存在域信息，false表示不存在域信息
	 * @throws AppBizException
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException;

	/**
	 * 设置域信息
	 * 
	 * @param isoMsg
	 * @param txnCtx
	 * @param iso8583BitMap
	 * @return true表示存在域信息，false表示不存在域信息
	 * @throws AppBizException
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException;

	/**
	 * 检查域信息
	 * 
	 * @param isoMsg
	 * @param txnCtx
	 * @param iso8583BitMap
	 * @throws AppBizException
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap)
			throws AppBizException;
}
