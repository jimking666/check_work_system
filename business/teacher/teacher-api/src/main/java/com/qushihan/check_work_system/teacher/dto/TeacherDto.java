package com.qushihan.check_work_system.teacher.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeacherDto {

    /**
     * 教师id
     */
    private Long teacherId;

    /**
     * 教师编号
     */
    private Long teacherNumber;

    /**
     * 教师密码
     */
    private String teacherPassword;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 是否删除 ？0未删除 1删除
     */
    private Boolean isdel;
}
