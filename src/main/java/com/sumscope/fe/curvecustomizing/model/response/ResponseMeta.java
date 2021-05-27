package com.sumscope.fe.curvecustomizing.model.response;

/**
 * @Author: sky.zhang
 * @Date: 2020/9/2 16:08
 */
public class ResponseMeta {
    private Integer errNum;
    private String errMsg;
    private String requestMethod;
    private String requestURI;

    public ResponseMeta() {
        this.errNum = 0;
        this.errMsg = "success";
    }

    public ResponseMeta(Integer errNum, String errMsg) {
        this.errNum = errNum;
        this.errMsg = errMsg;
    }

    public Integer getErrNum() {
        return errNum;
    }

    public void setErrNum(Integer errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }
}
