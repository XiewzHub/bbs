package com.hnie.forum.exception;

/**
 * Created by xiewz on 2018/6/26.\
 * 自定义异常
 * 默认返回码9999 表示系统异常
 *
 */
public class BbsException extends RuntimeException {
    private String errCode = "9999" ;  //异常对应的返回码
    private String errMsg;  //异常对应的描述信息

    public BbsException() {
        super();
    }

    public BbsException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public BbsException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BbsException(String errMsg,Throwable cause) {
        super(errMsg,cause);
        this.errMsg = errMsg;
    }
    public BbsException(Throwable cause) {
        super(cause);
        this.errMsg = "系统内部错误";
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
