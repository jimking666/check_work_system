package com.qushihan.check_work_system.teacher.dto;

import lombok.Data;

/**
 * 教师注册请求
 */
@Data
public class RegisterTeacherRequest {

    /**
     * 教师编号
     */
    private String teacherNumber;

    /**
     * 教师密码
     */
    private String teacherPassword;

    /**
     * 教师姓名
     */
    private String teacherName;
}
