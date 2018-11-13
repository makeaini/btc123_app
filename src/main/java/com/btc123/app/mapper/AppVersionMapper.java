package com.btc123.app.mapper;

import com.btc123.app.entity.AppVersion;
import org.apache.ibatis.annotations.Param;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    /**
     * 获取当前最新版本
     *
     * @param version    当前版本
     * @param deviceType 设备类型
     * @return
     */
    AppVersion selectMaxVersion(@Param("version") Long version, @Param("deviceType") Integer deviceType);

    /**
     *  查询当前版本和最大版本是否有需要强制更新的版本
     * @param version 当前版本
     * @param maxVersion 最大版本
     * @param deviceType 设备类型
     * @return
     */
    int selectBetweenVersionCount(@Param("version") Long version, @Param("maxVersion") Long maxVersion, @Param("deviceType") Integer deviceType);

}