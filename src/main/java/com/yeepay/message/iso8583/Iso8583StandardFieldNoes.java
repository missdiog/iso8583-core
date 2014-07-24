package com.yeepay.message.iso8583;

/**
 * 标准ISO8583域编号定义类
 * 
 * @author sea.bao
 */
public class Iso8583StandardFieldNoes {
	/**
	 * 消息类型域编号
	 */
	public static final int FIELD_NO_MSG_TYPE = 0;

	/**
	 * 位图域编号
	 */
	public static final int FIELD_NO_BIT_MAP = 1;

	/**
	 * 主账号域编号
	 */
	public static final int FIELD_NO_PAN = 2;

	/**
	 * 处理代码域编号
	 */
	public static final int FIELD_NO_PROCESS_CODE = 3;

	/**
	 * 交易金额域编号
	 */
	public static final int FIELD_NO_AMOUNT = 4;

	/**
	 * 系统跟踪号域编号
	 */
	public static final int FIELD_NO_AUTH_TRACE_NO = 11;

	/**
	 * 交易时间域编号
	 */
	public static final int FIELD_NO_TXN_TIME = 12;

	/**
	 * 交易日期域编号
	 */
	public static final int FIELD_NO_TXN_DATE = 13;

	/**
	 * 失效日期域编号
	 */
	public static final int FIELD_NO_EXPIRE_DATE = 14;

	/**
	 * 清算日期域编号
	 */
	public static final int FIELD_NO_SETTLEMENT_DATE = 15;
	
	/**
	 * 输入模式域编号
	 */
	public static final int FIELD_NO_SERVICE_ENTRY_MODE = 22;

	/**
	 * 卡序列号域编号
	 */
	public static final int FIELD_NO_CARD_SEQ_NO = 23;

	/**
	 * 服务条件代码域编号
	 */
	public static final int FIELD_NO_SERVICE_CONDITION_CODE = 25;

	/**
	 * 密码捕获码(可接受的密码最大长度)
	 */
	public static final int FIELD_NO_PIN_CAPTURE_CODE = 26;
	
	/**
	 * 受理机构标识码域编号
	 */
	public static final int FIELD_NO_ACQ_INST_ID = 32;
	
	/**
	 * 2磁道域编号
	 */
	public static final int FIELD_NO_TRACK2 = 35;

	/**
	 * 3磁道域编号
	 */
	public static final int FIELD_NO_TRACK3 = 36;

	/**
	 * 系统参考号域编号
	 */
	public static final int FIELD_NO_AUTH_REF = 37;

	/**
	 * 授权码域编号
	 */
	public static final int FIELD_NO_AUTH_CODE = 38;

	/**
	 * 应答码域编号
	 */
	public static final int FIELD_NO_RESPONSE_CODE = 39;

	/**
	 * 终端编号域编号
	 */
	public static final int FIELD_NO_TERMINAL_ID = 41;

	/**
	 * 商户编号域编号
	 */
	public static final int FIELD_NO_MERCHANT_ID = 42;

	/**
	 * 附加响应数据
	 */
	public static final int FIELD_NO_ADDI_AUTH_RESP = 44;

	/**
	 * 附加数据域编号
	 */
	public static final int FIELD_NO_ATTACH_DATA = 48;

	/**
	 * 货币代码数据域编号
	 */
	public static final int FIELD_NO_CURRENCY_CODE = 49;

	/**
	 * PIN域编号
	 */
	public static final int FIELD_NO_PIN = 52;

	/**
	 * 余额域编号
	 */
	public static final int FIELD_NO_BALANCE = 54;

	/**
	 * IC卡数据域编号
	 */
	public static final int FIELD_NO_IC_DATA = 55;

	/**
	 * 附加交易信息
	 */
	public static final int FIELD_NO_ADDI_TXN_INFO = 60;

	/**
	 * MAC域
	 */
	public static final int FIELD_NO_MAC = 64;
}
