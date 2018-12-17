package com.csvalue.service.impl;

import com.csvalue.utils.CfcaDbUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/*
* 初始化表结构
* */
@Component
public class InitCfcaDB {
    final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @PostConstruct
    public void init(){
        System.out.println("初始化表结构开始....");
        log.debug("初始化表结构....");
        CfcaDbUtil.run();
    }
}
