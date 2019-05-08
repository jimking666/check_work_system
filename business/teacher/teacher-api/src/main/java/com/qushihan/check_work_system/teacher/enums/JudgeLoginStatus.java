package com.qushihan.check_work_system.teacher.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 判断登陆状态枚举类
 */
@AllArgsConstructor
public enum JudgeLoginStatus {

    /**
     * 未知错误
     */
    UNKNOW(-1, "未知错误"),

    /**
     * 登陆成功
     */
    LOGIN_SUCCESS(0, "登陆成功"),

    /**
     * 账号或密码错误
     */
    NUMBER_OR_PASSWORD_ERROR(1, "账号或密码错误"),

    /**
     * 账号禁用
     */
    ACCOUNT_DISABLED(2, "账号禁用");

    @Getter
    private Integer code;

    @Getter
    private String message;
}
