package com.qushihan.check_work_system.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 创建课程教师班级枚举类
 */
@AllArgsConstructor
public enum CreateCourseTeacherClazzStatus {

    /**
     * 创建成功
     */
    CREATE_SUCCESS(0, "创建成功"),

    /**
     * 重复创建
     */
    REPEAT_CREATE_FAIL(1, "重复创建");

    @Getter
    private Integer code;

    @Getter
    private String message;
}
