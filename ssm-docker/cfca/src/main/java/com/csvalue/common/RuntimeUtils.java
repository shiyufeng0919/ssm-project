package com.csvalue.common;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/*
* java调用sheet脚本工具类
* */
public class RuntimeUtils {
    public static Process exec(String command, String envp, String dir)
            throws IOException {
        String regex = "\\s+";
        String args[] = null;
        String envps[] = null;
        if (!StringUtils.isEmpty(command)) {
            args = command.split(regex);
        }
        if (!StringUtils.isEmpty(envp)) {
            envps = envp.split(regex);
        }
        return Runtime.getRuntime().exec(args, envps, new File(dir));
    }
}
