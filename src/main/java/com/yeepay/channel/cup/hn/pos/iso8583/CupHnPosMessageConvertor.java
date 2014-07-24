package com.yeepay.channel.cup.hn.pos.iso8583;

import java.util.Arrays;

import com.yeepay.message.TxnContext;
import com.yeepay.message.TxnPropNames;
import com.yeepay.message.iso8583.Iso8583MessageUtil;
import me.andpay.ac.consts.ServiceEntryModes;
import me.andpay.ti.util.ByteUtil;
import me.andpay.ti.util.StringUtil;

import com.yeepay.message.iso8583.Iso8583MessageConvertor;

/**
 * Description: ****
 * @author Kevin
 * @Createdate 2014年7月23日.
 **/

public class CupHnPosMessageConvertor extends Iso8583MessageConvertor {

	/**
	 * IC卡金融支付类
	 */
	private static final byte TXN_HEAD_APP_CATE_IC_FIN = 0x61;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected byte[] preUnpack(byte[] txnMsg, TxnContext txnCtx) {
		// LEN B2 + TPDU n10 + 应用类别 n2 + 软件总版本 n2 + 终端状态 n1 + 处理要求 n1 + 软件分版本 n6
		byte procReq = ByteUtil.byteAt(txnMsg, 9, (byte) 0x00);

		// 处理要求
		txnCtx.setProperty(TxnPropNames.TXN_HEAD_PROC_REQ, Integer.valueOf(procReq & 0x0F));

		// 检查条件代码
		return Iso8583MessageUtil.getIso8583DataFromRecv(txnMsg, tpdu, txnHead);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected byte[] postPack(byte[] txnMsg, TxnContext txnCtx) {
		// 报文头的应用类别默认为磁条卡金融支付
		byte[] curTxnHead = txnHead;

		String srvEntryMode = txnCtx.getProperty(TxnPropNames.SRV_ENTRY_MODE);
		Boolean fallback = txnCtx.getProperty(TxnPropNames.FALLBACK);
		if (StringUtil.startsWith(srvEntryMode, ServiceEntryModes.IC) || Boolean.TRUE.equals(fallback)) {
			// 输入模式为IC 或 FallBack交易，则设置报文头的应用类别为IC金融支付
			curTxnHead = Arrays.copyOf(curTxnHead, curTxnHead.length);
			curTxnHead[0] = TXN_HEAD_APP_CATE_IC_FIN;
		}
		return Iso8583MessageUtil.getIso8583DataToSend(txnMsg, tpdu, curTxnHead);
	}
	
}
