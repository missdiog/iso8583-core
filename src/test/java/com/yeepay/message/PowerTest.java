package com.yeepay.message;

import static com.yeepay.message.TxnPropNames.*;

import java.math.BigDecimal;
import java.util.Date;

import me.andpay.ac.consts.CurrencyCodes;
import me.andpay.ti.util.ByteUtil;
import me.andpay.ti.util.HexUtil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.yeepay.channel.cup.hn.service.CupHnPosIso8583Service;
import com.yeepay.message.iso8583.Iso8583MessageConvertor;

public class PowerTest {

	//private static Logger logger = LoggerFactory.getLogger(Iso8583Test.class);
	
	private static Gson gson = new Gson();
	
	public static void main(String[] args) throws Exception {
		// create and configure beans
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-config/application-context-test.xml" });

		Iso8583MessageConvertor messageConvertor = context.getBean(
				"iso8583.convertor.MessageConvertor",
				Iso8583MessageConvertor.class);
		CupHnPosIso8583Service cupHnPosIso8583Service = context.getBean(
				"cup.hn.pos.CupHnPosIso8583Service",
				CupHnPosIso8583Service.class);
				
		
		
		TxnContext ctx = new TxnContext();
		ctx.setProperty(PROCESS_CODE, "002010");//处理代码
		ctx.setProperty(PAN, "1234567890123456789");//主账号
		ctx.setProperty(AMT, new BigDecimal("100"));//交易金额
		ctx.setProperty(AUTH_TRACE_NO, "1234");//系统跟踪号
		ctx.setProperty(TXN_TIME, new Date());//交易日期/时间
		ctx.setProperty(MERCHANT_TYPE, "1800");//商户类型
		ctx.setProperty(SRV_ENTRY_MODE, "021");//输入方式
		ctx.setProperty(CARD_SEQ_NO, "123");//卡序列号
		ctx.setProperty(SRV_COND_CODE, "21");//服务点条件码
		ctx.setProperty(ACQ_INST_ID, "1234567890");//受理机构代码
		ctx.setProperty(SEND_INST_ID, "48255500");//发送机构代码
		ctx.setProperty(TRACK2, "12345678901234567890");//磁道2数据
		ctx.setProperty(TRACK3, "12345678901234567890");//磁道3数据

		ctx.setProperty(AUTH_TERM_ID, "88888888");//终端号
		ctx.setProperty(AUTH_MERCH_ID, "1234");//商户代号
		
		ctx.setProperty(MONTH_OWE_BILLS, new BigDecimal("100"));//欠费金额
		ctx.setProperty(OWE_MONTHS, new Integer("1"));//欠费月数
		ctx.setProperty(POWER_BILLS_REC_NO, "1234");//应收电费标识号
		ctx.setProperty(TOTAL_PAY_BILLS, new BigDecimal("300"));//电费金额
		ctx.setProperty(TOTAL_PENALTY_CONTRACT, new BigDecimal("400"));//违约金
		
		ctx.setProperty(USER_ID, "1234567890");//电力用户ID号
		ctx.setProperty(CUR, CurrencyCodes.CNY);//交易货币代码
		ctx.setProperty(PIN, "1234567890123456");//PIN数据
		ctx.setProperty(IC_DATA, "123467890");//IC卡信息
		ctx.setProperty(POWER_UNIT_CODE, "666666");//供电单位编码
		
		
		
		byte[] msg = cupHnPosIso8583Service.processCtx2IsoMsg(ctx);
		
		System.out.println(new String(msg));
		
		System.out.println(HexUtil.encodeHex(msg));
		
		
		TxnContext ctx1 = new TxnContext();
		messageConvertor.unpack(msg, ctx1);
		
		ctx1 = cupHnPosIso8583Service.processIsoMsg2Ctx(msg);
		
		System.out.println(gson.toJson(ctx));
		System.out.println(gson.toJson(ctx));
		
	}
}
