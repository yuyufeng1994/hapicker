package com.hapicker.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hapicker.common.constant.BusWarningStatusEnum;
import com.hapicker.common.dto.BusWarningInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.mapper.BusWarningInfoMapper;
import com.hapicker.model.BusWarningInfo;
import com.hapicker.service.intef.IBusWarningService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/12/7.
 */
@Service
public class BusWarningServiceImpl implements IBusWarningService {
    @Autowired
    private BusWarningInfoMapper busWarningInfoMapper;

    @Override
    public void insert(BusWarningInfoDTO busWarningInfoDTO) {
        Date date = new Date();
        BusWarningInfo busWarningInfo = new BusWarningInfo();
        BeanUtils.copyProperties(busWarningInfoDTO, busWarningInfo);
        busWarningInfo.setWarningStatus(BusWarningStatusEnum.WARNING.getKey());
        busWarningInfo.setWarningTimes(0);
        busWarningInfo.setCreateTime(date);
        busWarningInfo.setUpdateTime(date);
        busWarningInfoMapper.insertSelective(busWarningInfo);
    }

    @Override
    public void update(BusWarningInfoDTO busWarningInfoDTO) {
        Date date = new Date();
        BusWarningInfo busWarningInfo = new BusWarningInfo();
        BeanUtils.copyProperties(busWarningInfoDTO, busWarningInfo);
        busWarningInfo.setCreateTime(null);
        busWarningInfo.setUpdateTime(date);
        busWarningInfoMapper.updateByPrimaryKeySelective(busWarningInfo);
    }

    @Override
    public BusWarningInfoDTO query(BusWarningInfoDTO busWarningInfoDTO) {
        BusWarningInfo busWarningInfo = new BusWarningInfo();
        BeanUtils.copyProperties(busWarningInfoDTO, busWarningInfo);
        BusWarningInfoDTO busWarningInfoDTORecord = new BusWarningInfoDTO();
        busWarningInfoMapper.select(busWarningInfo);
        BeanUtils.copyProperties(busWarningInfo, busWarningInfoDTORecord);
        return busWarningInfoDTORecord;
    }

    @Override
    public PageInfo<BusWarningInfoDTO> query(RequestPageDTO<BusWarningInfoDTO> requestPageDTO) {
        BusWarningInfo busWarningInfo = new BusWarningInfo();
        BeanUtils.copyProperties(requestPageDTO.getContent(), busWarningInfo);
        PageHelper.startPage(requestPageDTO.getPageNo(), requestPageDTO.getPageSize(), requestPageDTO.getOrderBy());
        List<BusWarningInfo> busWarningInfoList = busWarningInfoMapper.select(busWarningInfo);
        List<BusWarningInfoDTO> busWarningInfoDTOList = new ArrayList<>();
        if (busWarningInfoList != null) {
            for (BusWarningInfo warningInfo : busWarningInfoList) {
                BusWarningInfoDTO busWarningInfoDTO = new BusWarningInfoDTO();
                BeanUtils.copyProperties(warningInfo, busWarningInfoDTO);
                busWarningInfoDTOList.add(busWarningInfoDTO);
            }
        }
        PageInfo<BusWarningInfoDTO> busWarningInfoDTOPageInfo = new PageInfo<>(busWarningInfoDTOList);
        return busWarningInfoDTOPageInfo;
    }

    @Override
    public void delete(Long id) {
        busWarningInfoMapper.deleteByPrimaryKey(id);
    }
}
