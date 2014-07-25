package com.yeepay.message.iso8583.packager;

import org.jpos.iso.AsciiInterpreter;
import org.jpos.iso.ISOStringFieldPackager;
import org.jpos.iso.NullPrefixer;
import org.jpos.iso.RightPadder;

/**
 * Description: 8583报文数据右补齐空格定长数据类型
 * @author Kevin
 * @Createdate 2014年7月25日.
 **/
public class IFR_NUMERIC extends ISOStringFieldPackager {

	public IFR_NUMERIC() {
		super(RightPadder.SPACE_PADDER, AsciiInterpreter.INSTANCE,
				NullPrefixer.INSTANCE);
	}

	/**
	 * @param len - field len
	 * @param description symbolic descrption
	 */
	public IFR_NUMERIC(int len, String description) {
		super(len, description, RightPadder.SPACE_PADDER,
				AsciiInterpreter.INSTANCE, NullPrefixer.INSTANCE);
	}
	
}
