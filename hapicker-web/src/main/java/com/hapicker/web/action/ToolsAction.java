package com.hapicker.web.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.hapicker.common.dto.ResponseJson;
import com.hapicker.common.dto.TrainInfoDTO;
import com.hapicker.common.service.BusServices;
import com.hapicker.common.service.TrainServices;
import com.hapicker.common.vo.ScheduleBusVO;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuyufeng
 * @date 2018/12/13.
 */
@Controller
@RequestMapping(value = "tools")
public class ToolsAction {
    /**
     * 二维码服务
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        return "tools/index";
    }

    /**
     * 二维码服务
     *
     * @return
     */
    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public String toQrcode() {
        return "tools/qrcode";
    }

    /**
     * 二维码
     *
     * @param content
     * @param response
     * @throws IOException
     * @throws WriterException
     */

    @RequestMapping(value = "/doQrcode", method = RequestMethod.GET)
    public void doQrcode(String content, HttpServletResponse response) throws IOException, WriterException {
        if (StringUtils.isEmpty(content)) {
            return;
        }
        if (content.length() > 200) {
            content = content.substring(0, 200);
        }
        OutputStream os = response.getOutputStream();
        int width = 200; // 图像宽度
        int height = 200; // 图像高度
        String format = "png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
        MatrixToImageWriter.writeToStream(bitMatrix, format, os);
    }

    /**
     * 二维码下载
     *
     * @param content
     * @param response
     * @throws IOException
     * @throws WriterException
     */

    @RequestMapping(value = "/doQrcodeDownload", method = RequestMethod.GET)
    public void doQrcodeDownload(String content, HttpServletResponse response) throws IOException, WriterException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=qrcode.png");
        if (StringUtils.isEmpty(content)) {
            return;
        }
        if (content.length() > 200) {
            content = content.substring(0, 200);
        }
        OutputStream os = response.getOutputStream();
        int width = 200; // 图像宽度
        int height = 200; // 图像高度
        String format = "png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
        MatrixToImageWriter.writeToStream(bitMatrix, format, os);
    }

    @RequestMapping(value = "/train", method = RequestMethod.GET)
    public String toTrain() {
        return "tools/train";
    }


    /**
     * 查询火车票
     *
     * @param scheduleBusVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "train/querySchedule")
    public @ResponseBody
    ResponseJson querySchedule(@RequestBody ScheduleBusVO scheduleBusVO) throws Exception {
        try {
            List<TrainInfoDTO> result = TrainServices.searchTrainList(scheduleBusVO.getDeparture(), scheduleBusVO.getDestination(), scheduleBusVO.getBusDate());
            return ResponseJson.success(result);
        } catch (Exception e) {
            return ResponseJson.fail("车次未找到");
        }
    }

}
