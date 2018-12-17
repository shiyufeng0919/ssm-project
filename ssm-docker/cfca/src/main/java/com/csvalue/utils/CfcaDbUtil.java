package com.csvalue.utils;

import com.csvalue.common.PropertiesUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.sql.Connection;
import java.sql.DriverManager;

/*
* 初始化表结构
* */
public final class CfcaDbUtil {
    public static final String driver = PropertiesUtils.getDBProperty("jdbc.driver");
    public static final String url = PropertiesUtils.getDBProperty("jdbc.url");
    public static final String username = PropertiesUtils.getDBProperty("jdbc.username");
    public static final String userpwd = PropertiesUtils.getDBProperty("jdbc.userpwd");

    public static void run(){
        try {
            Class.forName(driver);
            //建立连接
            Connection connection=DriverManager.getConnection(url,username,userpwd);
            //创建ScriptRunner，用于执行SQL脚本
            ScriptRunner scriptRunner=new ScriptRunner(connection);
            scriptRunner.setErrorLogWriter(null);
            scriptRunner.setLogWriter(null);
            //执行sql脚本
            scriptRunner.runScript(Resources.getResourceAsReader("sql/"+"cfca"+".sql"));
            connection.close();//关闭连接
            System.out.println("=====success=========");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        run();
    }
}
