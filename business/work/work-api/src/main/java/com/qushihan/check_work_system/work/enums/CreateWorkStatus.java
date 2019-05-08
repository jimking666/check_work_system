package com.qushihan.check_work_system.work.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 判断发布作业状态枚举类
 */
@AllArgsConstructor
public enum CreateWorkStatus {

    /**
     * 发布成功
     */
    CREATE_SUCCESS(0, "发布成功"),

    /**
     * 重复发布
     */
    REPEAT_CREATE_FAIL(1, "重复发布");

    @Getter
    private Integer code;

    @Getter
    private String message;
}
