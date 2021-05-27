package com.sumscope.fe.curvecustomizing.model.response;

/**
 * @Author: sky.zhang
 * @Date: 2020/9/2 16:07
 */
public class GeneralResponse {
    private ResponseData data;
    private ResponseMeta meta;

    public GeneralResponse() {
        this.meta = new ResponseMeta();
        this.data = new ResponseData();
    }

    public GeneralResponse(Integer errNum, String errMsg) {
        this.meta = new ResponseMeta(errNum, errMsg);
        this.data = new ResponseData();
    }

    public GeneralResponse(String errMsg) {
        this.meta = new ResponseMeta(-1, errMsg);
        this.data = new ResponseData();
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    public ResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(ResponseMeta meta) {
        this.meta = meta;
    }
}
