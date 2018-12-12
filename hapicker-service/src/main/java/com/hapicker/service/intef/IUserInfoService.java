package com.hapicker.service.intef;

import com.hapicker.common.dto.UserConnectInfoDTO;
import com.hapicker.common.dto.UserInfoDTO;

import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/10/25.
 */
public interface IUserInfoService {

    /**
     * 插入用户互联
     * @param userConnectInfo
     */
    void insertUserConnectInfo(UserConnectInfoDTO userConnectInfo);

    /**
     * 插入用户和用户互联
     * @param userInfoDTO
     * @return
     */
    UserInfoDTO insertUserInfo(UserInfoDTO userInfoDTO);

    /**
     * 查询用户
     * @param userInfoDTO
     * @return
     */
    UserInfoDTO getUserInfoDTO(UserInfoDTO userInfoDTO);

    /**
     * 查询用户列表
     * @param userInfoDTO
     * @return
     */
    List<UserInfoDTO> queryUserInfoDTO(UserInfoDTO userInfoDTO);

    /**
     * 查询用户互联信息
     * @param userConnectInfo
     * @return
     */
    UserConnectInfoDTO getUserConnectInfo(UserConnectInfoDTO userConnectInfo);

    /**
     * 修改信息
     * @param userInfo
     */
    void updateUserInfo(UserInfoDTO userInfo);
}
