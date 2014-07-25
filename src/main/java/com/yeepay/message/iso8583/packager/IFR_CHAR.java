package com.yeepay.message.iso8583.packager;

import org.jpos.iso.AsciiInterpreter;
import org.jpos.iso.ISOStringFieldPackager;
import org.jpos.iso.NullPrefixer;
import org.jpos.iso.RightTPadder;

/**
 * Description: 8583报文数据右补齐空格定长字符类型
 * @author Kevin
 * @Createdate 2014年7月25日.
 **/
public class IFR_CHAR extends ISOStringFieldPackager {
	
    public IFR_CHAR() {
        super(0, null, RightTPadder.SPACE_PADDER, AsciiInterpreter.INSTANCE, NullPrefixer.INSTANCE);
    }

    /**
     * @param len - field len
     * @param description symbolic descrption
     */
    public IFR_CHAR(int len, String description) {
        super(len, description, RightTPadder.SPACE_PADDER, AsciiInterpreter.INSTANCE, NullPrefixer.INSTANCE);
    }
}
