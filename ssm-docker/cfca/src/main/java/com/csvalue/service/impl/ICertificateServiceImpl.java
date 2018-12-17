package com.csvalue.service.impl;

import cfca.ra.common.vo.request.CertServiceRequestVO;
import cfca.ra.common.vo.request.TxRequestVO;
import cfca.ra.common.vo.response.CertServiceResponseVO;
import cfca.ra.common.vo.response.TxResponseVO;
import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;
import com.alibaba.fastjson.JSON;
import com.csvalue.common.ResponseCode;
import com.csvalue.common.ResponseResult;
import com.csvalue.model.CertificateParam;
import com.csvalue.model.CertificateResult;
import com.csvalue.service.ICertificateService;
import com.csvalue.utils.CertificateConfig;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ICertificateServiceImpl implements ICertificateService {

    final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    public ResponseResult<CertificateResult> getApplyCert(CertificateParam certificateParam){
        CertificateResult certificateResult = new CertificateResult();
        try {
            RAClient client = CertificateConfig.getRAClient();
            TxRequestVO txRequestVO = new TxRequestVO();
            txRequestVO.setTxCode("xxxx");
            TxResponseVO txResponseVO = (TxResponseVO) client.process(txRequestVO);
            if (!"xxxx".equals(txResponseVO.getResultCode())) {
                return ResponseResult.error(ResponseCode.DISCONNECT);
            }
            //设置请求参数
            CertServiceRequestVO certServiceRequestVO = setCertServiceRequestVO(certificateParam);
            //接收返回结果
            CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);

            log.info("ResultCode:", certServiceResponseVO.getResultCode()); //0000
            log.info("resultMsg:", certServiceResponseVO.getResultMessage()); //成功
            //成功获取
            if (RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())) {
                //设置返回结果
                certificateResult = setCertificateResult(certServiceResponseVO);
                log.info("返回申请结果：", JSON.toJSON(certificateResult));
            } else {
                return ResponseResult.error(Integer.parseInt(certServiceResponseVO.getResultCode()),certServiceResponseVO.getResultMessage());
            }
        } catch (RATKException e) {
            log.error(e.getMessage(), e);
            return ResponseResult.error(ResponseCode.FAIL);
        }
        return ResponseResult.success(certificateResult);
    }

    @Override
    public ResponseResult getRevokeCert(String dn) {
        try {
            RAClient client = CertificateConfig.getRAClient();
            CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
            certServiceRequestVO.setTxCode("yyyy");
            certServiceRequestVO.setDn(dn);
            CertServiceResponseVO certServiceResponseVO = (CertServiceResponseVO) client.process(certServiceRequestVO);
            log.info("返回状态码和状态值：",certServiceResponseVO.getResultCode(),certServiceResponseVO.getResultMessage());
            if(!RAClient.SUCCESS.equals(certServiceResponseVO.getResultCode())){ //成功状态
                return ResponseResult.error(Integer.parseInt(certServiceResponseVO.getResultCode()),certServiceResponseVO.getResultMessage());
            }
            log.info("返回结果:",certServiceResponseVO.getResultCode(),certServiceResponseVO.getResultMessage());
        } catch(RATKException e) {
            log.error(e.getMessage(), e);
            return ResponseResult.error(ResponseCode.FAIL);
        }
        return ResponseResult.success(ResponseCode.OK);
    }

    /*设置请求参数*/
    public CertServiceRequestVO setCertServiceRequestVO(CertificateParam certificateParam){
        CertServiceRequestVO certServiceRequestVO = new CertServiceRequestVO();
        certServiceRequestVO.setTxCode("yyyy");
        certServiceRequestVO.setCaName(certificateParam.getCaName());
        certServiceRequestVO.setCertType(certificateParam.getCertType());
        return certServiceRequestVO;
    }

    /*设置返回结果*/
    public CertificateResult setCertificateResult(CertServiceResponseVO certServiceResponseVO){
        CertificateResult certificateResult=new CertificateResult();
        certificateResult.setDn(certServiceResponseVO.getDn());
        return certificateResult;
    }

    //处理成pem格式
    public String dealCertToPem(String signatureCert){
        log.info("signatureCert:",signatureCert);
        String pem="-----BEGIN CERTIFICATE-----"+"\n";
        String pemEnd="-----END CERTIFICATE-----";
        String[] result=splitStringByLength(signatureCert,64);
        for(int i = 0; i < result.length; i++){
            pem=pem+result[i]+"\n";
        }
        pem=pem+pemEnd;
        log.info("pem:",pem);
        return pem;
    }

    /***
     * 将字符串按固定长度切割成字符子串
     * @param src 需要切割的字符串
     * @param length 字符子串的长度
     * @return 字符子串数组
     */
    public String[] splitStringByLength(String src, int length) {
        //检查参数是否合法
        if (null == src||src.equals("")) {
            log.info("the string is null");
            return null;
        }
        if (length <= 0) {
            log.info("the length < 0");
            return null;
        }
        log.info("now split \"" + src + "\" by length " + length);
        int n = (src.length() + length - 1) / length; //获取整个字符串可以被切割成字符子串的个数
        String[] split = new String[n];
        for (int i = 0; i < n; i++) {
            if (i < (n -1)) {
                split[i] = src.substring(i * length, (i + 1) * length);
            } else {
                split[i] = src.substring(i * length);
            }
        }
        return split;
    }
}
