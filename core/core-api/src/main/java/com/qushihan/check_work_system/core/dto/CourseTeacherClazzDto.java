package com.qushihan.check_work_system.core.dto;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseTeacherClazzDto {

    /**
     * 课程教师班级id
     */
    private Long courseTeacherClazzId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 教师id
     */
    private Long teacherId;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 班级id
     */
    private Long clazzId;

    /**
     * 班级名称
     */
    private String clazzName;

    /**
     * 作业发布数量
     */
    private Long workCount;

    /**
     * 是否删除
     */
    private Boolean isdel;

    /**
     * 创建时间
     */
    private Date createTime;
}
