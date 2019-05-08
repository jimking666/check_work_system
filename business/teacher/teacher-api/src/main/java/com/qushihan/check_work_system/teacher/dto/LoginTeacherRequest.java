package com.qushihan.check_work_system.teacher.dto;

import lombok.Data;

/**
 * 教师登陆请求
 */
@Data
public class LoginTeacherRequest {

    /**
     * 教师编号
     */
    private Long teacherNumber;

    /**
     * 教师密码
     */
    private String teacherPassword;
}
