package com.yeepay.channel.cup.hn.pos.iso8583;

import java.util.Arrays;

import me.andpay.ti.base.AppBizException;
import me.andpay.ti.lnk.annotaion.Lnkwired;
import me.andpay.ti.s3.api.crypto.TxnAppCryptoService;
import me.andpay.ti.s3.api.kms.KeyDataWithCv;
import me.andpay.ti.util.ByteUtil;
import me.andpay.ti.util.HexUtil;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

import com.yeepay.message.iso8583.Iso8583MacHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;

/**
 * ISO8583 MAC助手实现类 (银联MAC计算方式)
 * @author alex
 */
public class CupHnPosIso8583MacHelper implements Iso8583MacHelper {
	/**
	 * MAC域编号
	 */
	protected int macFieldNo = Iso8583StandardFieldNoes.FIELD_NO_MAC;

	/**
	 * 交易报文定义
	 */
	protected ISOPackager txnISOPackager;

	/**
	 * 交易应用加解密服务
	 */
	protected TxnAppCryptoService txnAppCryptoService;

	/**
	 * {@inheritDoc}
	 */
	public byte[] calcMac(ISOMsg isoMsg, String macKey) throws AppBizException {
		byte[] macStub = new byte[8];

		if (isoMsg.hasField(macFieldNo) == false) {
			// 没有MAC域时填充
			Iso8583Operator.setField(isoMsg, macFieldNo, macStub);
		}
		isoMsg.setPackager(txnISOPackager);

		byte[] mab = null;
		try {
			// 完整报文(含MAC)
			mab = isoMsg.pack();
		} catch (Throwable t) {
			throw new RuntimeException("Pack error when calc mac", t);
		}
		// MAC Block - 去除MAC域内容
		mab = ByteUtil.getBytes(mab, 0, mab.length - macStub.length);
		if (mab.length % 8 != 0) {
			// MAB长度补满8的倍数
			mab = ByteUtil.rightPad(mab, mab.length + 8 - mab.length % 8, (byte) 0x00);
		}

		// TEK
		KeyDataWithCv tekByLmk = KeyDataWithCv.fromHex(macKey);
		
		//去除MAC域内容后，取前8字节进行DES
		byte[] desMacUnit = txnAppCryptoService.encryptDataByTek(tekByLmk, ByteUtil.getBytes(mab, 0, 8), false);
		
		byte[] mabUnit = desMacUnit;
		
		for (int offset = 8; offset < mab.length; offset += 8) {
			//计算结果后8字节进行XOR
			mabUnit = ByteUtil.xor(mabUnit, ByteUtil.getBytes(mab, offset, 8));
			// 用MAK对XOR结果进行DES
			mabUnit = txnAppCryptoService.encryptDataByTek(tekByLmk, mabUnit, false);
		}
		
		return mabUnit;
	}

	/**
	 * {@inheritDoc}
	 */
	public void verifyMac(ISOMsg isoMsg, String macKey) throws AppBizException {
		byte[] actMac = Iso8583Operator.getFieldBinary(isoMsg, macFieldNo);
		byte[] expMac = calcMac(isoMsg, macKey);
		if (Arrays.equals(expMac, actMac) == false) {
			// MAC校验错误
			throw new RuntimeException(String.format("Mac verify error, expMac=%s, actMac=%s",
					HexUtil.encodeHex(expMac), HexUtil.encodeHex(actMac)));
		}
	}

	/**
	 * @param macFieldNo
	 *            the macFieldNo to set
	 */
	public void setMacFieldNo(int macFieldNo) {
		this.macFieldNo = macFieldNo;
	}

	/**
	 * @param txnISOPackager
	 *            the txnISOPackager to set
	 */
	public void setTxnISOPackager(ISOPackager txnISOPackager) {
		this.txnISOPackager = txnISOPackager;
	}

	/**
	 * @param txnAppCryptoService
	 *            the txnAppCryptoService to set
	 */
	@Lnkwired
	public void setTxnAppCryptoService(TxnAppCryptoService txnAppCryptoService) {
		this.txnAppCryptoService = txnAppCryptoService;
	}
}
