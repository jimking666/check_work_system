package com.qushihan.check_work_system.course.dto;

import lombok.Data;

/**
 * 搜索课程请求
 */
@Data
public class GetCourseBySearchRequest {
    /**
     * 搜索课程名字
     */
    private String searchCourseName;

}