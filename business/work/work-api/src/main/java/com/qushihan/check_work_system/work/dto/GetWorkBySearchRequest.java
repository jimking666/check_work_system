package com.qushihan.check_work_system.work.dto;

import lombok.Data;

/**
 * 搜索作业请求
 */
@Data
public class GetWorkBySearchRequest {

    /**
     * 搜索作业题目名字
     */
    private String searchWorkTitle;
}
