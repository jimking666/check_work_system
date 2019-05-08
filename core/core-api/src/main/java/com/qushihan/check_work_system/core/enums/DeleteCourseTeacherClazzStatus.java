package com.qushihan.check_work_system.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除课程教师班级枚举类
 */
@AllArgsConstructor
public enum DeleteCourseTeacherClazzStatus {

    /**
     * 删除成功
     */
    DELETE_SUCCESS(0, "删除成功"),

    /**
     * 删除失败
     */
    DELETE_FAIL(1, "删除失败");

    @Getter
    private Integer code;

    @Getter
    private String message;
}
