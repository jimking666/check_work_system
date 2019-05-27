package com.qushihan.check_work_system.inf.util;

import java.util.Random;

public class GetIdUtil {

    /**
     * 随机生成三组 xxxx(四位数字)最后拼接起来 生成 xxxid
     *
     * @return
     */
    public static Long getId() {
        int randomNumber1 = new Random().nextInt(9000) + 1000;
        int randomNumber2 = new Random().nextInt(9000) + 1000;
        int randomNumber3 = new Random().nextInt(9000) + 1000;
        String id = String.valueOf(randomNumber1) + String.valueOf(randomNumber2) + String.valueOf(randomNumber3);
        return Long.parseLong(id);
    }
}
