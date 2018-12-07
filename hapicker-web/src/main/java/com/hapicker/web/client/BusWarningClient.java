package com.hapicker.web.client;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.BusWarningInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.common.dto.ResponseDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuyufeng
 * @date 2018/12/7.
 */
@FeignClient(value = "hapicker-service")
@RequestMapping(value = "busWarning")
public interface BusWarningClient {

    /**
     * 插入监听
     * @param busWarningInfoDTO
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    ResponseDTO insert(@RequestBody BusWarningInfoDTO busWarningInfoDTO);

    /**
     * 查询监听
     * @param requestPageDTO
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    ResponseDTO<PageInfo<BusWarningInfoDTO>> query(@RequestBody RequestPageDTO<BusWarningInfoDTO> requestPageDTO);

}
