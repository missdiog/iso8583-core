package com.yeepay.channel.cup.hn.pos.iso8583.helper;

import static com.yeepay.message.TxnPropNames.*;

import java.math.BigDecimal;

import me.andpay.ti.base.AppBizException;

import org.jpos.iso.ISOMsg;

import com.yeepay.channel.cup.hn.util.ConstantUtil;
import com.yeepay.message.TxnContext;
import com.yeepay.message.iso8583.Iso8583BitMap;
import com.yeepay.message.iso8583.Iso8583FieldTransferHelper;
import com.yeepay.message.iso8583.Iso8583Operator;
import com.yeepay.message.iso8583.Iso8583StandardFieldNoes;

/**
 * 欠费查询转换助手
 * @author LiuDawei
 *
 */
public class Field48OweQueryTransferHelper implements Iso8583FieldTransferHelper {

	public int getFieldNo() {
		return Iso8583StandardFieldNoes.FIELD_NO_ATTACH_DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	public void checkFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		throw new RuntimeException("Unexpected check, fieldNo=" + getFieldNo());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean getFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		String attachData = Iso8583Operator.getFieldString(isoMsg, getFieldNo());
		if(attachData != null){
			//如果返回成功(00)
			if(ConstantUtil.RESP_SUC_CODE.equals(Iso8583Operator.getFieldString(isoMsg, 
					Iso8583StandardFieldNoes.FIELD_NO_RESPONSE_CODE))){
				String[] fields = attachData.split("|");
				txnCtx.setProperty(OWE_FLAG, fields[0]);//欠费标识
				txnCtx.setProperty(USER_NAME, fields[1].trim());//用户名称
				txnCtx.setProperty(TOTAL_PAY_BILLS, new BigDecimal(fields[2].trim()));//总应缴费用
				txnCtx.setProperty(TOTAL_PENALTY_CONTRACT, new BigDecimal(fields[3].trim()));//总违约金
				txnCtx.setProperty(OWE_MONTHS, Integer.valueOf(fields[4].trim()));//欠费月数
				
				//有欠费-即有【欠费明细信息】
				if(ConstantUtil.OWE_FLAG_YES.equals(fields[0])){
					txnCtx.setProperty(BILLS_REC_NO, fields[5].trim());//应收费用标识号
					txnCtx.setProperty(MONTH_OWE_BILLS, new BigDecimal(fields[6].trim()));//每月欠费金额
					txnCtx.setProperty(MONTH_PENALTY_CONTRACT, new BigDecimal(fields[7].trim()));//每月违约金
				}
			}else{
				txnCtx.setProperty(RESP_ERROR_CODE, attachData);
			}
		}
		return (attachData != null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean setFieldInfo(ISOMsg isoMsg, TxnContext txnCtx, Iso8583BitMap iso8583BitMap) throws AppBizException {
		throw new RuntimeException("Unexpected set, fieldNo=" + getFieldNo());
	}

}
