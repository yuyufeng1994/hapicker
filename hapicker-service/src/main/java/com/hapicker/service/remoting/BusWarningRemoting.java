package com.hapicker.service.remoting;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.*;
import com.hapicker.service.intef.IBusWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuyufeng
 * @date 2018/12/7.
 */
@RestController
@Api(description = "车次监听")
@RequestMapping("busWarning")
public class BusWarningRemoting {

    @Autowired
    private IBusWarningService busWarningService;

    @ApiOperation(value = "插入", httpMethod = "POST")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    ResponseDTO insert(@RequestBody BusWarningInfoDTO busWarningInfoDTO) {
        try {
            busWarningService.insert(busWarningInfoDTO);
        } catch (DuplicateKeyException e) {
            return ResponseDTO.fail("车次已存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseDTO.success();
    }

    @ApiOperation(value = "查询", httpMethod = "POST")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    ResponseDTO<PageInfo<BusWarningInfoDTO>> query(@RequestBody RequestPageDTO<BusWarningInfoDTO> requestPageDTO) {
        PageInfo<BusWarningInfoDTO> articleInfoPageInfo = busWarningService.query(requestPageDTO);
        return ResponseDTO.success(articleInfoPageInfo);
    }

}
