package com.yeepay.channel.cup.hn.service.impl;

import me.andpay.ti.base.AppBizException;

import com.yeepay.channel.cup.hn.service.CupHnPosIso8583Service;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583MessageConvertor;

/**
 * Description: ****
 * @author Kevin
 * @Createdate 2014年7月25日.
 **/
public class CupHnPosIso8583ServiceImpl implements CupHnPosIso8583Service {
	
	private Iso8583MessageConvertor iso8583MessageConvertor;
	
	public byte[] processCtx2IsoMsg(TxnContext ctx) throws AppBizException {
		if (ctx == null) {
			throw new RuntimeException("TxnContext is required, but txn context is null..");
		}
		return iso8583MessageConvertor.pack(ctx);
	}
	
	public TxnContext processIsoMsg2Ctx(byte[] isoMsg) throws AppBizException {
		if (isoMsg == null) {
			throw new RuntimeException("ISOMsg is required, but isoMsg is null..");
		}
		TxnContext ctx = new TxnContext();
		iso8583MessageConvertor.unpack(isoMsg, ctx);
		return ctx;
	}
	
	public TxnContext processIsoMsg2Ctx(byte[] isoMsg, TxnContext ctx) throws AppBizException {
		if (isoMsg == null) {
			throw new RuntimeException("ISOMsg is required, but isoMsg is null..");
		}
		if (ctx == null) {
			throw new RuntimeException("TxnContext is required, but txn context is null..");
		}
		iso8583MessageConvertor.unpack(isoMsg, ctx);
		return ctx;
	}

	public void setIso8583MessageConvertor(
			Iso8583MessageConvertor iso8583MessageConvertor) {
		this.iso8583MessageConvertor = iso8583MessageConvertor;
	}

}
