package com.csvalue.model;

import java.io.Serializable;

//参数实体类
public class CertificateParam implements Serializable {
    private String caName;
    private String certType;

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }
}
