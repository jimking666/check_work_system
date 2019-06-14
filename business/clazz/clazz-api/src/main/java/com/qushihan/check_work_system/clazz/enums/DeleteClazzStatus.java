package com.qushihan.check_work_system.clazz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除班级状态枚举类
 */
@AllArgsConstructor
public enum DeleteClazzStatus {

    /**
     * 删除成功
     */
    DELETE_SUCCESS(0, "删除成功"),

    /**
     * 无此班级
     */
    NOT_SUCH_COURSE(1, "无此班级"),

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
