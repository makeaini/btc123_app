package com.btc123.app.bean;

import com.btc123.app.entity.IEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by btc on 2018/5/16.
 */
public class PageBean<T> implements IEntity {
    private int pageNumber;
    private int pageSize;
    private long total;
    private List<T> list;



    public PageBean(Integer pageNumber, Integer pageSize, long total, List<T> list) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public PageBean(PageInfo pageInfo) {
        this.pageNumber = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        this.list = pageInfo.getList();
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return list;
    }

    public void setData(List<T> data) {
        this.list = data;
    }

    public PageBean(List<T> data) {
        this.list = data;
    }
}
