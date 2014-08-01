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

public class SignInTest {

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
		ctx.setProperty(PROCESS_CODE, "910000");//处理代码
		ctx.setProperty(AUTH_TRACE_NO, "1234");//系统跟踪号
		ctx.setProperty(TXN_TIME, new Date());//交易日期/时间
		ctx.setProperty(SEND_INST_ID, "48255500");//发送机构代码
		
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
