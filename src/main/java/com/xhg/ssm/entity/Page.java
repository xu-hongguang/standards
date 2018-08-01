package com.xhg.ssm.entity;

import java.util.List;

public class Page<E> {
    /**
     * 首页
     */
    private int pageNo;
    //页容量
    private int pageSize;
    //总页数
    private int pageCounts;
    //总记录
    private int totalCounts;
    //存储学生列表
    private List<E> rows;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo > 0) {
            this.pageNo = pageNo;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getPageCounts() {
        return pageCounts;
    }

    /**设置 总页数 = (总记录数+每页显示数量-1)/每页显示数量 */
    public void setPageCounts() {
        this.pageCounts = (totalCounts + pageSize - 1) / pageSize;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        if (totalCounts >= 0) {
            this.totalCounts = totalCounts;
        }
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
