package com.qushihan.check_work_system.teacher.util;

import java.util.Random;

public class TeacherUtil {

    /**
     * 使用teacherNumber + xxxx(四位数字)生成teacherId
     *
     * @param teacherNumber
     *
     * @return
     */
    public static Long getTeacherId(Long teacherNumber) {
        int randomNumber = new Random().nextInt(9000) + 1000;
        String teacherId = String.valueOf(teacherNumber) + String.valueOf(randomNumber);
        return Long.parseLong(teacherId);
    }
}
