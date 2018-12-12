package com.hapicker.service.impl;

import com.hapicker.common.dto.UserConnectInfoDTO;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.mapper.UserConnectInfoMapper;
import com.hapicker.mapper.UserInfoMapper;
import com.hapicker.model.UserConnectInfo;
import com.hapicker.model.UserInfo;
import com.hapicker.service.intef.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/10/25.
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserConnectInfoMapper userConnectInfoMapper;


    @Override
    public UserConnectInfoDTO getUserConnectInfo(UserConnectInfoDTO userConnectInfoDTO) {
        UserConnectInfo userConnectInfoQuery = new UserConnectInfo();
        BeanUtils.copyProperties(userConnectInfoDTO,userConnectInfoQuery);
        UserConnectInfo userConnectInfo = userConnectInfoMapper.selectOne(userConnectInfoQuery);
        if(userConnectInfo == null){
            return null;
        }
        BeanUtils.copyProperties(userConnectInfo,userConnectInfoDTO);
        return userConnectInfoDTO;
    }

    @Override
    public void updateUserInfo(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO,userInfo);
        userInfo.setUpdateTime(new Date());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public void insertUserConnectInfo(UserConnectInfoDTO userConnectInfoDTO){
        UserConnectInfo userConnectInfo = new UserConnectInfo();
        BeanUtils.copyProperties(userConnectInfoDTO,userConnectInfo);
        Date date = new Date();
        userConnectInfo.setCreateTime(date);
        userConnectInfo.setUpdateTime(date);
        userConnectInfoMapper.insertSelective(userConnectInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoDTO insertUserInfo(UserInfoDTO userInfoDTO){
        Date date = new Date();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO,userInfo);
        userInfo.setUserId(null);
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        userInfoMapper.insertSelectiveWidthGenerateKey(userInfo);

        if(!StringUtils.isEmpty(userInfoDTO.getPlatformKey())&!StringUtils.isEmpty(userInfoDTO.getOpenId())){
            UserConnectInfo userConnectInfo = new UserConnectInfo();
            userConnectInfo.setUserId(userInfo.getUserId());
            userConnectInfo.setOpenId(userInfoDTO.getOpenId());
            userConnectInfo.setPlatformKey(userInfoDTO.getPlatformKey());
            userConnectInfo.setCreateTime(date);
            userConnectInfo.setUpdateTime(date);
            userConnectInfoMapper.insertSelective(userConnectInfo);
        }
        BeanUtils.copyProperties(userInfo,userInfoDTO);
        return userInfoDTO;
    }


    @Override
    public UserInfoDTO getUserInfoDTO(UserInfoDTO userInfoDTO) {
        UserInfo userInfoQuery = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfoQuery);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoQuery);
        UserInfoDTO userInfoDTOResult = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTOResult);
        return userInfoDTOResult;
    }

    @Override
    public List<UserInfoDTO> queryUserInfoDTO(UserInfoDTO userInfoDTO) {
        List<UserInfoDTO> userInfoDTOResult = new ArrayList<>();
        UserInfo userInfoQuery = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfoQuery);
        List<UserInfo> userInfos = userInfoMapper.select(userInfoQuery);
        for (UserInfo userInfo : userInfos) {
            UserInfoDTO userInfoDTOTemp = new UserInfoDTO();
            BeanUtils.copyProperties(userInfo, userInfoDTOTemp);
            userInfoDTOResult.add(userInfoDTO);
        }
        return userInfoDTOResult;
    }


}
