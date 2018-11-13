package com.btc123.app.enumeration;


import java.util.Arrays;
import java.util.Optional;

/**
 * 广告类型枚举
 *
 * @author liyi
 * @create 2018-01-14 16:34
 **/
public enum LockStatusEnum {

    /**
     * 锁定
     */
    LOCK(2),
    /**
     * 未锁定
     */
    UN_LOCK(1);

    private int status;
    


    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

    LockStatusEnum(int status){
        this.status = status;
    }

}
