package com.qushihan.check_work_system.inf.util;

/**
 * 转换工具类
 */
public class TransitionUtil {

    /**
     * 将string类型转化为long类型
     *
     * @param str
     *
     * @return
     */
    public static Long stringToLong(String str) {
        String[] strs = str.split(",");
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            sb.append(s);
        }
        return Long.parseLong(sb.toString());
    }
}
