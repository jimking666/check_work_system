package com.qushihan.check_work_system.work.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 删除作业状态枚举类
 */
@AllArgsConstructor
public enum DeleteWorkStatus {

    /**
     * 删除成功
     */
    DELETE_SUCCESS(0, "删除成功")
    ;

    @Getter
    private Integer code;

    @Getter
    private String message;
}
