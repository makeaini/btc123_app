package com.btc123.app.mapper;

import com.btc123.app.entity.UserMarket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMarketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserMarket record);

    int insertSelective(UserMarket record);

    UserMarket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMarket record);

    int updateByPrimaryKey(UserMarket record);

    int deleteUserMarket(UserMarket userMarket);

    List<String> selectByUserId(@Param("userId") Long userId,@Param("pageBegin") Integer pageBegin,@Param("pageSize") Integer pageSize);

    List<UserMarket> isUserMarket(UserMarket userMarket);
}