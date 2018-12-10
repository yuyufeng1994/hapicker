package com.hapicker.mapper;

import com.hapicker.common.dto.MessageInfoDTO;
import com.hapicker.model.MessageInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageInfoMapper extends Mapper<MessageInfo> {
    /**
     * 查询
     * @param messageId
     * @return
     */
    MessageInfoDTO selectById(@Param("messageId") Long messageId);

    /**
     * 差群列表
     * @param objInfo
     * @return
     */
    List<MessageInfoDTO> selectList(MessageInfo objInfo);
}