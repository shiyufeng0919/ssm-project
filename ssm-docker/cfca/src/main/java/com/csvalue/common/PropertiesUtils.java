package com.csvalue.common;

import java.util.ResourceBundle;

//加载属性配置文件
public class PropertiesUtils {

    private static ResourceBundle resource;
    private static ResourceBundle resourceDB;

    static{
        //读取配置文件 config 为文件名
        resource = ResourceBundle.getBundle("properties/config");
        resourceDB=ResourceBundle.getBundle("properties/jdbc");
    }

    /**
     * 提供外部访问的方法
     * @param key
     * @return
     */
    public static String getProperty(String key){
        //以String格式返回 key 对应的 value
        return resource.getString(key);
    }

    public static String getDBProperty(String key){
        //以String格式返回 key 对应的 value
        return resourceDB.getString(key);
    }

    public static void main(String[] args){
        //读取制定的key
        String config = PropertiesUtils.getProperty("conf.url");
        System.out.println(config);
        String url=PropertiesUtils.getDBProperty("jdbc.url");
        System.out.println(url);
    }
}
