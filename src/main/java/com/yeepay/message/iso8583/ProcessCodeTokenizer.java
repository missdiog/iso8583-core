package com.yeepay.message.iso8583;


/**
 * Description: ****
 * @author Kevin
 * @Createdate 2014年7月23日.
 **/

public class ProcessCodeTokenizer {

	/**
	 * 分隔符
	 */
	private static final String DELIMITER = "#";

	/**
	 * 标记内容
	 */
	private StringBuilder token = new StringBuilder();

	/**
	 * 添加标记组成部分，为null则忽略
	 * @param part
	 * @return
	 */
	public ProcessCodeTokenizer append(String part) {
		if (part != null) {
			if (token.length() != 0) {
				token.append(DELIMITER);
			}
			token.append(part);
		}
		return this;
	}

	/**
	 * 删除最后一个标记组成部分
	 * @return
	 */
	public ProcessCodeTokenizer removeLast() {
		int end = token.length();
		if (end > 0) {
			int start = Math.max(token.lastIndexOf(DELIMITER), 0);
			token.delete(start, end);
		}
		return this;
	}

	/**
	 * 获取当前标记内容
	 * @return
	 */
	public String getToken() {
		return token.toString();
	}
	
}
