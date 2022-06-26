package com.qfedu.base;


public class PageQuery<T> {
    private Integer startRow;
    private Integer limit;
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
