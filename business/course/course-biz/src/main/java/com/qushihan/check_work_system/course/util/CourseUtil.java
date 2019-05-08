package com.qushihan.check_work_system.course.util;

import java.util.Random;

import com.qushihan.check_work_system.course.model.auto.Course;

public class CourseUtil {

    /**
     * 随机生成 xxxx(四位数字)
     * 使用courseName字符串长度 * xxxx 再拼接 xxxx生成courseId
     *
     * @param courseName
     *
     * @return
     */
    public static Long getCourseId(String courseName) {
        //String——>char[]
        char course[] = courseName.toCharArray();
        int randomNumber = new Random().nextInt(9000) + 1000;
        String courseId = String.valueOf(course.length * randomNumber) + String.valueOf(randomNumber);
        return Long.parseLong(courseId);
    }
}
