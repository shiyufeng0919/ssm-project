package com.csvalue.utils;

import cfca.ra.toolkit.RAClient;
import cfca.ra.toolkit.exception.RATKException;
import com.csvalue.common.PropertiesUtils;

/**
 * 配置文件
 * */
public class CertificateConfig {
    public static final int connectTimeout = Integer.parseInt(PropertiesUtils.getProperty("conf.connectTimeout"));
    public static final int readTimeout = Integer.parseInt(PropertiesUtils.getProperty("conf.readTimeout"));

    public static final String url = PropertiesUtils.getProperty("conf.url");

    public static RAClient getRAClient() throws RATKException {
        int type = 1;
        RAClient client = null;
        client = new RAClient(url, connectTimeout, readTimeout);
        return client;
    }
}
