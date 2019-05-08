package com.qushihan.check_work_system.clazz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 判断创建班级状态枚举类
 */
@AllArgsConstructor
public enum CreateClazzStatus {

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
