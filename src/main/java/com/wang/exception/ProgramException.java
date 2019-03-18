package com.wang.exception;
/**
 * @Copyright: 2010 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2012-12-24 上午11:21:45
 */


/**
 * 该异常属于业务异常，一般不是错误，主要用来控制特定逻辑的异常。<p>
 * 这个异常主要控制的逻辑是将后台的异常转化成提示语返回到前台。<br>
 * 比如页面上的表单数据校验结果、访问权限校验失败等。
 * <p></p>
 *
 * @author huanghuafeng 2012-12-24 上午11:21:45
 * @version V1.0
 */

public class ProgramException extends RuntimeException {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 异常代码，需要项目统一定义
     */
    private String code;

    /**
     * 格式化参数
     */
    private Object[] args;

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     */
    public ProgramException(String code) {
        this.code = code;
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param args 异常信息中的格式化参数
     */
    public ProgramException(String code, Object[] args) {
        this.code = code;
        this.args = args;
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param e    内部异常
     */
    public ProgramException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param args 异常信息中的格式化参数
     * @param e    内部异常
     */
    public ProgramException(String code, Object[] args, Throwable e) {
        super(e);
        this.code = code;
        this.args = args;
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param msg  异常描述
     */
    public ProgramException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public ProgramException(Long code, String msg) {
        super(msg);
        this.code = code.toString();
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param args 异常信息中的格式化参数
     * @param msg  异常描述
     */
    public ProgramException(String code, Object[] args, String msg) {
        super(msg);
        this.code = code;
        this.args = args;
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param msg  异常描述
     * @param e    内部异常
     */
    public ProgramException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param msg  异常描述
     * @param e    内部异常
     */
    public ProgramException(Long code, String msg, Throwable e) {
        super(msg, e);
        this.code = code.toString();
    }

    /**
     * 创建一个新的实例ProgramException.
     *
     * @param code 异常码
     * @param args 异常信息中的格式化参数
     * @param msg  异常描述
     * @param e    内部异常
     */
    public ProgramException(String code, Object[] args, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.args = args;
    }


    /**
     * 获取异常码
     *
     * @return
     * @author huanghuafeng 2013-11-4 下午05:01:39
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置异常码
     *
     * @param code
     * @author huanghuafeng 2013-11-4 下午05:01:45
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * 获取格式化参数
     *
     * @return
     * @author huanghuafeng 2013-11-4 下午05:01:50
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * 设置格式化参数
     *
     * @param args
     * @author huanghuafeng 2013-11-4 下午05:02:00
     */
    public void setArgs(Object[] args) {
        this.args = args;
    }
}

