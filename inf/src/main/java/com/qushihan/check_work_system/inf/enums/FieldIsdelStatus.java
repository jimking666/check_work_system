package com.qushihan.check_work_system.inf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Isdel状态枚举类
 */
@AllArgsConstructor
public enum FieldIsdelStatus {

    /**
     * 未删除
     */
    ISDEL_FALSE(0, false, "未删除"),

    /**
     * 删除
     */
    ISDEL_TRUE(1, true, "删除");

    @Getter
    private Integer code;

    @Getter
    private Boolean isdel;

    @Getter
    private String message;
}
