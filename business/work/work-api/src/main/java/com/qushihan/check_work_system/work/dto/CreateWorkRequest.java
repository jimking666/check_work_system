package com.qushihan.check_work_system.work.dto;

import lombok.Data;

/**
 * 创建作业请求
 */
@Data
public class CreateWorkRequest {

    /**
     * 作业题目
     */
    private String workTitle;

    /**
     * 作业内容
     */
    private String workContent;

    /**
     * 重复率
     */
    private Float repetitiveRate;

    /**
     * 课程教师班级id
     */
    private String courseTeacherClazzId;
}
