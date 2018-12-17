package com.csvalue.controller;
import com.alibaba.fastjson.JSON;
import com.csvalue.common.ResponseResult;
import com.csvalue.model.CertificateParam;
import com.csvalue.model.CertificateResult;
import com.csvalue.service.ICertificateService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello")
public class CertificateController {

    final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ICertificateService ICertificateService;

    @ResponseBody
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseResult<CertificateResult> applyCertificate(@RequestBody CertificateParam certificateParam) {
        log.info("接收到的参数：", JSON.toJSONString(certificateParam));
        ResponseResult<CertificateResult> certificateResult= ICertificateService.getApplyCert(certificateParam);
        log.info("请求返回结果:",certificateResult);
        return certificateResult;
    }


    /*
    * 撤销
    * */
    @ResponseBody
    @RequestMapping(value = "/revoke", method = RequestMethod.POST)
    public ResponseResult revokeCertificate(String dn) {
        log.info("接收到的参数：", dn);
        ResponseResult responseResult= ICertificateService.getRevokeCert(dn);
        return responseResult;
    }
}
