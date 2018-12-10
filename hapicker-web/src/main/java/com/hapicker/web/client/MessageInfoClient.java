package com.hapicker.web.client;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.MessageInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.common.dto.ResponseDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
@FeignClient(value = "hapicker-service")
@RequestMapping(value = "message")
public interface MessageInfoClient {
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    ResponseDTO insert(@RequestBody MessageInfoDTO messageInfoDTO);

    @RequestMapping(value = "get/{messageId}", method = RequestMethod.GET)
    ResponseDTO<MessageInfoDTO> get(@PathVariable("messageId") Long messageId);

    @RequestMapping(value = "query", method = RequestMethod.POST)
    ResponseDTO<PageInfo<MessageInfoDTO>> query(@RequestBody RequestPageDTO<MessageInfoDTO> requestPageDTO);
}
