package com.hapicker.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.MessageInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.mapper.MessageInfoMapper;
import com.hapicker.model.MessageInfo;
import com.hapicker.service.intef.IMessageInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
@Service
public class MessageInfoServiceImpl implements IMessageInfoService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Override
    public void insert(MessageInfoDTO messageInfoDTO) {
        MessageInfo messageInfo = new MessageInfo();
        BeanUtils.copyProperties(messageInfoDTO, messageInfo);
        messageInfo.setCreateTime(new Date());
        messageInfo.setUpdateTime(new Date());
        messageInfoMapper.insertSelective(messageInfo);
    }

    @Override
    public void update(MessageInfoDTO messageInfoDTO) {
        MessageInfo messageInfo = new MessageInfo();
        BeanUtils.copyProperties(messageInfoDTO, messageInfo);
        messageInfo.setCreateTime(null);
        messageInfo.setUpdateTime(new Date());
        messageInfoMapper.updateByPrimaryKey(messageInfo);
    }

    @Override
    public MessageInfoDTO query(Long messageId) {
        MessageInfoDTO record = messageInfoMapper.selectById(messageId);
        return record;
    }

    @Override
    public PageInfo<MessageInfoDTO> query(RequestPageDTO<MessageInfoDTO> requestPageDTO) {
        MessageInfo objInfo = new MessageInfo();
        BeanUtils.copyProperties(requestPageDTO.getContent(), objInfo);
        PageHelper.startPage(requestPageDTO.getPageNo(), requestPageDTO.getPageSize(), requestPageDTO.getOrderBy());
        List<MessageInfoDTO> objInfoList = messageInfoMapper.selectList(objInfo);
        PageInfo<MessageInfoDTO> objInfoPageInfo = new PageInfo<>(objInfoList);
        return objInfoPageInfo;
    }

   /* @Override
    public PageInfo<MessageInfoDTO> query(RequestPageDTO<MessageInfoDTO> requestPageDTO) {
        MessageInfo objInfo = new MessageInfo();
        BeanUtils.copyProperties(requestPageDTO.getContent(), objInfo);
        PageHelper.startPage(requestPageDTO.getPageNo(), requestPageDTO.getPageSize(), requestPageDTO.getOrderBy());
        List<MessageInfo> objInfoList = messageInfoMapper.select(objInfo);
        PageInfo<MessageInfo> objInfoPageInfo = new PageInfo<>(objInfoList);
        Page<MessageInfoDTO> objInfoDTOList = new Page<>(requestPageDTO.getPageNo(), requestPageDTO.getPageSize());
        if (objInfoList != null) {
            for (MessageInfo warningInfo : objInfoList) {
                MessageInfoDTO objDTO = new MessageInfoDTO();
                BeanUtils.copyProperties(warningInfo, objDTO);
                objInfoDTOList.add(objDTO);
            }
        }
        objInfoDTOList.setTotal(objInfoPageInfo.getTotal());
        PageInfo<MessageInfoDTO> busWarningInfoDTOPageInfo = new PageInfo<>(objInfoDTOList);
        return busWarningInfoDTOPageInfo;
    }*/



}
