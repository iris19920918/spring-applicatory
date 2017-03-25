package com.wang.common;


public enum WStatus {
	
	SUCCESS(9000,"成功"),
	VALIDATEERROR(1000,"验证失败"),
	TOKENERROR(2000,"未登录或登录信息已失效"),
	SERVERERROR(3000,"服务端错误"),
	NOSERVERERROR(4000,"没有服务错误"),
	TRADEERROR(5000,"交易错误"),
	VERSIONERROR(8000, "版本错误");
	
	private int code;
	
	private String msg;
	
	private WStatus(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public static WStatus get(int code) {
        for (WStatus c : WStatus.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

}
