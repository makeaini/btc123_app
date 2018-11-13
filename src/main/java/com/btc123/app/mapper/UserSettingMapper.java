package com.btc123.app.mapper;

import com.btc123.app.entity.UserSetting;
import org.apache.ibatis.annotations.Param;

public interface UserSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserSetting record);

    int insertSelective(UserSetting record);

    UserSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSetting record);

    int updateByPrimaryKey(UserSetting record);

    /**
     * 通过类型查询用户设置
     * @param userId 用户Id
     * @param settingType 1:k线设置,2：价格设置
     * @return
     */
    UserSetting selectByUserIdAndType(@Param("userId") Long userId,@Param("settingType") Integer settingType);
}