package com.yeepay.message.iso8583.packager;

import org.jpos.iso.IFA_AMOUNT;
import org.jpos.iso.IFA_BINARY;
import org.jpos.iso.IFA_BITMAP;
import org.jpos.iso.IFA_FLLCHAR;
import org.jpos.iso.IFA_FLLNUM;
import org.jpos.iso.IFA_LLCHAR;
import org.jpos.iso.IFA_LLLCHAR;
import org.jpos.iso.IFA_LLLNUM;
import org.jpos.iso.IFA_LLNUM;
import org.jpos.iso.ISOBasePackager;
import org.jpos.iso.ISOFieldPackager;

/**
 * ISO 8583 v1987 BINARY Packager
 * @author alex
 */
public class ISO87BPackager extends ISOBasePackager {
	protected ISOFieldPackager[] fld = {
			new IFA_FLLNUM(4, "MESSAGE TYPE INDICATOR"),  // 0
			new IFA_BITMAP(16, "BIT MAP"), // 1
			new IFA_LLNUM(19, "PAN - PRIMARY ACCOUNT NUMBER"), // 2
			new IFA_FLLNUM(6, "PROCESSING CODE"), // 3
			new IFA_FLLNUM(12, "AMOUNT, TRANSACTION"), // 4
			new IFA_FLLNUM(12, "AMOUNT, SETTLEMENT"), // 5
			new IFA_FLLNUM(12, "AMOUNT, CARDHOLDER BILLING"),
			new IFA_FLLNUM(10, "TRANSMISSION DATE AND TIME"),
			new IFA_FLLNUM(8, "AMOUNT, CARDHOLDER BILLING FEE"),
			new IFA_FLLNUM(8, "CONVERSION RATE, SETTLEMENT"),   
			new IFA_FLLNUM(8, "CONVERSION RATE, CARDHOLDER BILLING"),  // 10
			new IFA_FLLNUM(6, "SYSTEM TRACE AUDIT NUMBER"), // 11
			new IFA_FLLNUM(6, "TIME, LOCAL TRANSACTION"), // 12
			new IFA_FLLNUM(4, "DATE, LOCAL TRANSACTION"), // 13
			new IFA_FLLNUM(4, "DATE, EXPIRATION"), // 14
			new IFA_FLLNUM(4, "DATE, SETTLEMENT"), // 15
			new IFA_FLLNUM(4, "DATE, CONVERSION"),
			new IFA_FLLNUM(4, "DATE, CAPTURE"),
			new IFA_FLLNUM(4, "MERCHANTS TYPE"),// 18
			new IFA_FLLNUM(3, "ACQUIRING INSTITUTION COUNTRY CODE"),  
			new IFA_FLLNUM(3, "PAN EXTENDED COUNTRY CODE"),   // 20
			new IFA_FLLNUM(3, "FORWARDING INSTITUTION COUNTRY CODE"),
			new IFA_FLLNUM(3, "POINT OF SERVICE ENTRY MODE"), // 22
			new IFA_FLLNUM(3, "CARD SEQUENCE NUMBER"), // 23
			new IFA_FLLNUM(3, "NETWORK INTERNATIONAL IDENTIFIEER"),
			new IFA_FLLNUM(2, "POINT OF SERVICE CONDITION CODE"), // 25
			new IFA_FLLNUM(2, "POINT OF SERVICE PIN CAPTURE CODE"),
			new IFA_FLLNUM(1, "AUTHORIZATION IDENTIFICATION RESP LEN"),
			
			new IFA_FLLCHAR(9, "AMOUNT, TRANSACTION FEE"), // 28
			/*Amount*/
			new IFA_AMOUNT(9, "AMOUNT, SETTLEMENT FEE"),
			new IFA_AMOUNT(9, "AMOUNT, TRANSACTION PROCESSING FEE"),  // 30
			new IFA_AMOUNT(9, "AMOUNT, SETTLEMENT PROCESSING FEE"),
			new IFA_LLNUM(11, "ACQUIRING INSTITUTION IDENT CODE"), // 32
			new IFA_LLNUM(11, "FORWARDING INSTITUTION IDENT CODE"), // 33
			new IFA_LLCHAR(28, "PAN EXTENDED"),
			new IFA_LLNUM(37, "TRACK 2 DATA"), // 35
			new IFA_LLLNUM(104, "TRACK 3 DATA"), // 36
			new IFA_FLLNUM(12, "RETRIEVAL REFERENCE NUMBER"), // 37
			new IFA_FLLCHAR(6, "AUTHORIZATION IDENTIFICATION RESPONSE"), // 38
			new IFA_FLLCHAR(2, "RESPONSE CODE"), // 39
			new IFA_FLLCHAR(3, "SERVICE RESTRICTION CODE"),   // 40
			new IFA_FLLCHAR(8, "CARD ACCEPTOR TERMINAL IDENTIFICACION"), // 41
			new IFA_FLLCHAR(15, "CARD ACCEPTOR IDENTIFICATION CODE"), // 42
			new IFA_FLLCHAR(40, "CARD ACCEPTOR NAME/LOCATION"),
			new IFA_LLCHAR(25, "ADITIONAL RESPONSE DATA"), // 44
			new IFA_LLCHAR(76, "TRACK 1 DATA"), // 45
			new IFA_LLLCHAR(999, "ADITIONAL DATA - ISO"), // 46
			new IFA_LLLCHAR(999, "ADITIONAL DATA - NATIONAL"),
			new IFA_LLLCHAR(999, "ADITIONAL DATA - PRIVATE"), // 48
			new IFA_FLLCHAR(3, "CURRENCY CODE, TRANSACTION"), // 49
			new IFA_FLLCHAR(3, "CURRENCY CODE, SETTLEMENT"),  // 50
			new IFA_FLLCHAR(3, "CURRENCY CODE, CARDHOLDER BILLING"),
			new IFA_BINARY(8, "PIN DATA"), // 52
			
			
			
			new IFA_FLLNUM(16, "SECURITY RELATED CONTROL INFORMATION"),
			new IFA_LLLCHAR(120, "ADDITIONAL AMOUNTS"), // 54
			new IFA_LLLCHAR(999, "RESERVED ISO"), // 55
			new IFA_LLLCHAR(999, "RESERVED ISO"),
			new IFA_LLLCHAR(999, "RESERVED NATIONAL"),
			new IFA_LLLCHAR(999, "RESERVED NATIONAL"),
			new IFA_LLLCHAR(999, "RESERVED NATIONAL"),
			new IFA_LLLCHAR(999, "RESERVED PRIVATE"), // 60
			new IFA_LLLCHAR(999, "RESERVED PRIVATE"), // 61
			new IFA_LLLCHAR(999, "RESERVED PRIVATE"), // 62
			new IFA_LLLCHAR(999, "RESERVED PRIVATE"), // 63
			new IFA_BINARY(8, "MESSAGE AUTHENTICATION CODE FIELD") // 64
			
			/*
			new IFB_BINARY(1, "BITMAP, EXTENDED"), // 65
			new IFB_NUMERIC(1, "SETTLEMENT CODE", true),
			new IFB_NUMERIC(2, "EXTENDED PAYMENT CODE", true),
			new IFB_NUMERIC(3, "RECEIVING INSTITUTION COUNTRY CODE", true),
			new IFB_NUMERIC(3, "SETTLEMENT INSTITUTION COUNTRY CODE", true),
			new IFB_NUMERIC(3, "NETWORK MANAGEMENT INFORMATION CODE", true),// 70
			new IFB_NUMERIC(4, "MESSAGE NUMBER", true),
			new IFB_NUMERIC(4, "MESSAGE NUMBER LAST", true),
			new IFB_NUMERIC(6, "DATE ACTION", true),
			new IFB_NUMERIC(10, "CREDITS NUMBER", true),
			new IFB_NUMERIC(10, "CREDITS REVERSAL NUMBER", true),
			new IFB_NUMERIC(10, "DEBITS NUMBER", true),
			new IFB_NUMERIC(10, "DEBITS REVERSAL NUMBER", true),
			new IFB_NUMERIC(10, "TRANSFER NUMBER", true),
			new IFB_NUMERIC(10, "TRANSFER REVERSAL NUMBER", true),
			new IFB_NUMERIC(10, "INQUIRIES NUMBER", true), // 80
			new IFB_NUMERIC(10, "AUTHORIZATION NUMBER", true),
			new IFB_NUMERIC(12, "CREDITS, PROCESSING FEE AMOUNT", true),
			new IFB_NUMERIC(12, "CREDITS, TRANSACTION FEE AMOUNT", true),
			new IFB_NUMERIC(12, "DEBITS, PROCESSING FEE AMOUNT", true),
			new IFB_NUMERIC(12, "DEBITS, TRANSACTION FEE AMOUNT", true),
			new IFB_NUMERIC(16, "CREDITS, AMOUNT", true),
			new IFB_NUMERIC(16, "CREDITS, REVERSAL AMOUNT", true),
			new IFB_NUMERIC(16, "DEBITS, AMOUNT", true),
			new IFB_NUMERIC(16, "DEBITS, REVERSAL AMOUNT", true),
			new IFB_NUMERIC(42, "ORIGINAL DATA ELEMENTS", true), // 90
			new IF_CHAR(1, "FILE UPDATE CODE"),
			new IF_CHAR(2, "FILE SECURITY CODE"),
			new IF_CHAR(6, "RESPONSE INDICATOR"),
			new IF_CHAR(7, "SERVICE INDICATOR"),
			new IF_CHAR(42, "REPLACEMENT AMOUNTS"),
			new IFB_BINARY(16, "MESSAGE SECURITY CODE"),
			new IFB_AMOUNT(17, "AMOUNT, NET SETTLEMENT", false),
			new IF_CHAR(25, "PAYEE"),
			new IFB_LLNUM(11, "SETTLEMENT INSTITUTION IDENT CODE", false),
			new IFB_LLNUM(11, "RECEIVING INSTITUTION IDENT CODE", false), // 100
			new IFB_LLCHAR(17, "FILE NAME"),
			new IFB_LLCHAR(28, "ACCOUNT IDENTIFICATION 1"),
			new IFB_LLCHAR(28, "ACCOUNT IDENTIFICATION 2"),
			new IFB_LLLCHAR(100, "TRANSACTION DESCRIPTION"),
			new IFB_LLLCHAR(999, "RESERVED ISO USE"),
			new IFB_LLLCHAR(999, "RESERVED ISO USE"),
			new IFB_LLLCHAR(999, "RESERVED ISO USE"),
			new IFB_LLLCHAR(999, "RESERVED ISO USE"),
			new IFB_LLLCHAR(999, "RESERVED ISO USE"),
			new IFB_LLLCHAR(999, "RESERVED ISO USE"), // 110
			new IFB_LLLCHAR(999, "RESERVED ISO USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"), // 120
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
			new IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
			new IFB_BINARY(8, "MAC 2") // 128
			*/
		};

	/**
	 * 构造函数
	 */
	public ISO87BPackager() {
		setFieldPackager(buildFieldPackagers());
	}
	
	/**
	 * 构建字段定义数组
	 * 
	 * @return
	 */
	protected ISOFieldPackager[] buildFieldPackagers() {
		return fld;
	}
}
