package com.qushihan.check_work_system.course.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除课程状态枚举类
 */
@AllArgsConstructor
public enum DeleteCourseStatus {

    /**
     * 删除成功
     */
    DELETE_SUCCESS(0, "删除成功"),

    /**
     * 无此课程
     */
    NOT_SUCH_COURSE(1, "无此课程"),

    /**
     * 存在关联
     */
    HAVE_RELEVANCE(2, "存在关联")
    ;

    @Getter
    private Integer code;

    @Getter
    private String message;
}
