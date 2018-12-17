package com.csvalue.model;

import java.io.Serializable;

/*
* 返回结果
* */
public class CertificateResult implements Serializable {
    private String dn;
    private String serialNo;

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
