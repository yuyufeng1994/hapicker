package com.hapicker.service.remoting;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.BusWarningInfoDTO;
import com.hapicker.common.dto.MessageInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.service.intef.IMessageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
@RestController
@Api(description = "消息")
@RequestMapping("message")
public class MessageInfoRemoting {

    @Autowired
    private IMessageInfoService messageInfoService;

    @ApiOperation(value = "插入", httpMethod = "POST")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    ResponseDTO insert(@RequestBody MessageInfoDTO messageInfoDTO) {
        messageInfoService.insert(messageInfoDTO);
        return ResponseDTO.success();
    }

    @ApiOperation(value = "查询", httpMethod = "GET")
    @RequestMapping(value = "get/{messageId}", method = RequestMethod.GET)
    ResponseDTO<MessageInfoDTO> get(@PathVariable("messageId") Long messageId) {
        MessageInfoDTO result = messageInfoService.query(messageId);
        return ResponseDTO.success(result);
    }

    @ApiOperation(value = "分页查询", httpMethod = "POST")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    ResponseDTO<PageInfo<MessageInfoDTO>> query(@RequestBody RequestPageDTO<MessageInfoDTO> requestPageDTO) {
        PageInfo<MessageInfoDTO> articleInfoPageInfo = messageInfoService.query(requestPageDTO);
        return ResponseDTO.success(articleInfoPageInfo);
    }

}
