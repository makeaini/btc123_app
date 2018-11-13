package com.btc123.app.bean.request;

import com.btc123.app.bean.BaseBean;

/**
 * Created by shining on 2018/5/11.
 */
public class QueryPageBean extends BaseBean{
    private int pageNumber=1;
    private int pageSize=10;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
