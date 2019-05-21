package com.qushihan.check_work_system.core.dto;

import lombok.Data;

/**
 * 搜索课程教师班级请求
 */
@Data
public class GetCourseTeacherClazzBySearchRequest {
    /**
     * 搜索课程名字
     */
    private String searchCourseName;

}

