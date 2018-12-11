package com.hapicker.mapper;

import com.hapicker.model.BusWarningInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BusWarningInfoMapper extends Mapper<BusWarningInfo> {
    /**
     * 查询IDs
     * @param pageNo
     * @return
     */
    List<Long> selectWarningIds(@Param("pageNo") int pageNo);

    /**
     * 查询对象
     * @param pageNo
     * @return
     */
    List<BusWarningInfo> selectWarningBus(@Param("pageNo") int pageNo,@Param("upTime") int upTime);
}