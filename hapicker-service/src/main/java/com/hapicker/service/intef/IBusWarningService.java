package com.hapicker.service.intef;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.BusWarningInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;

import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/12/7.
 */
public interface IBusWarningService {
    /**
     * 插入一条车次监听
     * @param busWarningInfoDTO
     */
    void insert(BusWarningInfoDTO busWarningInfoDTO);

    /**
     * 更新
     * @param busWarningInfoDTO
     */
    void update(BusWarningInfoDTO busWarningInfoDTO);

    /**
     * 查询单个
     * @param busWarningInfoDTO
     * @return
     */
    BusWarningInfoDTO query(BusWarningInfoDTO busWarningInfoDTO);

    /**
     * 分页查询
     * @param requestPageDTO
     * @return
     */
    PageInfo<BusWarningInfoDTO> query(RequestPageDTO<BusWarningInfoDTO> requestPageDTO);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
}
