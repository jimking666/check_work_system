package com.qushihan.check_work_system.work.util;

import java.util.Random;

public class WorkUtil {

    /**
     * 随机生成三组 xxxx(四位数字)最后拼接起来 生成 workId
     *
     * @return
     */
    public static Long getWorkId() {
        int randomNumber1 = new Random().nextInt(9000) + 1000;
        int randomNumber2 = new Random().nextInt(9000) + 1000;
        int randomNumber3 = new Random().nextInt(9000) + 1000;
        String workId = String.valueOf(randomNumber1) + String.valueOf(randomNumber2) + String.valueOf(randomNumber3);
        return Long.parseLong(workId);
    }
}
