package com.btc123.app.service.impl;

import com.btc123.app.entity.AppVersion;
import com.btc123.app.enumeration.BusinessEnum;
import com.btc123.app.exception.ProValiDataException;
import com.btc123.app.mapper.AppVersionMapper;
import com.btc123.app.service.AppVersionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by shining on 2018/5/17.
 */
@Service
public class AppVersionServiceImpl implements AppVersionServiceI {
    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public AppVersion findByVersion(Long version, Integer deviceType) throws ProValiDataException {
        AppVersion appVersion = appVersionMapper.selectMaxVersion(version, deviceType);
        Optional.ofNullable(appVersion).orElseThrow(() -> new ProValiDataException(BusinessEnum.VERSION_NEW_ERROR.getCode(),
                BusinessEnum.VERSION_NEW_ERROR.getMessage()));
        //强制更新
        if (appVersion.getUpdateType().intValue() == 1) {
            return appVersion;
        }
        //查询当前版本和最大版本是否有需要强制更新的版本,如果其实中一个版本需要强制更新就把当前版本设置为强制更新的版本
        int count = appVersionMapper.selectBetweenVersionCount(version, appVersion.getNewVersion(), deviceType);
        if (count > 0) {
            appVersion.setUpdateType(1);
        }
        return appVersion;
    }
}
