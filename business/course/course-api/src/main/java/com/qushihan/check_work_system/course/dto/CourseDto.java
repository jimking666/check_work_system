package com.qushihan.check_work_system.course.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseDto {

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 是否删除
     */
    private Boolean isdel;

    /**
     * 创建日期
     */
    private Date createTime;
}
