package com.yeepay.message;

/**
 * 交易数据上下文属性名称常量类
 * 
 * @author alex
 */
public final class TxnPropNames {
	/**
	 * 交易编号 - java.lang.String
	 */
	public static final String TXN_ID = "txnId";

	/**
	 * 原交易编号 - java.lang.String
	 */
	public static final String ORIG_TXN_ID = "origTxnId";

	/**
	 * 授权交易编号 - java.lang.String
	 */
	public static final String AUTH_TXN_ID = "authTxnId";

	/**
	 * 原授权交易编号 - java.lang.String
	 */
	public static final String ORIG_AUTH_TXN_ID = "origAuthTxnId";

	/**
	 * 交易模式 - java.lang.String
	 */
	public static final String TXN_MODE = "txnMode";

	/**
	 * 交易类型 - java.lang.String
	 */
	public static final String TXN_TYPE = "txnType";

	/**
	 * 原交易类型 - java.lang.String
	 */
	public static final String ORIG_TXN_TYPE = "origTxnType";

	/**
	 * 交易时间 - java.util.Date
	 */
	public static final String TXN_TIME = "txnTime";

	/**
	 * 二磁道数据 - byte[]
	 */
	public static final String TRACK2 = "track2";

	/**
	 * 三磁道数据 - byte[]
	 */
	public static final String TRACK3 = "track3";

	/**
	 * 卡号 - java.lang.String
	 */
	public static final String PAN = "pan";

	/**
	 * 密码 - byte[]
	 */
	public static final String PIN = "pin";

	/**
	 * 币别 - java.lang.String
	 */
	public static final String CUR = "cur";

	/**
	 * 金额 - java.math.BigDecimal
	 */
	public static final String AMT = "amt";

	/**
	 * 分期期数 - java.math.BigDecimal
	 */
	public static final String INSTALLMENT = "installment";

	/**
	 * 输入模式 - java.lang.String
	 */
	public static final String SRV_ENTRY_MODE = "srvEntryMode";

	/**
	 * 服务点条件代码 - java.lang.String
	 */
	public static final String SRV_COND_CODE = "srvCondCode";

	/**
	 * 失效日期/有效期(YYMM) - java.lang.String
	 */
	public static final String EXPIRE_DATE = "expireDate";

	/**
	 * CVV2 - java.lang.String
	 */
	public static final String CVV2 = "cvv2";

	/**
	 * 账户余额 - java.math.BigDecimal
	 */
	public static final String BALANCE = "balance";

	/**
	 * 分期期数 - java.lang.Integer
	 */
	public static final String TERM_IN_MONTHS = "termInMonths";

	/**
	 * 授权商户号 - java.lang.String
	 */
	public static final String AUTH_MERCH_ID = "authMerchId";

	/**
	 * 授权终端号 - java.lang.String
	 */
	public static final String AUTH_TERM_ID = "authTermId";

	/**
	 * 授权码 - java.lang.String
	 */
	public static final String AUTH_CODE = "authCode";

	/**
	 * 授权时间 - java.util.Date
	 */
	public static final String AUTH_TIME = "authTime";

	/**
	 * 原授权时间 - java.util.Date
	 */
	public static final String ORIG_AUTH_TIME = "origAuthTime";

	/**
	 * 授权币别 - java.lang.String
	 */
	public static final String AUTH_CUR = "authCur";

	/**
	 * 授权金额 - java.math.BigDecimal
	 */
	public static final String AUTH_AMT = "authAmt";

	/**
	 * 授权参考号 - java.lang.String
	 */
	public static final String AUTH_REF = "authRef";

	/**
	 * 授权跟踪编号 - java.lang.String
	 */
	public static final String AUTH_TRACE_NO = "authTraceNo";

	/**
	 * 原授权跟踪编号 - java.lang.String
	 */
	public static final String ORIG_AUTH_TRACE_NO = "origAuthTraceNo";

	/**
	 * 授权批次编号 - java.lang.String
	 */
	public static final String AUTH_BATCH_NO = "authBatchNo";

	/**
	 * 原授权批次编号 - java.lang.String
	 */
	public static final String ORIG_AUTH_BATCH_NO = "origAuthBatchNo";

	/**
	 * 授权票据编号 - java.lang.String
	 */
	public static final String AUTH_INOVICE_NO = "authInvoiceNo";

	/**
	 * 授权应答码 - java.lang.String
	 */
	public static final String AUTH_RESP_CODE = "authRespCode";

	/**
	 * 授权应答信息 - java.lang.String
	 */
	public static final String AUTH_RESP_MSG = "authRespMsg";

	/**
	 * 清算日期(MMDD) - java.lang.String
	 */
	public static final String AUTH_SETTLE_DATE = "authSettleDate";

	/**
	 * 终端签到返回的商户名称 - java.lang.String
	 */
	public static final String AUTH_MERCHANT_NAME = "authMerchantName";

	/**
	 * 附加授权响应数据 - java.lang.String
	 */
	public static final String ADDI_AUTH_RESP = "addiAuthResp";

	/**
	 * 受理机构标识码 - java.lang.String
	 */
	public static final String ACQ_INST_ID = "acqInstId";

	/**
	 * 卡类型 - java.lang.String
	 */
	public static final String CARD_TYPE = "cardType";

	/**
	 * 发卡组织 - java.lang.String
	 */
	public static final String CARD_ASSOC = "cardAssoc";

	/**
	 * 持卡人证件类型 - java.lang.String
	 */
	public static final String CARD_HOLDER_CERT_TYPE = "cardHolderCertType";

	/**
	 * 持卡人证件号码 - java.lang.String
	 */
	public static final String CARD_HOLDER_CERT_NO = "cardHolderCertNo";

	/**
	 * 卡序列号 - java.lang.String
	 */
	public static final String CARD_SEQ_NO = "cardSerialNo";

	/**
	 * IC卡数据 - byte[]
	 */
	public static final String IC_DATA = "icData";

	/**
	 * 交易降级标志 - java.lang.Boolean
	 */
	public static final String FALLBACK = "fallBack";

	/**
	 * 工作密钥 - byte[]
	 */
	public static final String WORKING_KEY = "workingKey";

	/**
	 * 终端主密钥 - byte[]
	 */
	public static final String MASTER_KEY = "masterKey";

	/**
	 * 终端主密钥校验值 - byte[]
	 */
	public static final String MASTER_KEY_CV = "masterKeyCv";

	/**
	 * MAC密钥 - byte[]
	 */
	public static final String MAC_KEY = "macKey";

	/**
	 * MAC密钥校验值 - byte[]
	 */
	public static final String MAC_KEY_CV = "macKeyCv";

	/**
	 * PIN密钥 - byte[]
	 */
	public static final String PIN_KEY = "pinKey";

	/**
	 * PIN密钥校验值 - byte[]
	 */
	public static final String PIN_KEY_CV = "pinKeyCv";

	/**
	 * 消息类型 - java.lang.String
	 */
	public static final String MSG_TYPE = "msgType";

	/**
	 * 处理码 - java.lang.String
	 */
	public static final String PROCESS_CODE = "processCode";

	/**
	 * 交易请求数据 - java.lang.Object
	 */
	public static final String TXN_REQ_DATA = "txnReqData";

	/**
	 * 交易应答数据 - java.lang.Object
	 */
	public static final String TXN_RESP_DATA = "txnRespData";

	/**
	 * 报文头数据 - 处理要求 - java.lang.Integer
	 */
	public static final String TXN_HEAD_PROC_REQ = "txnHeadProcReq";
	
	/**
	 * 商户类型- 处理要求 - java.lang.String
	 */
	public static final String MERCHANT_TYPE = "merType";
	
	/**
	 * 手续费 - java.math.BigDecimal
	 */
	public static final String TXN_FEE_AMT = "txnFeeAmt";
	
	/**
	 * 发送机构代码 - java.lang.String
	 */
	public static final String SEND_INST_ID = "sendInstId"; 
	
	/**
	 * 附加数据 - java.lang.String
	 */
	public static final String ATTACH_DATA = "attachData";
	
	/**
	 * 用户ID号 - java.lang.String
	 */
	public static final String USER_ID = "userId";
	/**
	 * 供电单位编码 - java.lang.String
	 */
	public static final String POWER_UNIT_CODE = "powerUnitCode";
	
	/**
	 * 欠费标识 - java.lang.String
	 */
	public static final String OWE_FLAG = "oweFlag";
	
	/**
	 * 用户名称 - java.lang.String
	 */
	public static final String USER_NAME = "userName";
	
	/**
	 * 总应缴费用 - java.math.BigDecimal
	 */
	public static final String TOTAL_PAY_BILLS = "totalPayBills";
	
	/**
	 * 总违约金 - java.math.BigDecimal
	 */
	public static final String TOTAL_PENALTY_CONTRACT = "totalPenaltyContract";
	
	/**
	 * 欠费月数 - java.lang.Integer
	 */
	public static final String OWE_MONTHS = "oweMonths";
	
	/**
	 * 应收电费标识号 - java.lang.String
	 */
	public static final String POWER_BILLS_REC_NO = "powerBillsRecNo";
	
	/**
	 * 每月欠费金额 - java.math.BigDecimal
	 */
	public static final String MONTH_OWE_BILLS = "monthOweBills";
	
	/**
	 * 每月违约金 - java.math.BigDecimal
	 */
	public static final String MONTH_PENALTY_CONTRACT = "monthPenaltyContract";
	
	/**
	 * 电力返回错误代码 - java.lang.String
	 */
	public static final String POWER_ERROR_CODE = "powerErrorCode";

	private TxnPropNames() {
		
	}
}
