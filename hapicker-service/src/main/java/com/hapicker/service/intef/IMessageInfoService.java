package com.hapicker.service.intef;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.BusWarningInfoDTO;
import com.hapicker.common.dto.MessageInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.model.MessageInfo;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
public interface IMessageInfoService {

    /**
     * 插入
     * @param messageInfoDTO
     */
    void insert(MessageInfoDTO messageInfoDTO);

    /**
     * 更新
     * @param messageInfoDTO
     */
    void update(MessageInfoDTO messageInfoDTO);

    /**
     * 查询
     * @param messageId
     * @return
     */
    MessageInfoDTO query(Long messageId);


    /**
     * 分页查询
     * @param requestPageDTO
     * @return
     */
    PageInfo<MessageInfoDTO> query(RequestPageDTO<MessageInfoDTO> requestPageDTO);


}
