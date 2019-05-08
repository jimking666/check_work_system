package com.qushihan.check_work_system.student.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentDto {

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 学号
     */
    private Long studentNumber;

    /**
     * 学生密码
     */
    private String studentPassword;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 班级id
     */
    private Long clazzId;

    /**
     * 班级名称
     */
    private String clazzName;

    /**
     * 是否删除 ？0未删除 1删除
     */
    private Boolean isdel;
}
