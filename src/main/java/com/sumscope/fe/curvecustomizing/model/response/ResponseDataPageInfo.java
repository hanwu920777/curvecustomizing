package com.sumscope.fe.curvecustomizing.model.response;

/**
 * @Author: sky.zhang
 * @Date: 2020/9/2 16:10
 */
public class ResponseDataPageInfo {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
