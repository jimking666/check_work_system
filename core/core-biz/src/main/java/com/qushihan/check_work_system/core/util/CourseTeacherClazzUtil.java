package com.qushihan.check_work_system.core.util;

import java.util.Random;

/**
 * 课程教师班级工具类
 */
public class CourseTeacherClazzUtil {

    /**
     * courseTeacherClazzId = courseId后三位 + teacherId后三位 + clazzId后三位 + 随机三位数
     *
     * @param courseId
     * @param teacherId
     * @param clazzId
     *
     * @return
     */
    public static Long getCourseTeacherClazzId(Long courseId, Long teacherId, Long clazzId) {
        int randomNumber = new Random().nextInt(900) + 100;
        String courseId1 = String.valueOf(courseId);
        courseId1 = courseId1.substring(courseId1.length() - 3);
        String teacherId1 = String.valueOf(teacherId);
        teacherId1 = teacherId1.substring(teacherId1.length() - 3);
        String clazzId1 = String.valueOf(clazzId);
        clazzId1 = clazzId1.substring(clazzId1.length() - 3);
        String courseTeacherClazzId = courseId1 + teacherId1 + clazzId1 + String.valueOf(randomNumber);
        return Long.parseLong(courseTeacherClazzId);
    }
}
