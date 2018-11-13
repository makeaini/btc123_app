package com.btc123.app.service;

import com.btc123.app.entity.AppVersion;
import com.btc123.app.exception.ProValiDataException;

/**
 * Created by shining on 2018/5/17.
 */
public interface AppVersionServiceI {
    /**
     * 通过老版本查询是否有新版本
     * @param version 当前版本
     * @param deviceType 设备类型
     * @return
     */
    public AppVersion findByVersion(Long version, Integer deviceType) throws ProValiDataException;
}
