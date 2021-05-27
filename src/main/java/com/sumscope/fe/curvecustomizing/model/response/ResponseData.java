package com.sumscope.fe.curvecustomizing.model.response;

import java.util.Map;

/**
 * @Author: sky.zhang
 * @Date: 2020/9/2 16:08
 */
public class ResponseData<T> {
    private ResponseDataPageInfo pageInfo;
    private T result;

    public ResponseData() {
        this.result = null;
        this.pageInfo = null;
    }

    public ResponseData(T result) {
        this.result = result;
    }

    public ResponseDataPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(ResponseDataPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}