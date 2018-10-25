package com.hapicker.mapper;

import com.hapicker.model.UserInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author yuyufeng
 */
public interface UserInfoMapper extends Mapper<UserInfo> {

    void insertSelectiveWidthGenerateKey(UserInfo userInfo);
}