package com.wang.exception;

@SuppressWarnings("serial")
public class SalzburgException extends Exception {
    private String code;

    private String msg;

    public SalzburgException() {
        super();
    }

    public SalzburgException(String msg, String code) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
