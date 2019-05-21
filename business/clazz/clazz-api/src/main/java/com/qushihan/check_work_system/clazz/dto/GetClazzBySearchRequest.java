package com.qushihan.check_work_system.clazz.dto;

import lombok.Data;

/**
 * 搜索班级请求
 */
@Data
public class GetClazzBySearchRequest {

    /**
     * 搜索班级名字
     */
    private String searchClazzName;
}
