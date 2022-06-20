package com.qfedu.base;


public class PageQuery<T> {
    private Integer startRow;//起始行数
    private Integer limit;//要查询的结果行数
    private T queryCondition;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public T getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(T queryCondition) {
        this.queryCondition = queryCondition;
    }
}
