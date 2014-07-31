package com.yeepay.message.iso8583;

import static com.yeepay.message.TxnPropNames.PROCESS_CODE;

import java.util.Map;

import me.andpay.ti.util.MapUtil;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

import com.yeepay.message.TxnContext;
import com.yeepay.message.convertor.MessageConvertor;

/**
 * Description: ISO8583消息转换器实现类
 * @Createdate 2014年7月22日.
 **/

public class Iso8583MessageConvertor implements MessageConvertor<byte[], byte[]> {

	/**
	 * TPDU
	 */
	protected byte[] tpdu;

	/**
	 * 交易头
	 */
	protected byte[] txnHead;

	/**
	 * 交易ISO包定义
	 */
	protected ISOPackager defaultTxnISOPackager;

	/**
	 * 交易ISO包定义映射[交易类型 - 交易ISO包]
	 */
	protected Map<String, ISOPackager> txnISOPackagerMap;

	/**
	 * ISO8583数据转换服务
	 */
	protected Iso8583TransferForAuth iso8583TransferForAuth;

	/**
	 * {@inheritDoc}
	 */
	public void unpack(byte[] txnMsg, TxnContext txnCtx) {
		try {
			txnMsg = preUnpack(txnMsg, txnCtx);

			ISOMsg txnIsoMsg = new ISOMsg();
			txnIsoMsg.setPackager(getIsoPackager(txnCtx));
			txnIsoMsg.unpack(txnMsg);

			iso8583TransferForAuth.transferFromAuthResponse(txnCtx, txnIsoMsg);
		} catch (Throwable t) {
			throw new RuntimeException("Unpack iso8583 msg error, process code=" + txnCtx.getProperty(PROCESS_CODE),
					rewrapException(t));
		}
	}

	/**
	 * 解包前置处理
	 * 
	 * @param txnMsg
	 * @param txnCtx
	 * @return
	 */
	protected byte[] preUnpack(byte[] txnMsg, TxnContext txnCtx) {
		// 去除报文头
		return Iso8583MessageUtil.getIso8583DataFromRecv(txnMsg, tpdu, txnHead);
	}

	/**
	 * {@inheritDoc}
	 */
	public byte[] pack(TxnContext txnCtx) {
		try {
			ISOMsg txnIsoMsg = iso8583TransferForAuth.transferToAuthRequest(txnCtx);
			txnIsoMsg.setPackager(getIsoPackager(txnCtx));

			byte[] txnMsg = txnIsoMsg.pack();
			return postPack(txnMsg, txnCtx);
		} catch (Throwable t) {
			throw new RuntimeException("Pack iso8583 msg error, process code=" + txnCtx.getProperty(PROCESS_CODE),
					rewrapException(t));
		}
	}

	/**
	 * 组包后置处理
	 * @param txnMsg
	 * @param txnCtx
	 * @return
	 */
	protected byte[] postPack(byte[] txnMsg, TxnContext txnCtx) {
		// 增加报文头
		return Iso8583MessageUtil.getIso8583DataToSend(txnMsg, tpdu, txnHead);
	}

	/**
	 * 获取ISO包定义
	 * @param txnCtx
	 * @return
	 */
	protected ISOPackager getIsoPackager(TxnContext txnCtx) {
		// 优先根据交易类型选择，选择不到则使用默认包定义
		return MapUtil.get(txnISOPackagerMap, txnCtx.getStringProperty(PROCESS_CODE), defaultTxnISOPackager);
	}

	/**
	 * 包装异常对象
	 * @param t
	 * @return
	 */
	private Throwable rewrapException(Throwable t) {
		if (t instanceof ISOException) {
			// 将ISO异常包装成普通异常，获取实际异常
			ISOException isoEx = ((ISOException) t);
			if (isoEx.getNested() != null) {
				return new RuntimeException(isoEx.getMessage(), isoEx.getNested());
			}
		}
		return t;
	}

	/**
	 * @param tpdu
	 *            the tpdu to set
	 */
	public void setTpdu(String tpdu) {
//		this.tpdu = HexUtil.decodeHex(tpdu);
		this.tpdu = tpdu.getBytes();
	}

	/**
	 * @param txnHead
	 *            the txnHead to set
	 */
	public void setTxnHead(String txnHead) {
//		this.txnHead = HexUtil.decodeHex(txnHead);
		this.txnHead = txnHead.getBytes();
	}

	/**
	 * @param defaultTxnISOPackager
	 *            the defaultTxnISOPackager to set
	 */
	public void setDefaultTxnISOPackager(ISOPackager defaultTxnISOPackager) {
		this.defaultTxnISOPackager = defaultTxnISOPackager;
	}

	/**
	 * @param txnISOPackagerMap
	 *            the txnISOPackagerMap to set
	 */
	public void setTxnISOPackagerMap(Map<String, ISOPackager> txnISOPackagerMap) {
		this.txnISOPackagerMap = txnISOPackagerMap;
	}

	/**
	 * @param iso8583TransferForAuth
	 *            the iso8583TransferForAuth to set
	 */
	public void setIso8583TransferForAuth(Iso8583TransferForAuth iso8583TransferForAuth) {
		this.iso8583TransferForAuth = iso8583TransferForAuth;
	}

}
