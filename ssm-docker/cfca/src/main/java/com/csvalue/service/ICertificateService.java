package com.csvalue.service;

import com.csvalue.common.ResponseResult;
import com.csvalue.model.CertificateParam;
import com.csvalue.model.CertificateResult;

public interface ICertificateService {
    ResponseResult<CertificateResult> getApplyCert(CertificateParam certificateParam);
    ResponseResult getRevokeCert(String dn);
}
