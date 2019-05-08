package com.qushihan.check_work_system.core.dto;

import lombok.Data;

/**
 * 创建课程老师班级请求
 */
@Data
public class CreateCourseTeacherClazzRequest {

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 教师id
     */
    private String teacherId;

    /**
     * 班级id
     */
    private String clazzId;
}
