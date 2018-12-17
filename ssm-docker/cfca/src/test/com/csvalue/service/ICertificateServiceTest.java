package com.csvalue.service;


import com.csvalue.common.ResponseResult;
import com.csvalue.model.CertificateParam;
import com.csvalue.model.CertificateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/spring-*.xml")
public class ICertificateServiceTest {
    @Autowired
    private ICertificateService service;

    @Test
    public void getApplyCert() {
        String p10="";
        CertificateParam certificateParam=new CertificateParam();
        certificateParam.setBranchCode("678");
        certificateParam.setCaName("ca");
        certificateParam.setCertType("1");
        certificateParam.setCustomerType("2");
        certificateParam.setEmail("yufeng@jd.com");
        certificateParam.setIdentNo("1001");
        certificateParam.setIdentType("M");
        certificateParam.setKeyAlg("RSA");
        certificateParam.setKeyLength("2048");
        certificateParam.setP10(p10);
        certificateParam.setUserName("kaixinyufeng");
        ResponseResult<CertificateResult> certificateResultResponseResult=service.getApplyCert(certificateParam);
        System.out.println(certificateResultResponseResult.getMsg());
        System.out.println(certificateResultResponseResult.getCode());
    }

    @Test
    public void getRevokeCert() {
        String dn="test";
        ResponseResult responseResult=service.getRevokeCert(dn);
        System.out.println(responseResult.getCode());
        System.out.println(responseResult.getMsg());
    }
}

