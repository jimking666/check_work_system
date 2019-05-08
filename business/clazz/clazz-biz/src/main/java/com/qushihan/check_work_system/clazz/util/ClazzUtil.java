package com.qushihan.check_work_system.clazz.util;

import java.util.Random;

public class ClazzUtil {

    /**
     * 使用两个随机生成的四位数xxxx进行拼接成clazzId
     *
     * @return
     */
    public static Long getClazzId() {
        int randomNumber1 = new Random().nextInt(9000) + 1000;
        int randomNumber2 = new Random().nextInt(9000) + 1000;
        String courseId = String.valueOf(randomNumber1) + String.valueOf(randomNumber2);
        return Long.parseLong(courseId);
    }
}
