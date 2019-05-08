package com.qushihan.check_work_system.course.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 判断创建课程状态枚举类
 */
@AllArgsConstructor
public enum CreateCourseStatus {

    /**
     * 创建成功
     */
    CREATE_SUCCESS(0, "创建成功"),

    /**
     * 重复创建
     */
    REPEAT_CREATE_FAIL(1, "重复创建")
    ;
    @Getter
    private Integer code;

    @Getter
    private String message;
}
