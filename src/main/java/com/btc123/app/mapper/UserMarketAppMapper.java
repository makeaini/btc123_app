package com.btc123.app.mapper;

import com.btc123.app.entity.UserMarketApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMarketAppMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMarketApp record);

    int insertSelective(UserMarketApp record);

    UserMarketApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMarketApp record);

    int updateByPrimaryKey(UserMarketApp record);

    int deleteUserMarket(UserMarketApp userMarketApp);

    List<UserMarketApp> selectByToken(@Param("token") String token);

    Long selectByMaxSort(String token);

    Integer selectCountByTokenAndSign(UserMarketApp userMarketApp);
}