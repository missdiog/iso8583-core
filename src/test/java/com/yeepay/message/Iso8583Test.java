package com.yeepay.message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.yeepay.message.iso8583.Iso8583MessageConvertor;

/**
 * Description: ****
 * @author Kevin
 * @Createdate 2014年7月24日.
 **/
public class Iso8583Test {

	//private static Logger logger = LoggerFactory.getLogger(Iso8583Test.class);
	
	private static Gson gson = new Gson();
	
	public static void main(String[] args) throws Exception {
		// create and configure beans
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-config/application-context-test.xml" });

		Iso8583MessageConvertor messageConvertor = context.getBean(
				"iso8583.convertor.MessageConvertor",
				Iso8583MessageConvertor.class);
		
		TxnContext ctx = new TxnContext();
		ctx.setProperty(TxnPropNames.PROCESS_CODE, "400130");
		ctx.setProperty(TxnPropNames.SRV_ENTRY_MODE, "021");
		ctx.setProperty(TxnPropNames.AUTH_TRACE_NO, "161");
		
		byte[] msg = messageConvertor.pack(ctx);
		System.out.println(msg);
		
		
		TxnContext ctx1 = new TxnContext();
		ctx1.setProperty(TxnPropNames.PROCESS_CODE, "400130");
		messageConvertor.unpack(msg, ctx1);
		
		System.out.println(gson.toJson(ctx1));
		
	}
}
