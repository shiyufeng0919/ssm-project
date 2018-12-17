package com.csvalue.service.impl;

import com.csvalue.common.RuntimeUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
* Spring容器启动即启动sh脚本文件
* 测试环境应用--随spring容器启动即加载，现通过kms生成p10（csr），暂不需要用工具生成p10
* */
@Component
public class P10Server {
    final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
//    @PostConstruct
    public void init(){
        System.out.println("启动p10服务:init....");
        String command = "sh startup.sh";
        String dir=RuntimeUtils.class.getResource("/").getPath()+"KTServer";
        System.out.println(dir);
        try {
            RuntimeUtils.exec(command, null, dir);
            Process process = RuntimeUtils.exec(command, null, dir);
            //int i = process.waitFor();
            //System.exit(i);
        }catch (Exception e){
            log.error("启动p10异常："+e.getMessage());
        }
    }

//    @PreDestroy
    public void destroy(){
        System.out.println("关闭p10服务:destroy....");
        String command = "sh shutdown.sh";
        String dir=RuntimeUtils.class.getResource("/").getPath()+"KTServer";
        System.out.println(dir);
        try {
            RuntimeUtils.exec(command, null, dir);
        }catch (Exception e){
            log.error("关闭p10异常："+e.getMessage());
        }
    }
}
